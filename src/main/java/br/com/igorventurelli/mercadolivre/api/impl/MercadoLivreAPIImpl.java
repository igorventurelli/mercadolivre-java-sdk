package br.com.igorventurelli.mercadolivre.api.impl;

import br.com.igorventurelli.mercadolivre.api.MercadoLivreAPI;
import br.com.igorventurelli.mercadolivre.domain.AppInformation;
import br.com.igorventurelli.mercadolivre.domain.UserInformation;
import br.com.igorventurelli.mercadolivre.domain.enumeration.AppScope;
import br.com.igorventurelli.mercadolivre.domain.enumeration.TokenType;
import br.com.igorventurelli.mercadolivre.exception.CodeNotValidException;
import br.com.igorventurelli.mercadolivre.exception.MercadoLivreException;
import br.com.igorventurelli.mercadolivre.http.HttpRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.HttpResponseStatus;
import com.ning.http.client.Response;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MercadoLivreAPIImpl implements MercadoLivreAPI {

    private final HttpRequest http;
    private final AppInformation appInformation;
    private UserInformation userInformation;

    public MercadoLivreAPIImpl(final AppInformation appInformation) {
        http = new HttpRequest();
        this.appInformation = appInformation;
    }

    @Override
    public void authorize(final String code, final String callbackUrl) {
        final FluentStringsMap params = new FluentStringsMap();
        params.add("grant_type", "authorization_code");
        params.add("client_id", String.valueOf(appInformation.getClientId()));
        params.add("client_secret", appInformation.getClientSecret());
        params.add("code", code);
        params.add("redirect_uri", callbackUrl);

        final Response response = http.post("/oauth/token", params);

        parseAuthorizationResponse(response);
    }

    private void parseAuthorizationResponse(final Response response) {
        final JsonObject jsonObject;
        try {
            jsonObject = new JsonParser().parse(response.getResponseBody()).getAsJsonObject();
        } catch (IOException e) {
            throw new MercadoLivreException(e);
        }

        if(response.getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
            final String message = jsonObject.get("message").getAsString();
            final String error = jsonObject.get("error").getAsString();

            throw new CodeNotValidException(String.format("Error: %s - Message from API: %s", error, message));
        }

        final String accessToken = jsonObject.get("access_token").getAsString();
        final String refreshToken = jsonObject.get("refresh_token").getAsString();
        final String tokenTypeString = jsonObject.get("token_type").getAsString();
        final TokenType tokenType = TokenType.getFromString(tokenTypeString);
        final Long expiresIn = jsonObject.get("expires_in").getAsLong();
        final String[] scopesString = jsonObject.get("scope").getAsString().split(" ");
        List<AppScope> scopes = new ArrayList<>();
        for(final String scope : scopesString) {
            scopes.add(AppScope.getFromString(scope));
        }

        userInformation = new UserInformation(accessToken, refreshToken, expiresIn, scopes, tokenType);
        System.out.println(userInformation.toString());
    }

    @Override
    public void uploadImage(final String imageUrl) {

    }

    @Override
    public void refreshToken() {

    }
}

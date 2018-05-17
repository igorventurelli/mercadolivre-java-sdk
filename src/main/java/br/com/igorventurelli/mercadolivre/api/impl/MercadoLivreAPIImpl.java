package br.com.igorventurelli.mercadolivre.api.impl;

import br.com.igorventurelli.mercadolivre.api.MercadoLivreAPI;
import br.com.igorventurelli.mercadolivre.domain.AppInformation;
import br.com.igorventurelli.mercadolivre.http.HttpRequest;
import com.ning.http.client.FluentStringsMap;

public class MercadoLivreAPIImpl implements MercadoLivreAPI {

    private final HttpRequest http;
    private final AppInformation appInformation;

    public MercadoLivreAPIImpl(final AppInformation appInformation) {
        http = new HttpRequest();
        this.appInformation = appInformation;
    }

    @Override
    public void authorize(String code, String redirectUrl) {
        FluentStringsMap params = new FluentStringsMap();
        params.add("grant_type", "authorization_code");
        params.add("client_id", String.valueOf(appInformation.getClientId()));
        params.add("client_secret", appInformation.getClientSecret());
        params.add("code", code);
        params.add("redirect_uri", redirectUrl);

        http.post("/oauth/token", params);
    }

    @Override
    public void uploadImage(String imageUrl) {

    }

    @Override
    public void refreshToken() {

    }
}

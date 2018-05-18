package br.com.igorventurelli.mercadolivre.domain;

import br.com.igorventurelli.mercadolivre.domain.enumeration.AppScope;
import br.com.igorventurelli.mercadolivre.domain.enumeration.TokenType;
import lombok.Value;

import java.util.List;

@Value
public final class UserInformation {

    private final String accessToken;
    private final String refreshToken;
    private final Long expiresIn;
    private final List<AppScope> scopes;
    private final TokenType tokenType;

}

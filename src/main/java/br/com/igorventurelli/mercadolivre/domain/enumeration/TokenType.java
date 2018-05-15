package br.com.igorventurelli.mercadolivre.domain.enumeration;

public enum TokenType {

    BEARER("bearer");

    private final String type;

    private TokenType(final String type) {
        this.type = type;
    }
}

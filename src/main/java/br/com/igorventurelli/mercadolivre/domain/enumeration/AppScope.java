package br.com.igorventurelli.mercadolivre.domain.enumeration;

public enum AppScope {

    READ("read"), OFFLINE_ACCESS("offline_access"), WRITE("write");

    private final String scope;

    private AppScope(final String scope) {
        this.scope = scope;
    }
}

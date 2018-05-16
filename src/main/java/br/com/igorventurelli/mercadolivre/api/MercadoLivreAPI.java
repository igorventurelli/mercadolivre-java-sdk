package br.com.igorventurelli.mercadolivre.api;

public interface MercadoLivreAPI {

    void authorize(final String code, final String redirectUrl);

    void uploadImage(final String imageUrl);

    void refreshToken();

}

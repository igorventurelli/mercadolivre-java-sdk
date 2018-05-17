package br.com.igorventurelli.mercadolivre;

import br.com.igorventurelli.mercadolivre.api.MercadoLivreAPI;
import br.com.igorventurelli.mercadolivre.api.impl.MercadoLivreAPIImpl;
import br.com.igorventurelli.mercadolivre.domain.AppInformation;
import br.com.igorventurelli.mercadolivre.domain.UserInformation;

public final class MercadoLivre {

    private final AppInformation appInformation;
    private final MercadoLivreAPI ml;


    //private UserInformation userInformation;

    public MercadoLivre(final Long clientId, final String clientSecret) {
        appInformation = new AppInformation(clientId, clientSecret);
        ml = new MercadoLivreAPIImpl(appInformation);
        ml.authorize("TG-5afb7c25e4b0d7c5311eb778-319841180", "https://mercadolivre.templomilenar.com.br");
    }

}

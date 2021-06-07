package br.com.zup.mercadolivre.shopping;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPayment {

    pagseguro, paypal;

    public String returnPayment(UriComponentsBuilder uriComponentsBuilder,
                                Shopping shopping) {
        if (this.equals(pagseguro)) {
            String urlPagseguro = uriComponentsBuilder.path("/pagseguro-return/{id}")
                    .buildAndExpand(shopping.getId()).toString();
            return  "pagseguro.com?returnId="+ shopping.getId()
                    + "&redirectUrl="+urlPagseguro;
        } else {
            String urlPaypal = uriComponentsBuilder.path("/paypal-return/{id}")
                    .buildAndExpand(shopping.getId()).toString();
           return  "paypal.com?buyerId="+ shopping.getId()
                    + "&redirectUrl="+urlPaypal;
        }
    }
}

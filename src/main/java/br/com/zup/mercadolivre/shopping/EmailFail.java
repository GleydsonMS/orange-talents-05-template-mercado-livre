package br.com.zup.mercadolivre.shopping;

import br.com.zup.mercadolivre.shared.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class EmailFail {

    @Autowired
    private Mailer mailer;

    public void failShopping(@NotNull @Valid Shopping shopping, UriComponentsBuilder uriComponentsBuilder) {
        String urlReturn = shopping.getGateway().returnPayment(uriComponentsBuilder, shopping);

        mailer.send("Houve falha na compra, tente novamente pelo link: " + urlReturn,
                "Assunto do email enviado, Falha na compra.",
                shopping.getOwner().getEmail(),
                "problema-na-compra@mercadolivre.zup.com.br",
                shopping.getCustomer().getEmail());
    }
}

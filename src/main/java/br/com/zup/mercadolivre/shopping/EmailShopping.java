package br.com.zup.mercadolivre.shopping;

import br.com.zup.mercadolivre.shared.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class EmailShopping {

    @Autowired
    private Mailer mailer;

    public void newShopping(@NotNull @Valid Shopping shopping) {
        mailer.send("Template do corpo do email enviado. " + shopping.getCustomer().getEmail(),
                "Assunto do email enviado",
               "Mercado Livre",
                "interesse-compra@mercadolivre.zup.com.br",
                shopping.getOwner().getEmail());
    }
}

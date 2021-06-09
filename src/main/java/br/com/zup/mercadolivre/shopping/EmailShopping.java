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
        mailer.send("Interesse na compra do seu produto: " + shopping.getCustomer().getEmail(),
                "Interesse na compra do produto.",
               "Mercado Livre",
                "interesse-compra@mercadolivre.zup.com.br",
                shopping.getOwner().getEmail());
    }
}

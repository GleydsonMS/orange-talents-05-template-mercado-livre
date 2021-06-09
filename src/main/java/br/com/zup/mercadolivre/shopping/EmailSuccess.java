package br.com.zup.mercadolivre.shopping;

import br.com.zup.mercadolivre.shared.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailSuccess implements ShoppingSuccessEvent{

    @Autowired
    private Mailer mailer;

    @Override
    public void process(Shopping shopping) {
        mailer.send("Template do corpo do email enviado da compra efetuada com sucesso. ",
                "Compra efetuada com sucesso",
                shopping.getOwner().getEmail(),
                "compra-efetuada@mercadolivre.zup.com.br",
                shopping.getCustomer().getEmail());
    }
}

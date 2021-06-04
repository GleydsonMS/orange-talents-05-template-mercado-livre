package br.com.zup.mercadolivre.questions;

import br.com.zup.mercadolivre.shared.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class Emails {

    @Autowired
    private Mailer mailer;

    public void newQuestion(@NotNull @Valid Question question) {
        mailer.send("Template do corpo do email enviado.",
                "Assunto do email enviado",
                question.getCreator().getEmail(),
                "questions@mercadolivre.zup.com.br",
                question.getProductOwner().getEmail());
    }
}

package br.com.zup.mercadolivre.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ExistIdValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ExistId {

    String message() default "Não existe este id informado no banco de dados.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldName();

    Class<?> domainClass();
}

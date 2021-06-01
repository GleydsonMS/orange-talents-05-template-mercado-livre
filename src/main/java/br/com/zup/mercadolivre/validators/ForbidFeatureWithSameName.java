package br.com.zup.mercadolivre.validators;

import br.com.zup.mercadolivre.products.ProductDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ForbidFeatureWithSameName implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        ProductDTO productDTO = (ProductDTO) o;
        Set<String> sameFeatures = productDTO.findSameFeatures();
        if(!sameFeatures.isEmpty()) {
            errors.rejectValue("features", null, "Um produto não pode ter características iguais. " + sameFeatures);
        }
    }
}

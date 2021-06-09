package br.com.zup.mercadolivre.shopping;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class Ranking implements ShoppingSuccessEvent {

    @Override
    public void process(Shopping shopping) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/ranking";

        Map<String, Object> request = Map.of("shoppingId", shopping.getId(),
                "ownerId", shopping.getOwner().getId());

        restTemplate.postForEntity(url, request, String.class);
    }
}

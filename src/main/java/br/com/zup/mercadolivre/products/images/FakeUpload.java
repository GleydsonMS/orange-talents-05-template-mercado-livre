package br.com.zup.mercadolivre.products.images;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class FakeUpload implements UploadImages {

    public Set<String> send(List<MultipartFile> images) {
        return images.stream()
                .map(image -> "http://fakerepository.com/mercado-livre/product-images/"
                        + UUID.randomUUID().toString() + "-"
                        + image.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}

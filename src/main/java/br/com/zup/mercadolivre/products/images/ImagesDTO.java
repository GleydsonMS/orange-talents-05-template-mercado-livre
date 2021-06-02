package br.com.zup.mercadolivre.products.images;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagesDTO {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> images = new ArrayList<>();

    public ImagesDTO(@Size(min = 1) @NotNull List<MultipartFile> images) {
        this.images = images;
    }

    public List<MultipartFile> getImages() {
        return images;
    }
}

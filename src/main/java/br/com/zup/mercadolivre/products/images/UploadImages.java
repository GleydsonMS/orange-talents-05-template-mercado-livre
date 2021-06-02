package br.com.zup.mercadolivre.products.images;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface UploadImages {
    public Set<String> send(List<MultipartFile> images);
}

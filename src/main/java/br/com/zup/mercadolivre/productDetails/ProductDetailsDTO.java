package br.com.zup.mercadolivre.productDetails;

import br.com.zup.mercadolivre.products.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class ProductDetailsDTO {

    private String name;
    private BigDecimal price;
    private String description;
    private Set<ProductFeatureDetails> features;
    private Set<String> imageLinks;
    private SortedSet<String> questions;
    private Set<Map<String,String>> opinions;
    private Double averageScore;
    private Integer totalOfOpinions;

    public ProductDetailsDTO(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.features = product.mapFeatures(ProductFeatureDetails::new);
        this.imageLinks = product.mapImages(image -> image.getLink());
        this.questions = product.mapQuestions(question -> question.getTitle());

        OpinionsDetails opinionsDetails = product.geOpinions();

        this.opinions = opinionsDetails.mapOpinions(opinion -> {
            return Map.of("title", opinion.getTitle(), "description", opinion.getDescription());
        });

        this.averageScore = opinionsDetails.average();
        this.totalOfOpinions = opinionsDetails.total();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Set<ProductFeatureDetails> getFeatures() {
        return features;
    }

    public Set<String> getImageLinks() {
        return imageLinks;
    }

    public SortedSet<String> getQuestions() {
        return questions;
    }

    public Set<Map<String, String>> getOpinions() {
        return opinions;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public int getTotalOfOpinions() {
        return totalOfOpinions;
    }
}

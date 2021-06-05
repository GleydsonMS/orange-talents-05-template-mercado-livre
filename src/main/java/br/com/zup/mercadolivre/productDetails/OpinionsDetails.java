package br.com.zup.mercadolivre.productDetails;

import br.com.zup.mercadolivre.opinions.Opinion;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OpinionsDetails {

    private Set<Opinion> opinions;

    public OpinionsDetails(Set<Opinion> opinions) {
        this.opinions = opinions;
    }

    public <T> Set<T> mapOpinions(Function<Opinion, T> mapFunction) {
        return this.opinions.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public double average() {
        Set<Integer> scores = mapOpinions(opinion -> opinion.getScore());
        OptionalDouble average = scores.stream().mapToInt(score -> score).average();
        return average.orElse(0.0);
    }

    public int total() {
        return this.opinions.size();
    }
}

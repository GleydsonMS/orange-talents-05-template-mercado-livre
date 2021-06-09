package br.com.zup.mercadolivre.externalSystems;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ExternalSystemsController {

    @PostMapping("/notas-fiscais")
    public void processNF(@RequestBody @Valid NotaFiscalDTO notaFiscalDTO){
        System.out.println(notaFiscalDTO.toString());
    }

    @PostMapping("/ranking")
    public void processRanking(@RequestBody @Valid RankingDTO rankingDTO) {
        System.out.println(rankingDTO.toString());
    }
}

package com.lucasnogueira.onebrc.springbatch.processor;

import com.lucasnogueira.onebrc.springbatch.impl.Calculo;
import com.lucasnogueira.onebrc.springbatch.impl.CalculoMap;
import com.lucasnogueira.onebrc.springbatch.impl.ConteudoLinha;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ProcessCalculoMediaConfig {

    @Autowired
    private CalculoMap mapa;

    @Bean
    public ItemProcessor<ConteudoLinha, Map.Entry<String, Calculo>> processCalculoMedia() {

        return conteudoLinha -> {
            Calculo calculo = mapa.getMapa().computeIfAbsent(conteudoLinha.getNomeCidade(),
                    key -> {
                        Calculo novoCalculo = new Calculo();
                        novoCalculo.setNumMin(conteudoLinha.getTemperatura());
                        novoCalculo.setNumMax(conteudoLinha.getTemperatura());
                        return novoCalculo;
                    });

            calculo.setNumMax(Math.max(conteudoLinha.getTemperatura(), calculo.getNumMax()));
            calculo.setNumMin(Math.min(conteudoLinha.getTemperatura(), calculo.getNumMin()));

            return Map.entry(conteudoLinha.getNomeCidade(), calculo);
        };

    }
}

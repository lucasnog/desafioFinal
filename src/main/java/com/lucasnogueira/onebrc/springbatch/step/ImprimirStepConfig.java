package com.lucasnogueira.onebrc.springbatch.step;

import com.lucasnogueira.onebrc.springbatch.impl.CalculoMap;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ImprimirStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CalculoMap mapa;

    @Bean
    public Step imprimir() {
        return stepBuilderFactory
                .get("imorimirStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(mapa);
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();

    }
}

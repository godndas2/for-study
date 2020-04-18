package com.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class BatchJobTest {

    private final String BATCH_NAME = "batchJob_";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get(BATCH_NAME)
                .start(stepA())
                .next(stepB())
                .build();
    }

    @Bean
    public Step stepA() {
        return stepBuilderFactory.get(BATCH_NAME + "stepA")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(BATCH_NAME + "stepA Started");

                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step stepB() {
        return stepBuilderFactory.get(BATCH_NAME + "stepB")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(BATCH_NAME + "stepB Started");

                    return RepeatStatus.FINISHED;
                }).build();
    }
}

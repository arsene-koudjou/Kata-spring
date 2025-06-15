package com.kata.spring.swagger.config;
import com.kata.spring.swagger.batch.IntegerToStringProcessor;
import com.kata.spring.swagger.batch.JobCompletionNotificationListener;
import com.kata.spring.swagger.model.NumberItem;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {
    @Bean
    @StepScope
    public FlatFileItemReader<NumberItem> reader(@Value("${kata.openapi.source-file}") String filePath) {
        return new FlatFileItemReaderBuilder<NumberItem>()
                .name("numberReader")
                .resource(new FileSystemResource(filePath))
                .lineMapper((line, lineNumber) -> new NumberItem(Integer.parseInt(line)))
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<String> writer(@Value("${kata.openapi.dest-file}") String outputPath) {
        return new FlatFileItemWriterBuilder<String>()
                .name("stringWriter")
                .resource(new FileSystemResource(outputPath))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    @Bean
    public IntegerToStringProcessor processor() {
        return new IntegerToStringProcessor();
    }

    @Bean
    public Job importJob(JobRepository jobRepository,
                         Step step1,
                         JobCompletionNotificationListener listener) {
        return new JobBuilder("importJob", jobRepository)
                .start(step1)
                .listener(listener)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      FlatFileItemReader<NumberItem> reader,
                      FlatFileItemWriter<String> writer) {
        return new StepBuilder("step1", jobRepository)
                .<NumberItem, String>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor())
                .writer(writer)
                .build();
    }
}
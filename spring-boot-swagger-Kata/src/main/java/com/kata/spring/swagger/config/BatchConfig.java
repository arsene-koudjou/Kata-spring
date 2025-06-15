package com.kata.spring.swagger.config;
import com.kata.spring.swagger.batch.ParseIntegerToStringProcessor;
import com.kata.spring.swagger.batch.listeners.NotificationListenerForJobCompletion;
import com.kata.spring.swagger.batch.listeners.SkipLoggingListener;
import com.kata.spring.swagger.model.KataItem;
import com.kata.spring.swagger.service.KataService;
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
    public FlatFileItemReader<KataItem> reader(@Value("${kata.openapi.source-file}") String filePath) {
        return new FlatFileItemReaderBuilder<KataItem>()
                .name("numberReader")
                .resource(new FileSystemResource(filePath))
                .lineMapper((line, lineNumber) -> new KataItem(Integer.parseInt(line)))
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
    public ParseIntegerToStringProcessor processor() {
        return new ParseIntegerToStringProcessor(new KataService());
    }

    @Bean
    public Job importJob(JobRepository jobRepository,
                         Step kataStep,
                         NotificationListenerForJobCompletion listener) {
        return new JobBuilder("importJob", jobRepository)
                .start(kataStep)
                .listener(listener)
                .build();
    }

    @Bean
    public Step kataStep(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      FlatFileItemReader<KataItem> reader,
                      FlatFileItemWriter<String> writer) {
        return new StepBuilder("kataStep", jobRepository)
                .<KataItem, String>chunk(15, transactionManager)
                .reader(reader)
                .processor(processor())
                .writer(writer)
                .faultTolerant()
                .skip(IllegalArgumentException.class)
                .skip(NumberFormatException.class)
                .skip(FlatFileParseException.class)
                .skipLimit(50)
                .listener(new SkipLoggingListener())
                .build();
    }
}
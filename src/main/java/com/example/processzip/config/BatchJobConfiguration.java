package com.example.processzip.config;

import com.example.processzip.model.Data;
import com.example.processzip.reader.DataReader;
import com.example.processzip.writer.JdbcWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataReader dataReader;

    @Autowired
    private JdbcWriter jdbcWriter;


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Data, Data>chunk(10)
                .reader(dataReader.multiResourceItemReader())
                .writer(jdbcWriter.itemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }
}

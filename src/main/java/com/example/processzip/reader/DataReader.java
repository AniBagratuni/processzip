package com.example.processzip.reader;

import com.example.processzip.mapper.DataMapper;
import com.example.processzip.model.Data;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class DataReader {

    @Value("data/part_*.csv")
    private Resource[] inputResources;

    private static final String[] TOKENS = {"firstName", "lastName", "date"};

    @Bean
    public MultiResourceItemReader<Data> multiResourceItemReader() {
        MultiResourceItemReader<Data> resourceItemReader = new MultiResourceItemReader<>();
        resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate(itemReader());
        return resourceItemReader;
    }

    private FlatFileItemReader<Data> itemReader() {

        FlatFileItemReader<Data> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);

        reader.setLineMapper(new DefaultLineMapper() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(TOKENS);
                    }
                });

                //Set values in Data class
                setFieldSetMapper(new DataMapper());
            }
        });
        return reader;
    }
}


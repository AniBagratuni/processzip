package com.example.processzip.mapper;

import com.example.processzip.model.Data;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.util.UUID;

public class DataMapper implements FieldSetMapper<Data> {

    @Override
    public Data mapFieldSet(FieldSet fieldSet) {
        return new Data( UUID.randomUUID(),
                fieldSet.readString("firstName"),
                fieldSet.readString("lastName"),
                fieldSet.readString("date"));
    }
}
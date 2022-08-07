package com.ss.ers.app;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, java.sql.Date> {
    
    @Override
    public java.sql.Date convertToDatabaseColumn (LocalDate date) {
        
        return date == null ? null : java.sql.Date.valueOf (date);
    }

    @Override
    public LocalDate convertToEntityAttribute (java.sql.Date value) {
        return value == null ? null : value.toLocalDate ();
    }
    



    
}

package com.ss.ers.app;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp>{

    @Override
    public Timestamp convertToDatabaseColumn (LocalDateTime dateTime) {
        
        return  dateTime == null ? null : java.sql.Timestamp.valueOf (dateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute (Timestamp dbData) {
   
        return dbData == null ? null : dbData.toLocalDateTime ();
    }
    
}

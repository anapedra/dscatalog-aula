package com.anasatanaslopessantantospedra.dscatalog.controller.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidetionError extends StanderdErrer{
    private List<FieldMessage> errors=new ArrayList<>();

    public ValidetionError(Instant now, int value, String error, String message, String requestURI) {
        super();
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
    public void addError(String fieldName, String message){
       errors.add(new FieldMessage(fieldName,message));
    }
}

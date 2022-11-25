package com.anasatanaslopessantantospedra.dscatalog.service.exceptions;

public class DataBaseException extends RuntimeException{
    private static final long serialVersionUID=1L;
    public DataBaseException(Object id){
        super("Resource not found. Id "+ id);
    }
}

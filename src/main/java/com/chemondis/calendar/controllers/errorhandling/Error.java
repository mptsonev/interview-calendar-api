package com.chemondis.calendar.controllers.errorhandling;

import java.io.Serializable;

public class Error implements Serializable {

    private static final long serialVersionUID = 2L;

    public String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

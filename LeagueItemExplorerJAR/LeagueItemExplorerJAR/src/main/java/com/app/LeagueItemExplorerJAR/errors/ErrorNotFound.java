package com.app.LeagueItemExplorerJAR.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ErrorNotFound extends RuntimeException{
    private HttpStatusCode statusCode;
    private String message;

    public ErrorNotFound(String message) {
        this.statusCode = HttpStatus.NOT_FOUND;
        this.message = message;
    }

}


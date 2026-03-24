package com.traning.ecommerce.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//Day-4: Global Exception Handling
@RestControllerAdvice
public class GlobalExceptionHandler {

    //Day-4: Global Exception Handling
    // Tell Spring to trigger this method whenever a validation fails
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        // Loop through all the validation errors Spring found
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            // Get the field name (e.g., "name" or "price")
            String fieldName = ((FieldError) error).getField();
            // Get your custom message (e.g., "Product name is required")
            String errorMessage = error.getDefaultMessage();

            // Put them in the map!
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
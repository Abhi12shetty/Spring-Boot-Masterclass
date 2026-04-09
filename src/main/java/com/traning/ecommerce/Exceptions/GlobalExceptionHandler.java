package com.traning.ecommerce.Exceptions;

import com.traning.ecommerce.Payloads.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
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

    //Day 22: Global Exception Handling
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
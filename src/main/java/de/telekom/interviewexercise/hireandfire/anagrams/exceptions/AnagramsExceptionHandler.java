package de.telekom.interviewexercise.hireandfire.anagrams.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class AnagramsExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AnagramKeyNotFoundException.class)
    public final ResponseEntity<Object> handleBookingNotFoundException(AnagramKeyNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        AnagramErrorMessage error = new InvalidAnagramKeyErrorMessage("Invalid Anagram Key!", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public final ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        AnagramErrorMessage error = new AnagramConstraintViolationErrorMessage("Input string must have length between 2 to 10 and contain only alphabetic characters!", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
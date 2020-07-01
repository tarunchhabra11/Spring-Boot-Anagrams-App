package de.telekom.interviewexercise.hireandfire.anagrams.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AnagramKeyNotFoundException extends RuntimeException{


    public AnagramKeyNotFoundException(String input) {
        super("Invalid Anagram key : " + input);
    }
}

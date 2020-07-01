package de.telekom.interviewexercise.hireandfire.anagrams.exceptions;

import java.util.List;

public interface AnagramErrorMessage {
    String getMessage();

    List<String> getDetails();

    void setMessage(String message);

    void setDetails(List<String> details);
}

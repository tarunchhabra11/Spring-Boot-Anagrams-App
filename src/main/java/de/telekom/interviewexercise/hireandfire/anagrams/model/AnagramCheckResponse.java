package de.telekom.interviewexercise.hireandfire.anagrams.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AnagramCheckResponse
{
    public AnagramCheckResponse(
            Boolean isAnagram,
            Set<String> anagrams)
    {
        super();
        this.isAnagram = isAnagram;
        this.anagrams = anagrams == null ? new HashSet<>() : anagrams;
    }

    private Boolean isAnagram = false;
    private Set<String> anagrams;
}

package de.telekom.interviewexercise.hireandfire.anagrams.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AnagramsResponse
{
    private Set<String> anagrams;

    public AnagramsResponse(Set<String> anagrams)
    {
        super();
        this.anagrams = anagrams;
    }
}

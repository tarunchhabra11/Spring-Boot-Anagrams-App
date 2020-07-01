package de.telekom.interviewexercise.hireandfire.anagrams.service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Provides the functionality to generate and check anagrams.
 * Two strings are considered anagrams if they are written using the same exact letters and length, ignoring space, punctuation, and capitalization.
 * All generated anagrams must be of the exaxt same length and use the same letters as the base string.
 */
public interface AnagramCheckerService
{
    /**
     * Checks whether the given strings are anagrams of each other.
     * @param first the first string
     * @param second the second string
     * @return true if they are, false otherwise
     */
    boolean isAnagram(
            String first,
            String second);

    /**
     * Generates an identifier which is the same for all strings within an anagram set and can be calculated from
     * each of the anagrams, regardless of their position etc.
     * @param input the string to generate a key for.
     * @return Some character sequence which allows to identify all anagrams based on a set of letters.
     */
    char[] generateKey(String input);

    /**
     * Generates all the anagrams (permutations) for the letters in the given string.
     * @param input The start string to generate anagrams.
     * @return A set of all anagrams, with no duplicates.
     */
    Set<String> generateAnagrams(@NotNull @NotEmpty String input);
}

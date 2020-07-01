package de.telekom.interviewexercise.hireandfire.anagrams.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Service
public class SimpleAnagramCheckerServiceImpl implements AnagramCheckerService {
    @Override
    public boolean isAnagram(
        String first,
        String second) {
        if (first == null || second == null || first.isEmpty() || second.isEmpty()) {
            return false;
        }
        return Arrays.equals(generateKey(first), generateKey(second));
    }

    /**
     * @param input the string to generate a key for.
     * @return output key is sorted value of string in ascending order which will be same for all anagram strings.
     */
    @Override
    public char[] generateKey(String input) {
        char key[] = input.toCharArray();
        Arrays.sort(key);
        return key;
    }

    @Override
    public Set<String> generateAnagrams(@NotNull @NotEmpty String str) {
        Set<String> result = new HashSet<>();
        if (str == null) {
            return null;
        } else if (str.length() == 0) {
            result.add("");
            return result;
        }
        char initial = str.charAt(0); // first character
        String rem = str.substring(1); // Full string without first character
        Set<String> words = generateAnagrams(rem);
        for (String strNew : words) {
            for (int i = 0; i <= strNew.length(); i++) {
                result.add(charInsert(strNew, initial, i));
            }
        }
        return result;
    }

    private static String charInsert(String str, char ch, int j) {
        String start = str.substring(0, j);
        String end = str.substring(j);
        return start + ch + end;
    }

}

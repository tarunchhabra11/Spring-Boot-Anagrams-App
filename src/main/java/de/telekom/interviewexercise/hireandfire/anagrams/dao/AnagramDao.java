package de.telekom.interviewexercise.hireandfire.anagrams.dao;

import de.telekom.interviewexercise.hireandfire.anagrams.DataRepos.AnagramRepo;
import de.telekom.interviewexercise.hireandfire.anagrams.entity.Anagram;
import de.telekom.interviewexercise.hireandfire.anagrams.service.SimpleAnagramCheckerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class AnagramDao {
    @Autowired
    private AnagramRepo anagramRepo;

    @Autowired
    private SimpleAnagramCheckerServiceImpl simpleAnagramCheckerServiceImpl;


    public Boolean anagramExists(String input) {
        String anagramsKey = new String(simpleAnagramCheckerServiceImpl.generateKey(input));
        return !anagramRepo.findAnagramByKey(anagramsKey).isEmpty();
    }

    public void persistAnagrams(Set<String> anagrams, String input) {
        Anagram anagram = new Anagram();
        String anagramsKey = new String((simpleAnagramCheckerServiceImpl.generateKey(anagrams.stream().findAny().orElse(null))));
        anagram.setInput(input);
        anagram.setKey(anagramsKey);
        anagram.setValue(anagrams);
        anagramRepo.save(anagram);
    }

    public void deleteAnagram(String text) {
        String anagramsKey = new String((simpleAnagramCheckerServiceImpl.generateKey(text)));
        if (!retrieveAnagrams(anagramsKey).isEmpty())
            anagramRepo.deleteAnagramByKey(anagramsKey);
    }

    public Set<String> retrieveAnagrams(String text) {
        String anagramsKey = new String((simpleAnagramCheckerServiceImpl.generateKey(text)));
        return anagramRepo.findAnagramByKey(anagramsKey).stream().map(Anagram::getValue).findAny().get();
    }
}

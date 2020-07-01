package de.telekom.interviewexercise.hireandfire.anagrams.DataRepos;

import de.telekom.interviewexercise.hireandfire.anagrams.entity.Anagram;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface AnagramRepo extends JpaRepository<Anagram, Long> {

    Set<Anagram> findAnagramByKey(String key);

    @Transactional
    void deleteAnagramByKey(String key);

}

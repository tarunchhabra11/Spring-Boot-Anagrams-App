package de.telekom.hireandfire.anagrams

import de.telekom.interviewexercise.InterviewExerciseApplication
import de.telekom.interviewexercise.hireandfire.anagrams.dao.AnagramDao
import de.telekom.interviewexercise.hireandfire.anagrams.service.SimpleAnagramCheckerServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = InterviewExerciseApplication)
@ActiveProfiles("test")
class AnagramDaoTest extends Specification {

    @Autowired(required = false)
    private AnagramDao dao

    @Autowired(required = false)
    private SimpleAnagramCheckerServiceImpl anagramCheck

    static final String TEST_INPUT = "test";

    def "all expected beans are created"() {
        expect: "the REST-Controller is created"
        dao
    }

    def "An anagram for a given string does not exist"() {
        expect: "The result is false"
        dao.anagramExists("NonExistent") == false
    }

    def "Exists returns true for a persisted string"() {
        expect: "The result is true"
        when:
        dao.persistAnagrams(anagramCheck.generateAnagrams(TEST_INPUT), TEST_INPUT)
        then:
        dao.anagramExists(TEST_INPUT) == true
        cleanup:
        if (dao.anagramExists(TEST_INPUT)){
            dao.deleteAnagram(TEST_INPUT)
        }
    }

    def "Existing anagrams can be retrieved"() {
        setup: dao.persistAnagrams(anagramCheck.generateAnagrams(TEST_INPUT), TEST_INPUT)
        expect: "The result is the set of anagrams"
        when:
        dao.retrieveAnagrams(TEST_INPUT)
        // TODO: implement test call - remove the return stmt
        then:
        dao.anagramExists(TEST_INPUT) == true
        dao.retrieveAnagrams(TEST_INPUT).size() == 12
        cleanup:
        // TODO: cleanup the database - remove the return stmt
        if (dao.anagramExists(TEST_INPUT)){
            dao.deleteAnagram(TEST_INPUT)
        }
    }
}

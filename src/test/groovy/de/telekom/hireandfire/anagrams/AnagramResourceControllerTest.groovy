package de.telekom.hireandfire.anagrams

import de.telekom.interviewexercise.InterviewExerciseApplication
import de.telekom.interviewexercise.hireandfire.anagrams.model.AnagramCheckResponse
import de.telekom.interviewexercise.hireandfire.anagrams.model.AnagramsResponse
import de.telekom.interviewexercise.hireandfire.anagrams.controllers.AnagramResourceController
import de.telekom.interviewexercise.hireandfire.randomstuff.EntityRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import javax.validation.ConstraintViolationException

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = InterviewExerciseApplication)
@ActiveProfiles("test")
class AnagramResourceControllerTest extends Specification {
    @Autowired(required = false)
    private AnagramResourceController restController

    @Autowired
    private EntityRepo repo

    def "all expected beans are created"() {
        expect: "the REST-Controller is created"
        restController
    }

    def "The given strings are not anagrams"(){
        // TODO implement this test
        given: String inputOne="One"
        String inputTwo="Two"
        when: ResponseEntity<AnagramCheckResponse> result = restController.checkStrings(inputOne, inputTwo)
        then:
        result.getStatusCodeValue().equals(200)
        result.getBody().getIsAnagram().equals(false)
    }

    def "The given strings are anagrams within the validation boundaries"(){
        given: String inputOne="tarun"
        String inputTwo="nurat"
        expect: "true should be returned"
        when: ResponseEntity<AnagramCheckResponse> result =  restController.checkStrings(inputOne, inputTwo)

        // TODO implement this test
        then:
        result.getStatusCodeValue().equals(200)
        result.getBody().getIsAnagram().equals(true)
    }

    def "The first of The given strings is two long"(){
        expect: "An exception is thrown"
        when:
        restController.checkStrings("Abcdefghijklmnop", "sett")
        then:
        thrown(ConstraintViolationException.class)
    }

    def "The second of The given strings is two long"(){
        expect: "An exception is thrown"
        when:
        restController.checkStrings("sett", "Abcdefghijklmnop")
        then:
        thrown(ConstraintViolationException.class)
    }

    def "When generating anagrams, the given string contains numbers"(){
        expect: "An exception is thrown"
        when:
        restController.fetchAnagramsForString("set245t")
        then:
        thrown(Exception.class)
    }

    def "Generate anagrams with valid input"(){
        expect: "Generated list of anagrams"
        when:
        AnagramsResponse response = restController.fetchAnagramsForString("FEUER",false)
        then:
        println "Anagrams list for input : " + "FEUER -->" + response.getAnagrams().toList()
        // TODO: implement the check - remove the return stmt
    }

}

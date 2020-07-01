package de.telekom.hireandfire

import de.telekom.interviewexercise.InterviewExerciseApplication
import de.telekom.interviewexercise.hireandfire.randomstuff.SomeEntity
import de.telekom.interviewexercise.hireandfire.randomstuff.SomeResource
import de.telekom.interviewexercise.hireandfire.randomstuff.EntityRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import de.telekom.interviewexercise.hireandfire.randomstuff.SomeResource.SomeDto
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = InterviewExerciseApplication)
@ActiveProfiles("test")
class InterviewExerciseApplicationTest extends Specification {
    @Autowired(required = false)
    private SomeResource restController

    @Autowired
    private EntityRepo repo

    def "all expected beans are created"() {
        expect: "the REST-Controller is created"
        restController
    }

    def "We get the desired response form SomeResource"(){
        expect: "The answer to everything in life"
        restController.getSomeDto().value == "42";
        restController.getSomeDto().name == "Meaning of life";
    }

    def "persist some entity into the db"(){
        setup: "Remove all existing items"
        repo.deleteAll()
        when: "when an entity is sent to the endpoint"
        SomeDto newDto = new SomeDto()
        newDto.name = "NewName"
        newDto.value = "NewValue"
        restController.addSomeDto(newDto)
        List<SomeEntity> result = repo.findAll()
        then: "The entity was saved"
        result.size() == 1
    }
}

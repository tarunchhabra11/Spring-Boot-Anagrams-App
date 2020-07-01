package de.telekom.interviewexercise.hireandfire.randomstuff;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1/someresource")
@Endpoint(id="custom")
@Api(tags = "Interview Questions Resource")
@Repository
public class SomeResource
{
    @ApiOperation("Fetches some attribute")
    @ApiResponse(code = 200, message = "OK", response = SomeDto.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    SomeDto getSomeDto(){
        SomeDto result = new SomeDto();
        result.name = "Meaning of life";
        result.value = "42";
        return result;
    }


    @ApiOperation("Creates a new attribute in the db")
    @ApiResponse(code = 201, message = "ACCEPTED", response = SomeDto.class)
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    SomeDto addSomeDto(@RequestBody SomeDto someDto){
        SomeEntity newEntity = new SomeEntity();
        newEntity.setName(someDto.name);
        newEntity.setValue(someDto.value);
        repo.save(newEntity);
        return someDto;
    }

    static int data = 42;

    @ReadOperation
    Integer getData(@Selector String name){
        return data;
    }

    @WriteOperation
    void updateData(@Selector String name,  int updated){
        data = updated;
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    public class SomeDto
    {
        private String name;
        private String value;
    }

    @Autowired
    private EntityRepo repo;
}

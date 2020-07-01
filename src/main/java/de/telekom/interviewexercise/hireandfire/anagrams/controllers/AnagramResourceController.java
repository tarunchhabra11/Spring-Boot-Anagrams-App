package de.telekom.interviewexercise.hireandfire.anagrams.controllers;

import de.telekom.interviewexercise.hireandfire.anagrams.dao.AnagramDao;
import de.telekom.interviewexercise.hireandfire.anagrams.exceptions.AnagramKeyNotFoundException;
import de.telekom.interviewexercise.hireandfire.anagrams.model.AnagramCheckResponse;
import de.telekom.interviewexercise.hireandfire.anagrams.model.AnagramsResponse;
import de.telekom.interviewexercise.hireandfire.anagrams.service.AnagramCheckerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/v1/anagrams")
@Api(tags = "A simple endpoint to manage strings and their anagrams")
public class AnagramResourceController {
    @Autowired
    private AnagramCheckerService anagramCheckerService;
    @Autowired
    private AnagramDao anagramDao;

    private static final String VALID_REGEX = "^[\\p{Alpha}]+$";

    @ApiResponse(code = 200, message = "OK", response = AnagramsResponse.class)
    @ApiOperation("Returns a json of all anagrams for the given input string")
    @GetMapping(value = "/{input}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AnagramsResponse fetchAnagramsForString(
        @PathVariable("input")
        @NotBlank(message = "Must not be empty")
        @Size(max = 10, min = 2, message = "Must be between 2 and 10 characters long")
        @Pattern(regexp = VALID_REGEX, message = "Must only contain alphabetic characters") String input,
        @RequestParam(name = "persist", defaultValue = "false") Boolean persist) {
        Set<String> result;
        if (anagramDao.anagramExists(input)) {
            result = anagramDao.retrieveAnagrams(input);
        } else {
            result = anagramCheckerService.generateAnagrams(input);
            if (persist) {
                anagramDao.persistAnagrams(result, input);
            }
        }
        return new AnagramsResponse(result);
    }

    @ApiOperation("Deletes all anagrams for the given string")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "NO CONTENT"),
        @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @DeleteMapping(value = "/{input}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteAnagramsForString(
        @ApiParam(value = "An input string to delete the anagrams for", name = "input", required = true, type = "string", example = "SomeText")
        @PathVariable("input")
        @NotBlank(message = "Must not be empty") @Size(max = 10, min = 2, message = "Must be between 2 and 10 characters long") @Pattern(regexp = VALID_REGEX, message = "Must only contain alphabetic characters") String input) {
        if (anagramDao.anagramExists(input)) {
            anagramDao.deleteAnagram(input);
            return ResponseEntity.noContent().build();
        } else {
            throw new AnagramKeyNotFoundException(input);
        }
    }

    @ApiOperation("Checks whether 2 strings are anagrams")
    @ApiResponse(code = 200, message = "OK")
    @GetMapping(value = "/check/{input1}/{input2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnagramCheckResponse> checkStrings(
        @ApiParam(value = "Input string-1 to check whether string-2 is its anagrams ", name = "input1", required = true, type = "string", example = "telekom")
        @PathVariable("input1")
        @NotBlank(message = "Must not be empty")
        @Size(max = 10, min = 2, message = "Must be between 2 and 10 characters long")
        @Pattern(regexp = VALID_REGEX, message = "Must only contain alphabetic characters") String input1,
        @ApiParam(value = "Input string-2 to check whether string-1 is its anagrams ", name = "input2", required = true, type = "string", example = "komtele")
        @PathVariable("input2") @NotBlank(message = "Must not be empty")
        @Size(max = 10, min = 2, message = "Must be between 2 and 10 characters long")
        @Pattern(regexp = VALID_REGEX, message = "Must only contain alphabetic characters") String input2) {

        return !anagramCheckerService.isAnagram(input1, input2) ? new ResponseEntity<>(new AnagramCheckResponse(false, null), HttpStatus.OK) : new ResponseEntity<>(new AnagramCheckResponse(true, anagramCheckerService.generateAnagrams(input1)), HttpStatus.OK);

    }
}

# Interview Exercise
Hello dear candidate,
we are very happy that you chose to apply for a position as Software Developer at DT Greece. To get a better understanding of how you can support us in our projects and check
weather you are a suitable candiate for the opening, we'd like you to complete the following exercises. 

## Intro
- Please adhere to the procedure outlined below
- Of course, any questions can be asked at any time
- You can use all means at your disposal to finish the tasks (Stackoverflow, google, literature etc.) 
- Once done, you submit a pull request to the repo with a branch name of your chosing, which will be reviewed by us
- Initially some of the tests will fail. If you finish the exercises below all tests should succeed

## Interview & Coding-Challenge Preparation of the participant
* Checkout the project to your local workspace
* Make sure the project is up an running
  * ```mvn clean install -DskipTests``` completes successfully
  * You can start the server (eg. using: ```mvn spring-boot:run -DskipTests)  ``` and access the swagger documentation (swagger-ui) 
* Carefully read the contents of this file and try to solve the tasks.

Note: The project should be running with Java 11 without any problems.

## Procedure during the interview
* Introduction and smalltalk
* Coding Challenge discussion of your solution
* Further discussion

## Challenge exercises:

### Prerequisites
* You should have Java installed and running on your computer
* You may use any IDE you prefer. Most DT folks work with IntelliJ 

### Exercise 1: 
Annotate the controller method ```fetchAnagramsForString``` in the ```AnagramResource``` controller. The method generates all anagrams
for a given String and returns the result in an ```AnagramsResponse```. The method is already implemented. 
The method is already implemented. You only have to add the required annotations for Spring-Rest and Swagger.

The request is implemented to: 

* accept an input string 
  * the string must be not longer than 10 characters
  * the string must be valid alphabetic strings without special characters
* accept a boolean query parameter "persist" with the default value false 
  * If persist is true, then all the anagrams are persisted to the H2 database.

You should:  

* make sure that the input is correctly validated.  
* Complete the implementation for the TODOs in the ```AnagramResourceTest``` class to test the methods.


#### Exercise 1.1:
In order to successfully complete Exercise 1 you have to implement the required methods in the ```SimpleAnagramChecker``` class.

* Implement the missing methods from the AnagramChecker interface.
  * The generation of anagrams is not vocabulary based but returns simple permutations of the input 
  * Two strings are considered anagrams if they are written using the same exact letters and length, ignoring space, punctuation, and capitalization
* The ```generateKey``` method should ensure that all anagrams for a given input string result in the same key

### Exercise 2: 
The original string (string 1 from Exercise 1) is written into the db

* All anagrams for the given string are calculated and stored in the db. You can use any db structure you want. An example is given with the ```Anagram``` object. 
* The ```AnagramDao``` contains a number of data access methods you should implement. 
* Complete the implementation for the TODOs in the ```AnagramDao``` class

### Exercise 3:     
Currently everything is cramped into the ```de.telekom.hireandfire.anagram``` package. 

* Refactor the project to reflect a more meaningful structure

### Submit your results
Submit your changes into a branch called ```{{name_of_your_choosing}}``` using a pull request 

DROP TABLE IF EXISTS ANAGRAMS;

CREATE TABLE ANAGRAM
(

    anagram_key             NVARCHAR(10) not null,
    ALL_ANAGRAMS            NVARCHAR(10) not null,
);

CREATE INDEX ANAGRAMS_KEY ON ANAGRAM (anagram_key);



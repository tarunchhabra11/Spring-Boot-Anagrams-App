package de.telekom.interviewexercise.hireandfire.anagrams.entity;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Component
@Table(name = "ANAGRAM")
public class Anagram
{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "input",nullable = false,unique = true)
    private String input;

    @Column(name = "Anagram_key",nullable = false,unique = true)
    private String key;

    @CollectionTable(name = "ALL_ANAGRAMS",joinColumns = @JoinColumn(name = "Anagram_id"))
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> value = new HashSet<>();

    public Anagram(String key,Set<String> value)
    {
        this.key = key;
        this.value = value;
    }
}

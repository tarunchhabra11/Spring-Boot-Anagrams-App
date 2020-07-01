package de.telekom.interviewexercise.hireandfire.randomstuff;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SOMEENTITY")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class SomeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name="name", columnDefinition = "VARCHAR(255)")
    private String name;
    @Column(name="value", columnDefinition = "VARCHAR(255)")
    private String value;
}

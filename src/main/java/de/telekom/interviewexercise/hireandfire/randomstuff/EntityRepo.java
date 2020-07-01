package de.telekom.interviewexercise.hireandfire.randomstuff;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepo extends PagingAndSortingRepository<SomeEntity, Long>
{

}

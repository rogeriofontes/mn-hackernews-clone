package br.com.everis.repository;

import br.com.everis.model.domain.Person;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Override
    List<Person> findAll();
    Slice<Person> list(Pageable pageable);
    //Pagination
    List<Person> findByAgeGreaterThan(int oge, Pageable pageable);
    //Ordering
    List<Person> listOrderByAgeDesc();
    List<Person> listOrderByAgeAsc();
    //Filter & Ordering
    List<Person> findByAgeGreaterThanOrderByAgeAsc(int age);
}

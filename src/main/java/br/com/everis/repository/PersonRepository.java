package br.com.everis.repository;

import br.com.everis.model.domain.Person;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import io.micronaut.data.repository.CrudRepository;
import org.hibernate.dialect.Dialect;

import java.util.List;
import java.util.Optional;

@Repository
//@JdbcRepository(dialect= Dialect.POSTGRES)
public interface PersonRepository extends CrudRepository<Person, Long>  {

    @Override
    List<Person> findAll();

    Slice<Person> list(Pageable pageable);

    Optional<Person> findByAgeGreaterThan(int age);

    // Pagination
    List<Person> findByAgeGreaterThan(int age, Pageable pageable);

    // Ordering
    List<Person> listOrderByAgeDesc();

    List<Person> listOrderByAgeAsc();

    // Filter & Ordering
    List<Person> findByAgeGreaterThanOrderByAgeAsc(int age);




 }

package br.com.resende.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.resende.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}

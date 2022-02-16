package br.com.resende.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.resende.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	@Modifying
	@Query("UPDATE Person p SET p.enabled = 0 WHERE p.id =:id")
	void disablePersons(@Param("id") Long id);
	
	@Query("SELECT p FROM Person p WHERE p.firstName LIKE LOWER(CONCAT ('%', :firstName, '%')) ")
	Page<Person> findPersonByName(@Param("firstName") String firstName, Pageable pageable);
	
	//void disabelPersons(@Param("id") Long id);
	//"UPDATE Person p SET p.enabled =  false WHERE p.id =: id"
	
	/*@Query("SELECT p FROM Person p WHERE p.firstName =:firstName ")
	Person findByPersonName(@Param("firstName") String firstName);
	
	@Modifying
	@Query("UPDATE Person p SET p.firstName = :firstName WHERE p.id =:id")
	void updateFirstNamePersonsById(@Param("firstName") String firstName, @Param("id") Long id);*/
	
}

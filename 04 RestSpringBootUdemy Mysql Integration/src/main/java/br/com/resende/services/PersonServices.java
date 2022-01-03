package br.com.resende.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resende.exception.ResourceNotFoundException;
import br.com.resende.model.Person;
import br.com.resende.repository.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;
	
	public Person create(Person person) {
		
		return repository.save(person);
		
	}
	
	public List<Person> findAll() {

		return repository.findAll();
		
	}
	
	public Person findByID(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
	}
	
	public Person update(Person person) {
		
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		entity.setFistName(person.getFistName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		repository.save(entity);
		
		return entity;
		
	}
	
	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		repository.delete(entity);
		
		return ;
	}
	
	
	
	/*private Person mockPerson(int i) {
	Person person = new Person();
	person.setId(counter.incrementAndGet());
	person.setFistName("Person Name" + i);
	person.setLastName("Last Name" + i);
	person.setAddress("Some address in Brasil" + i);
	person.setGender("Male");
	return person;
}*/

	

}

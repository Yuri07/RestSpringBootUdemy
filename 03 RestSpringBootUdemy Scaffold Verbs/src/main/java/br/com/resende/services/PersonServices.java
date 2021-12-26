package br.com.resende.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.resende.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	
	public Person create(Person person) {
		
		//codigo para salvar no banco de dados
		
		return person;
		
	}
	
	public Person update(Person person) {
		
		//codigo para atualizar registro no banco de dados
		
		return person;
		
	}
	
	public void delete(String id) {
		//se id existe no banco de dados deleta
		return ;
	}
	
	public Person findByID(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFistName("Yuri");
		person.setLastName("Resende");
		person.setAddress("Caucaia - CE, Brasil");
		person.setGender("Male");
		return person;
	}
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
			
		}
		return persons;
		
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFistName("Person Name" + i);
		person.setLastName("Last Name" + i);
		person.setAddress("Some address in Brasil" + i);
		person.setGender("Male");
		return person;
	}

}

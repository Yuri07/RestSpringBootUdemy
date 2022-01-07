package br.com.resende.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resende.converter.DozerConverter;
import br.com.resende.data.model.Person;
import br.com.resende.data.vo.PersonVO;
import br.com.resende.exception.ResourceNotFoundException;
import br.com.resende.repository.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);//(var aqui é igual a Person)
		var vo = DozerConverter
				.parseObject(repository.save(entity), PersonVO.class);//(var aqui é igual a PersonVO)
		return vo;
		
	}
	
	public List<PersonVO> findAll() {

		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
		
	}
	
	public PersonVO findByID(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) {
		
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		repository.save(entity);
		
		return DozerConverter.parseObject( entity, PersonVO.class );
		
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

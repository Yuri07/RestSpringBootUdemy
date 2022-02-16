package br.com.resende.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.resende.converter.DozerConverter;
import br.com.resende.data.model.Person;
import br.com.resende.data.vo.v1.PersonVO;
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
	
	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {

		var page = repository.findPersonByName(firstName, pageable);
		
		return page.map(this::convertToPersonVO);
		
		
	}
	
	public Page<PersonVO> findAll(Pageable pageable) {

		var page = repository.findAll(pageable);
		
		return page.map(this::convertToPersonVO);
		
		
	}
	
	private PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
		
	}
	
	/*public List<PersonVO> findAll(Pageable pageable) {

		var entities = repository.findAll(pageable).getContent();
		
		return DozerConverter.parseListObjects(entities, PersonVO.class);
		
		
		
		//return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);//sem paginacao
		
	}*/
	
	public PersonVO findByID(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) {
		
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		repository.save(entity);
		
		return DozerConverter.parseObject( entity, PersonVO.class );
		
	}
	
	//(garantir a consistencia no banco de dados
					//operacao costomizada em UserRepository, entao temos que colocar @Transactional)
	@Transactional
	public PersonVO disablePersons(Long id) {
		repository.disablePersons(id);
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		return DozerConverter.parseObject(entity, PersonVO.class);
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

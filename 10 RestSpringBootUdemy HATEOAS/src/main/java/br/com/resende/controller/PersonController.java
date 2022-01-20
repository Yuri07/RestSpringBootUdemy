package br.com.resende.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.resende.data.vo.v1.PersonVO;
import br.com.resende.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonServices services;
	
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<PersonVO> findAll() {
	
		List<PersonVO> personsVO = services.findAll();
		personsVO
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(PersonController.class).findByID(p.getKey())).withSelfRel()
				)
			);
		return personsVO;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findByID(@PathVariable("id") Long id) {
		
		PersonVO personVO =  services.findByID(id);
		personVO.add(linkTo(methodOn(PersonController.class).findByID(id)).withSelfRel());
		return personVO;
	}
	
	
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
				consumes= {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO create(@RequestBody PersonVO person) {

		PersonVO personVO = services.create(person);
		
		personVO.add(linkTo(methodOn(PersonController.class).findByID(personVO.getKey())).withSelfRel());
		
		return personVO;
	}
	
	
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person) {

		PersonVO personVO = services.update(person);
		
		personVO.add(linkTo(methodOn(PersonController.class).findByID(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {//retorna apenas status 200 ok no postman
	
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}


/* Forma mais mais explicita de declarar os metodos do controller 
@RequestMapping(method=RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() {
	
		return services.findAll();
	}
	
	@RequestMapping(value="/{id}", 
					method=RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findByID(@PathVariable("id") Long id) {
		
		return services.findByID(id);
	}
	
	
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person create(@RequestBody Person person) {

		return services.create(person);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person update(@RequestBody Person person) {

		return services.create(person);
	}
	
	@RequestMapping(value="/{id}", 
			method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {//retorna apenas status 200 ok no postman
	
		services.delete(id);
	}
	*/

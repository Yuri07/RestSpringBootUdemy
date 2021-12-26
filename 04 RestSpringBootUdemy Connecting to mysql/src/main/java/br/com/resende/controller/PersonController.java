package br.com.resende.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.resende.model.Person;
import br.com.resende.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices services;
	
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

}

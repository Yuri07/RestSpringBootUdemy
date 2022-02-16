package br.com.resende.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.resende.data.vo.v1.BooksVO;
import br.com.resende.data.vo.v1.PersonVO;
import br.com.resende.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin
@Api(value = "Person Endpoint", description = "Description for person", tags = {"PersonEndpoint"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonServices services;
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;
	
	//{{baseUrl}}/api/person/v1?page=0&limit=10&direction=asc ou direction=desc //{{baseUrl}}/api/person/v1?page=40&limit=10&direction=asc
	@ApiOperation(value = "Find all people recorded")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(									//para remover warnings(injetar melho o PagedResourceAssembler)
	//public ResponseEntity<PagedResources<PersonVO>> findAll(
	//public List<PersonVO> findAll(
									@RequestParam(value="page", defaultValue = "0") int page,
									@RequestParam(value="limit", defaultValue = "12") int limit,
									@RequestParam(value="direction", defaultValue = "asc") String direction) {
									//,PagedResourcesAssembler assembler){
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
	
		Page<PersonVO> persons= services.findAll(pageable);
		//List<PersonVO> personsVO = services.findAll(pageable);
		persons
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(PersonController.class).findByID(p.getKey())).withSelfRel()
				)
			);
		
		PagedResources<?> resources = assembler.toResource(persons);
		
		//return personsVO;
		//return new ResponseEntity<>(assembler.toResource(persons),HttpStatus.OK);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
		//{{baseUrl}}/api/person/v1/findPersonByName/ab?page=0&limit=10&direction=asc
		@ApiOperation(value = "Find all people recorded with FistName Like Path Paramn")
		@GetMapping(value = "/findPersonByName/{firstName}", produces = {"a"
				+ "pplication/json", "application/xml", "application/x-yaml"})
		public ResponseEntity<?> findPersonByName(
		//public ResponseEntity<PagedResources<PersonVO>> findPersonByName(
										@PathVariable("firstName") String firstName,
										@RequestParam(value="page", defaultValue = "0") int page,
										@RequestParam(value="limit", defaultValue = "12") int limit,
										@RequestParam(value="direction", defaultValue = "asc") String direction){
										//,PagedResourcesAssembler assembler) {
			
			var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
			Page<PersonVO> persons= services.findPersonByName(firstName, pageable);
			persons
				.stream()
				.forEach(p -> p.add(
						linkTo(methodOn(PersonController.class).findByID(p.getKey())).withSelfRel()
					)
				);
			//return new ResponseEntity<>(assembler.toResource(persons),HttpStatus.OK);

			PagedResources<?> resources = assembler.toResource(persons);

			return new ResponseEntity<>(resources, HttpStatus.OK);
		}
	
	//@CrossOrigin(origins = "http;//localhost:8080")//permitir acesso a esse metodo somente a local host
	@ApiOperation(value = "Find one people recorded by Id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findByID(@PathVariable("id") Long id) {
		
		PersonVO personVO =  services.findByID(id);
		personVO.add(linkTo(methodOn(PersonController.class).findByID(id)).withSelfRel());
		return personVO;
	}
	
	//@CrossOrigin(origins = {"http;//localhost:8080", "http://www.erudio.com.br"})
	@ApiOperation(value = "Create one people record")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
				consumes= {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO create(@RequestBody PersonVO person) {

		PersonVO personVO = services.create(person);
		
		personVO.add(linkTo(methodOn(PersonController.class).findByID(personVO.getKey())).withSelfRel());
		
		return personVO;
	}
	
	
	@ApiOperation(value = "Update one people recorded")
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person) {

		PersonVO personVO = services.update(person);
		
		personVO.add(linkTo(methodOn(PersonController.class).findByID(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Disable a especific person recorded by Id")
	@PatchMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO disablePersons(@PathVariable("id") Long id) {
		
		PersonVO personVO =  services.disablePersons(id);
		//PersonVO personVO =  services.findByID(id);
		personVO.add(linkTo(methodOn(PersonController.class).findByID(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "Delete one people recorded")
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

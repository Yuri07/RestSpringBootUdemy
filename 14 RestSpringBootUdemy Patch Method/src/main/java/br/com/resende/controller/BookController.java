package br.com.resende.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.resende.data.vo.v1.BooksVO;
import br.com.resende.data.vo.v1.PersonVO;
import br.com.resende.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book Endpoint", description = "Description for book", tags = {"BookEndpoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	private BookServices services;
	
	//http://localhost:8080/api/book/v1?page=0&limit=10&direction=desc	(http://localhost:8080/auth/signin  [body paramns : {    "username": "{{user}}","password": "{{password}}" }])
	@ApiOperation(value = "Find all book recorded")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<PagedResources<BooksVO>> findAll(
	//public List<BooksVO> findAll() {
									@RequestParam(value="page", defaultValue = "0") int page,
									@RequestParam(value="limit", defaultValue = "12") int limit,
									@RequestParam(value="direction", defaultValue = "asc") String direction,
									PagedResourcesAssembler assembler){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
	
		Page<BooksVO> books = services.findAll(pageable);
		
		//List<BooksVO> booksVO = services.findAll();
		books
			.stream()
			.forEach(b -> b.add(
					linkTo(methodOn(BookController.class).findByID(b.getKey())).withSelfRel()
				)
			);
		//return books;
		return new ResponseEntity<>(assembler.toResource(books),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find one book recorded by Id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BooksVO findByID(@PathVariable("id") Long id) {
		
		BooksVO bookVO =  services.findByID(id);
		bookVO.add(linkTo(methodOn(BookController.class).findByID(id)).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Create one book record")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
				consumes= {"application/json", "application/xml", "application/x-yaml"})
	public BooksVO create(@RequestBody BooksVO book) {

		BooksVO bookVO = services.create(book);
		
		bookVO.add(linkTo(methodOn(BookController.class).findByID(bookVO.getKey())).withSelfRel());
		
		return bookVO;
	}
	
	@ApiOperation(value = "Update one book record")
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BooksVO update(@RequestBody BooksVO book) {

		BooksVO bookVO = services.update(book);
		
		bookVO.add(linkTo(methodOn(BookController.class).findByID(bookVO.getKey())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "Delete one book record")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {//retorna apenas status 200 ok no postman
	
		services.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
}

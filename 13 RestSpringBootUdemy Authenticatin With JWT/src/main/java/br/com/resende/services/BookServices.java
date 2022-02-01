package br.com.resende.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resende.converter.DozerConverter;
import br.com.resende.data.model.Books;
import br.com.resende.data.model.Person;
import br.com.resende.data.vo.v1.BooksVO;
import br.com.resende.data.vo.v1.PersonVO;
import br.com.resende.exception.ResourceNotFoundException;
import br.com.resende.repository.BooksRepository;

@Service
public class BookServices {
	
	@Autowired
	BooksRepository repository;
	
	public BooksVO create(BooksVO books) {
		var entity = DozerConverter.parseObject(books, Books.class);//(var aqui é igual a Books)
		var vo = DozerConverter
				.parseObject(repository.save(entity), BooksVO.class);//(var aqui é igual a BooksVO)
		return vo;
		
	}
	
	public List<BooksVO> findAll() {

		return DozerConverter.parseListObjects(repository.findAll(), BooksVO.class);
		
	}
	
	public BooksVO findByID(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		return DozerConverter.parseObject(entity, BooksVO.class);
	}
	
	public BooksVO update(BooksVO books) {
		
		Books entity = repository.findById(books.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		entity.setAuthor(books.getAuthor());
		entity.setLaunchDate(books.getLaunchDate());
		entity.setPrice(books.getPrice());
		entity.setTitle(books.getTitle());
		
		repository.save(entity);
		
		return DozerConverter.parseObject( entity, BooksVO.class );
		
	}
	
	public void delete(Long id) {
		Books entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID"));
		
		repository.delete(entity);
		
		return ;
	}

}

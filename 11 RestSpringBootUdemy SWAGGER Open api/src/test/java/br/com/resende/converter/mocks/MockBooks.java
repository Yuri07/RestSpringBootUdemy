package br.com.resende.converter.mocks;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.resende.data.model.Books;
import br.com.resende.data.model.Person;
import br.com.resende.data.vo.v1.BooksVO;
import br.com.resende.data.vo.v1.PersonVO;

public class MockBooks {

	public Books mockEntity() {
    	return mockEntity(0);
    }
    
    public BooksVO mockVO() {
    	return mockVO(0);
    }
    
    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BooksVO> mockVOList() {
        List<BooksVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    private Books mockEntity(Integer number) {
    	Books book = new Books();
    	book.setAuthor("Author Test" + number);
    	Date date  = new Date(1, number, 1);
    	book.setLaunchDate(date);
    	book.setPrice(number + 0.1);
    	book.setId(number.longValue());
    	book.setTitle("Title Test" + number);
        return book;
    }

    private BooksVO mockVO(Integer number) {
    	BooksVO book= new BooksVO();
    	book.setAuthor("Author Test" + number);
    	Date date  = new Date(1, number, 1);
    	book.setLaunchDate(date);
    	book.setPrice(number + 0.1);
    	book.setKey(number.longValue());
    	book.setTitle("Title Test" + number);
        return book;
    }
	
}

package br.com.resende.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.resende.data.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

}

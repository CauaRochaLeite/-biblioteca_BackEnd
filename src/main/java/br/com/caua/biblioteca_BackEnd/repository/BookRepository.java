package br.com.caua.biblioteca_BackEnd.repository;

import br.com.caua.biblioteca_BackEnd.model.BookModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    public Page<BookModel> findAll(Pageable pageable);

}
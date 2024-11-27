package br.com.caua.biblioteca_BackEnd.service;

import br.com.caua.biblioteca_BackEnd.dto.BookDto;
import br.com.caua.biblioteca_BackEnd.exceptions.ResourceNotFoundException;
import br.com.caua.biblioteca_BackEnd.mapper.CustomModelMapper;
import br.com.caua.biblioteca_BackEnd.model.AuthorModel;
import br.com.caua.biblioteca_BackEnd.model.BookModel;
import br.com.caua.biblioteca_BackEnd.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public BookDto create(BookDto bookDto){
        BookModel bookModel = CustomModelMapper.parseObject(bookDto, BookModel.class);
        return CustomModelMapper.parseObject(repository.save(bookModel), BookDto.class);
    }

    public BookDto findById(long id){
        BookModel bookModel = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Livro não encontrado!"));
        return CustomModelMapper.parseObject(bookModel, BookDto.class);
    }

    public BookDto update(BookDto bookDto){
        BookModel bookModel = repository.findById(bookDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Livro não encontrado!")
        );
        bookModel.setTitle(bookDto.getTitle());
        bookModel.setPublicationDate(bookDto.getPublicationDate());
        bookModel.setAuthor(CustomModelMapper.parseObject(bookDto.getAuthor(), AuthorModel.class));
        return CustomModelMapper.parseObject(repository.save(bookModel), BookDto.class);
    }

    public void delete(long id){
        BookModel bookModel = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Livro não encontrado!")
        );
        repository.delete(bookModel);
    }

    public Page<BookDto> findAll(Pageable pageable){
        var books = repository.findAll(pageable);
        return books.map( c -> CustomModelMapper.parseObject(c, BookDto.class));
    }

}
package br.com.caua.biblioteca_BackEnd.controller;

import br.com.caua.biblioteca_BackEnd.dto.BookDto;
import br.com.caua.biblioteca_BackEnd.exceptions.CustomExceptionResponse;
import br.com.caua.biblioteca_BackEnd.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Books", description = "Endpoint usado para operações que envolvem books")
@RestController
@RequestMapping("/api/books")

public class BookController {

    @Autowired
    private BookService service;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cria ou cadastra um novo Book (Livro)", tags = {"Book"},responses = {
            @ApiResponse(description = "CREATED", responseCode = "201", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BookDto.class)
            )}),
    })
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto){
        BookDto book = service.create(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }



    @Operation(summary = "Recuperar um Book (ou Livro) mediante um ID informado", tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Book recuperado com sucesso!", responseCode = "200", content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class)
                    )}),
                    @ApiResponse(description = "Resource not found", responseCode = "404", content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomExceptionResponse.class)
                    )})
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable(name = "id") long id){
        BookDto book = service.findById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto){
        BookDto bookUpdated = service.update(bookDto);
        return new ResponseEntity<>(bookUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") long id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<BookDto> assembler
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
        Page<BookDto> books = service.findAll(pageable);
        return new ResponseEntity(assembler.toModel(books), HttpStatus.OK);

    }

}
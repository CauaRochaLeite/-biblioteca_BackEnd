package br.com.caua.biblioteca_BackEnd.controller;

import br.com.caua.biblioteca_BackEnd.dto.AuthorDto;
import br.com.caua.biblioteca_BackEnd.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto){
        AuthorDto author = service.create(authorDto);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable(name = "id") long id){
        AuthorDto author = service.findById(id);
        this.buildSelfLink(author);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AuthorDto> update(@RequestBody AuthorDto authorDto){
        AuthorDto author = service.update(authorDto);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") long id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<AuthorDto>> findAll(){
        CollectionModel<AuthorDto> authors  = CollectionModel.of(service.findAll());
        for(AuthorDto author : authors){
            buildSelfLink(author);
        }
        this.buildCollectionLink(authors);
        return new ResponseEntity<CollectionModel<AuthorDto>>(authors, HttpStatus.OK);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<AuthorDto>> findByName(@PathVariable(name = "name") String name){
        var authors = service.findByName(name);
        return new ResponseEntity<List<AuthorDto>>(authors, HttpStatus.OK);
    }

    @GetMapping("/find/nationality/{nationality}")
    public ResponseEntity<List<AuthorDto>> findByNationality(@PathVariable(name = "nationality") String nationality){
        var authors = service.findByNatinality(nationality);
        return new ResponseEntity<List<AuthorDto>>(authors, HttpStatus.OK);
    }

    private void buildSelfLink(AuthorDto authorDto){
        //self link
        authorDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findById(authorDto.getId())
                ).withSelfRel()
        );
    }

    public void buildCollectionLink(CollectionModel<AuthorDto> authors){
        authors.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findAll()
                ).withRel(LinkRelation.of("COLLECTION"))
        );
    }


}
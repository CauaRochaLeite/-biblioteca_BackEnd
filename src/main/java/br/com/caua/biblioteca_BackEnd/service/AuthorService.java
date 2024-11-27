package br.com.caua.biblioteca_BackEnd.service;

import br.com.caua.biblioteca_BackEnd.dto.AuthorDto;
import br.com.caua.biblioteca_BackEnd.exceptions.ResourceNotFoundException;
import br.com.caua.biblioteca_BackEnd.mapper.CustomModelMapper;
import br.com.caua.biblioteca_BackEnd.model.AuthorModel;
import br.com.caua.biblioteca_BackEnd.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public AuthorDto create(AuthorDto authorDto){
        AuthorModel author = CustomModelMapper.parseObject(authorDto, AuthorModel.class);
        return CustomModelMapper.parseObject(repository.save(author), AuthorDto.class);
    }

    public AuthorDto findById(long id){
        AuthorModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Autor não encontrada")
        );
        return CustomModelMapper.parseObject(found, AuthorDto.class);
    }

    public AuthorDto update(AuthorDto authorDto){
        AuthorModel found = repository.findById(authorDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Autor não encontrada"));
        found.setName(authorDto.getName());
        found.setNationality(authorDto.getNationality());
        return CustomModelMapper.parseObject(repository.save(found), AuthorDto.class);
    }

    public void delete(long id){
        AuthorModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Autor não encontrada"));
        repository.delete(found);
    }

    public List<AuthorDto> findAll(){
        var list = repository.findAll();
        return CustomModelMapper.parseObjectList(list, AuthorDto.class);
    }

    public List<AuthorDto> findByName(String name){
        var authors = repository.findByNameContainsIgnoreCaseOrderByName(name);
        return CustomModelMapper.parseObjectList(authors, AuthorDto.class);
    }

    public List<AuthorDto> findByNatinality(String nationality){
        var authors = repository.findByNationalityEqualsIgnoreCaseOrderByNationalityAscNameAsc(nationality);
        return CustomModelMapper.parseObjectList(authors, AuthorDto.class);
    }



}

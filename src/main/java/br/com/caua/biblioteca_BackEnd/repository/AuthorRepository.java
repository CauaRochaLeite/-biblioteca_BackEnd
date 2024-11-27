package br.com.caua.biblioteca_BackEnd.repository;

import br.com.caua.biblioteca_BackEnd.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

    public List<AuthorModel> findByNameContainsIgnoreCaseOrderByName(String name);

    public List<AuthorModel> findByNationalityEqualsIgnoreCaseOrderByNationalityAscNameAsc(String nationality);

}
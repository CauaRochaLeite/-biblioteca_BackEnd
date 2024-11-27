package br.com.caua.biblioteca_BackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorDto extends RepresentationModel<AuthorDto> {

    private long id;
    private String name;
    private String nationality;

    public String getNameNationality(){
        return name + "/" + nationality;
    }

}
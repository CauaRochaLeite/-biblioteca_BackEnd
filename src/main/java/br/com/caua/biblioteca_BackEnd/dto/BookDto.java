package br.com.caua.biblioteca_BackEnd.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDto {

    private long id;

    private String title;

    private Date publicationDate;

    private AuthorDto author;

}
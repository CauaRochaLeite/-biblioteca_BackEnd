package br.com.caua.biblioteca_BackEnd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String nationality;

}

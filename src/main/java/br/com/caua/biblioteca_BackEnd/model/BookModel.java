package br.com.caua.biblioteca_BackEnd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "publication_date", nullable = false)
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorModel author;


}

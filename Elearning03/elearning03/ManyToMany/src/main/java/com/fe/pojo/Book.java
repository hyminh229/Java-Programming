package com.fe.pojo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    private Long id;

    @Column(name ="title", length = 30)
    private String title;

    private String author;

    private String isbn;

    @ManyToMany(mappedBy = "books")
    private Set<Student> student = new HashSet<Student>();

    public Book(){
        super();
    }
    public Book(String title, String author, String isbn){
        super();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
}
package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name="book_category")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
    //@JsonIgnore
    @Fetch(FetchMode.JOIN)
    private Set<Book> books;

    public BookCategory(String name, Book... books) {
        this.name = name;
        this.books = Stream.of(books).collect(Collectors.toSet());
        this.books.forEach(x -> x.setBookCategory(this));
    }
    public BookCategory(){}

    public BookCategory(int id, String name, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}

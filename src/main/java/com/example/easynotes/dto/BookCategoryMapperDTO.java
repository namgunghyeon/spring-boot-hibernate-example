package com.example.easynotes.dto;

import com.example.easynotes.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookCategoryMapperDTO {
    private int id;
    private String name;
    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = new ArrayList<>(books.stream().map(book -> new Book(book.getId(), book.getName())).collect(Collectors.toList()));;
    }

}

package com.example.easynotes.dto;

import com.example.easynotes.model.Book;
import com.example.easynotes.model.BookCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookCategoryDTO {
    private int id;
    private String name;
    private List<Book> books;

    public BookCategoryDTO(BookCategory bookCategory) {
        this.name = bookCategory.getName();
        this.books = new ArrayList<>(bookCategory.getBooks().stream().map(book -> new Book(book.getId(), book.getName())).collect(Collectors.toList()));
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

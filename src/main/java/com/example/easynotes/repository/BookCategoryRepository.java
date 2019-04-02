package com.example.easynotes.repository;


import com.example.easynotes.model.BookCategory;
import com.example.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
    @Query("select a from BookCategory a join fetch a.books")
    List<BookCategory> findAllJoinFetch();
}

package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface NoteCustomRepository {
    @Query("select c from Note c where c.id = :id")
    public Note findTest(@Param("id") Long id);

}

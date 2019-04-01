package com.example.easynotes.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CustomNoteRepositoryImpl implements CustomNoteRepository {

    @Override
    public void print() {
        System.out.println("CustomNoteRepositoryImpl print");
    }
}

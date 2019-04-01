package com.example.easynotes.validate;

import com.example.easynotes.error.InvalidParamter;
import com.example.easynotes.model.Note;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;

@Component
public class NoteValidator {
    public void validate(Note note) {
        if (note.getTitle().length() > 140) {
            throw new InvalidParamter("Too long title");
        }
    }
}




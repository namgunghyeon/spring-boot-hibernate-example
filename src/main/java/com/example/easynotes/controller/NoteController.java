package com.example.easynotes.controller;

import com.example.easynotes.dto.BookCategoryDTO;
import com.example.easynotes.dto.BookCategoryMapperDTO;
import com.example.easynotes.error.NotFoundError;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Book;
import com.example.easynotes.model.BookCategory;
import com.example.easynotes.model.Note;
import com.example.easynotes.model.Pencil;
import com.example.easynotes.repository.*;
import com.example.easynotes.validate.NoteValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    PencilRepository pencilRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Autowired
    NoteValidator noteValidator;

    @Autowired
    private ModelMapper modelMapper;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



    @GetMapping("/notes")
    public List<Note> getAllNotes(Pageable page) {
        Page<Note> result = noteRepository.findAll(page);
        noteRepository.print();

        return result.getContent();
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        noteValidator.validate(note);

        Pencil pencil = new Pencil();
        pencil.setTitle("연필");

        note.addPencil(pencil);

        return noteRepository.save(note);
    }

    @PostMapping("/bookCategory")
    public BookCategoryMapperDTO createBookcategory(@Valid @RequestBody Note note) {
        BookCategory  bookCategory = bookCategoryRepository.
                save(new BookCategory("Category 1", new Book("Hello Koding 1"), new Book("Hello Koding 2")));

        System.out.println(bookCategory.getBooks().size());
        BookCategoryMapperDTO bookCategoryMapperDTO = modelMapper.map(bookCategory, BookCategoryMapperDTO.class);

        return bookCategoryMapperDTO;
    }

    @GetMapping("/bookCategory")
    public List<BookCategoryDTO> getBookcategory(@Valid @RequestBody Note note) {
        List<BookCategory> bookCategories = bookCategoryRepository.findAll();

        return bookCategories.stream().map(bookCategory -> new BookCategoryDTO(bookCategory)).collect(Collectors.toList());

    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/error1")
    public List<Note> error1() {
        throw new NotFoundError("error advice handler");
    }

    @GetMapping("/error2")
    @ExceptionHandler(value = IllegalArgumentException.class)
    public List<Note> error2() {
        throw new NotFoundError("error ExceptionHandler");
    }

    @GetMapping("/error3")
    public List<Note> error3() throws IllegalArgumentException {
        throw new IllegalArgumentException("error IllegalArgumentException");
    }

    @GetMapping("/error4")
    public List<Note> error4() throws Exception {
        throw new Exception("error Exception");
    }
}

package com.example.easynotes.controller;

import com.example.easynotes.error.NotFoundError;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;


    //@Autowired
    //public NoteController(NoteRepository noteRepository) {
    //    this.noteRepository = noteRepository;
    //}


    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        //System.out.println(noteRepository.findTest(new Long(1)).getTitle());
        //noteRepository.findTest2("123");
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
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

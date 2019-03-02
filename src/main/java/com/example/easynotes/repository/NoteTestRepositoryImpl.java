package com.example.easynotes.repository;


import com.example.easynotes.model.Note;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import java.util.List;


public class NoteTestRepositoryImpl implements NoteTestRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory(@Autowired EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return factory.unwrap(SessionFactory.class);
    }


    @Override
    public void findTest2(String id) {
        SessionFactory sessionFactory = this.sessionFactory(entityManagerFactory);
        List<Note> notes = sessionFactory.openSession().createQuery("select n from Note as n").getResultList();

        System.out.println(notes.size());
        notes.forEach(note -> System.out.println(note.getTitle()));

        System.out.println("find test 2");
    }
}


package com.example.easynotes;

import com.example.easynotes.dto.BookCategoryMapperDTO;
import com.example.easynotes.model.Book;
import com.example.easynotes.model.BookCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DTOTests {

    @Autowired
    private ModelMapper modelMapper;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Test
    public void bookCategoryModelMapperTest() {
        BookCategory bookCategory = mock(BookCategory.class);
        Set<Book> books = Stream.of(new Book("Hello Koding 1"), new Book("Hello Koding 2")).collect(Collectors.toSet());

        when(bookCategory.getId()).thenReturn(1);
        when(bookCategory.getName()).thenReturn("programming");
        when(bookCategory.getBooks()).thenReturn(books);


        BookCategoryMapperDTO bookCategoryMapperDTO = modelMapper.map(bookCategory, BookCategoryMapperDTO.class);

        Assert.assertEquals("name",  "programming", bookCategoryMapperDTO.getName());
        Assert.assertEquals("book size",  2, bookCategoryMapperDTO.getBooks().size());
    }
}

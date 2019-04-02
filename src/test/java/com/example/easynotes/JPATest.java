package com.example.easynotes;

import com.example.easynotes.model.Book;
import com.example.easynotes.model.BookCategory;
import com.example.easynotes.model.Member;
import com.example.easynotes.model.Order;
import com.example.easynotes.repository.BookCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Test
    public void bookCategory() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setName("book category");

        Book book1 = new Book();
        book1.setName("book1");
        book1.setBookCategory(bookCategory);

        bookCategory.setBooks(new HashSet<>(Arrays.asList(book1)));

        Assert.assertEquals("Check Book size",1, bookCategory.getBooks().size());
        bookCategoryRepository.save(bookCategory);

        List<BookCategory> bookCategories = bookCategoryRepository.findAllJoinFetch();
        Assert.assertEquals("Check Inserted bookCategory size",1, bookCategories.size());
        Assert.assertEquals("Check Inserted book size",1, bookCategories.get(0).getBooks().size());
    }

    @Test
    public void orders() {
        Member member = new Member();
        member.setName("member");

        Order order = new Order();
        order.setMember(member);

        Assert.assertEquals("Check Order size",1, member.getOrders().size());
    }
}

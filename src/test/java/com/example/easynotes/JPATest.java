package com.example.easynotes;

import com.example.easynotes.model.Book;
import com.example.easynotes.model.BookCategory;
import com.example.easynotes.model.Member;
import com.example.easynotes.model.Order;
import com.example.easynotes.repository.BookCategoryRepository;
import com.example.easynotes.repository.MemberRepository;
import com.example.easynotes.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

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
    public void member() {
        Order order = new Order();
        Member member = new Member();
        member.setName("member1");

        order.setMember(member);

        Assert.assertEquals("Check Order size",1, member.getOrders().size());

        memberRepository.save(member);

        List<Member> members = memberRepository.findAll();
        Assert.assertEquals("Check Inserted member size",1, members.size());
        Assert.assertEquals("Check Inserted order size",1, members.get(0).getOrders().size());

        memberRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    @Transactional
    public void order() {
        Order order = new Order();
        Member newMember = new Member();
        newMember.setName("member2");

        memberRepository.save(newMember);
        List<Member> members = memberRepository.findAll();
        Assert.assertEquals("Check Inserted member size",1, members.size());

        order.setMember(members.get(0));

        orderRepository.save(order);
        List<Order> orders = orderRepository.findAll();

        Assert.assertEquals("Check Inserted order size",1, orders.size());
        Assert.assertEquals("Check Inserted member name","member2", orders.get(0).getMember().getName());
    }
}

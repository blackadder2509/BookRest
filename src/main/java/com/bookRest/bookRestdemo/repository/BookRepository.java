package com.bookRest.bookRestdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookRest.bookRestdemo.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByTitleContainingOrDescriptionContaining(String searchTerm, String searchTerm2);

	
}

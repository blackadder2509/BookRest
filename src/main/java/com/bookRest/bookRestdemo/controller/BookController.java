package com.bookRest.bookRestdemo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookRest.bookRestdemo.entities.Book;
import com.bookRest.bookRestdemo.repository.BookRepository;

@RestController
public class BookController {
	@Autowired
	BookRepository bookRepo;
	
	@GetMapping("/books")
	public List<Book>index(){
		return bookRepo.findAll();
	}
	@GetMapping("/books/{id}")
	public Book show(@PathVariable int id) {
		return bookRepo.findById(id).get();
	}
	@PostMapping("/books/search")
	public List<Book>search(@RequestBody Map<String, String>body){
		String searchTerm = body.get("text");
		return bookRepo.findByTitleContainingOrDescriptionContaining(searchTerm,searchTerm);
	}
	@PostMapping("/books")
	public Book create(@RequestBody Book book) {
		return bookRepo.save(book);
	}
	
	@PutMapping("/books/{id}")
	public Book update(@PathVariable int id, @RequestBody Book book) {
	Book bookToUpdate = bookRepo.findById(id).get();
	if(book.getTitle() !=null) {
	bookToUpdate.setTitle(book.getTitle());
	}
	if (book.getAuthor() !=null) {
    	bookToUpdate.setAuthor(book.getAuthor());
	}
	if(book.getDescription() !=null) {
	bookToUpdate.setDescription(book.getDescription());
	}
	return bookRepo.save(bookToUpdate);
	}
	@DeleteMapping("/books/{id}")
	public boolean delete(@PathVariable int id) {
		bookRepo.deleteById(id);
		return true;
	}
}

package de.uni.koeln.se.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.repository.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	public List <Book> findBooks () {
		return bookRepo.findAll();
	}
	
	public Optional <Book> fetchBook (int id) {
		return bookRepo.findById(id);
	}
	
	public Book addBook (Book book) {
		return bookRepo.save(book);
	}
	
	public boolean deleteBook (int id) {
		boolean status;
		try {
			bookRepo.deleteById(id);
			status = true;
		} catch (Exception e) {
			status = false;
		}
		return status;
	}
	public Optional <Book> getOldestBook () {
		return Optional.ofNullable(getOldestBook(findBooks()));
	}
	
	public Optional <Book> getLatestBook () {
		return Optional.ofNullable(getLatestBook(findBooks()));
	}
	
	private Book getOldestBook (List <Book> books) {
		if (books == null) {return null;}
		Book oldestBook = books.get(0);
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getYear() < oldestBook.getYear()) {
				oldestBook = books.get(i);
			}
		}
		return oldestBook;
	}
	private Book getLatestBook (List <Book> books) {
		if (books == null) {return null;}
		Book latestBook = books.get(0);
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getYear() > latestBook.getYear()) {
				latestBook = books.get(i);
			}
		}
		return latestBook;
	}
}

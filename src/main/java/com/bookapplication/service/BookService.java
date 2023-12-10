package com.bookapplication.service;

import com.bookapplication.entity.Book;
import com.bookapplication.model.requestDTO.BookDTO;
import com.bookapplication.model.requestDTO.CategoryDTO;
import com.bookapplication.model.requestresponse.ApiResponse;
import com.bookapplication.model.requestresponse.PaginationResponse;

import java.util.List;

public interface BookService {
     ApiResponse<Book> addBook(BookDTO bookDTO);
     ApiResponse<Book> editBook(Long bookId,BookDTO bookDTO);
     PaginationResponse getAllBooks(int offset, int limit);
     ApiResponse<Book> addBookToCategory(Long categoryId,Long bookId);
     ApiResponse<Book> addBookToFavorites(Long bookId);
     ApiResponse<List<Book>> getAllFavoriteBooks();
     ApiResponse<Book> deleteBook(Long bookId);






}

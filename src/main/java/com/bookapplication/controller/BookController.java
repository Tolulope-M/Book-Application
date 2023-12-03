package com.bookapplication.controller;

import com.bookapplication.entity.Book;
import com.bookapplication.model.requestDTO.BookDTO;
import com.bookapplication.model.requestresponse.ApiResponse;
import com.bookapplication.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;

    /**
     * Endpoint to add a new book.
     *
     * @param bookDTO The data of the book to be added.
     * @return ResponseEntity containing ApiResponse with the added book.
     */
    @PostMapping()
    public ResponseEntity<ApiResponse<Book>> addBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    /**
     * Endpoint to edit an existing book.
     *
     * @param bookId   The ID of the book to be edited.
     * @param bookDTO  The updated data for the book.
     * @return ResponseEntity containing ApiResponse with the edited book.
     */
    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Book>> editBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.editBook(bookId, bookDTO));
    }

    /**
     * Endpoint to get all books.
     *
     * @return ResponseEntity containing ApiResponse with a list of all books.
     */
    @GetMapping()
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    /**
     * Endpoint to add a book to a category.
     *
     * @param categoryId The ID of the category.
     * @param bookId     The ID of the book to be added to the category.
     * @return ResponseEntity containing ApiResponse with the added book.
     */
    @PostMapping("/{categoryId}/{bookId}")
    public ResponseEntity<ApiResponse<Book>> addBookToCategory(@PathVariable Long categoryId,
                                                               @PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.addBookToCategory(categoryId, bookId));
    }

    /**
     * Endpoint to add a book to the favorites list.
     *
     * @param bookId The ID of the book to be added to favorites.
     * @return ResponseEntity containing ApiResponse with the added book.
     */
    @PutMapping("/favorite/{bookId}")
    public ResponseEntity<ApiResponse<Book>> addBookToFavorites(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.addBookToFavorites(bookId));
    }

    /**
     * Endpoint to get all favorite books.
     *
     * @return ResponseEntity containing ApiResponse with a list of all favorite books.
     */
    @GetMapping("/favorite")
    public ResponseEntity<ApiResponse<List<Book>>> getAllFavoriteBooks() {
        return ResponseEntity.ok(bookService.getAllFavoriteBooks());
    }

    /**
     * Endpoint to delete a book.
     *
     * @param bookId The ID of the book to be deleted.
     * @return ResponseEntity containing ApiResponse with the deleted book.
     */
    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Book>> deleteBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.deleteBook(bookId));
    }
}

package com.bookapplication.service.impl;

import com.bookapplication.entity.Book;
import com.bookapplication.entity.Category;
import com.bookapplication.exception.ClientSideException;
import com.bookapplication.exception.ServerSideException;
import com.bookapplication.model.requestDTO.BookDTO;
import com.bookapplication.model.requestresponse.ApiResponse;
import com.bookapplication.model.requestresponse.PaginationResponse;
import com.bookapplication.repository.BookRepository;
import com.bookapplication.repository.CategoryRepository;
import com.bookapplication.service.BookService;
import com.bookapplication.util.IsbnGeneration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bookapplication.constants.AppConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final IsbnGeneration isbnGeneration;

    @Override
    public ApiResponse<Book> addBook(BookDTO bookDTO) {
            Optional<Book> bookResponse = bookRepository.findByTitle(bookDTO.getTitle());

            if (bookResponse.isPresent()) {
                throw new ClientSideException(BOOK_ALREADY_EXIST);
            }

            Book book = new Book();
            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setDescription(bookDTO.getDescription());
            book.setIsbn(isbnGeneration.generateIsbn());

            Book savedBook = bookRepository.save(book);
            log.info("saved book >> {}", savedBook);
            return ApiResponse.<Book>builder()
                    .status(SUCCESS_STATUS_CODE)
                    .message(SUCCESS)
                    .data(savedBook)
                    .build();
    }

    @Override
    public ApiResponse<Book> editBook(Long bookId, BookDTO bookDTO) {
            Optional<Book> bookResponse = bookRepository.findById(bookId);
            if (!bookResponse.isPresent()) {
                throw new ClientSideException(BOOK_NOT_FOUND);
            }
            Book book = bookResponse.get();
            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setDescription(bookDTO.getDescription());
            Book savedBook = bookRepository.save(book);

            log.info("saved book >> {}", savedBook);
            return ApiResponse.<Book>builder()
                    .status(SUCCESS_STATUS_CODE)
                    .message(SUCCESS)
                    .data(savedBook)
                    .build();
    }

    @Override
    public PaginationResponse getAllBooks(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Book> page = bookRepository.findAll(pageable);
        return new PaginationResponse<>(page.getContent(), page);

    }

    @Override
    public ApiResponse<Book> addBookToCategory(Long categoryId, Long bookId) {
            Book bookResponse = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ClientSideException(BOOK_NOT_FOUND));

            Category categoryResponse = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ClientSideException(CATEGORY_NOT_FOUND));

            List<Book> book = bookRepository.findAllByCategory(categoryResponse);
            if(book.contains(bookResponse)){
                throw new ClientSideException("Book Already added to category");
            }

            bookResponse.setCategory(categoryResponse);
            Book savedBook = bookRepository.save(bookResponse);

            log.info("saved book >> {}", savedBook);
            return ApiResponse.<Book>builder()
                    .status(SUCCESS_STATUS_CODE)
                    .message(SUCCESS)
                    .data(savedBook)
                    .build();
    }

    @Override
    public ApiResponse<Book> addBookToFavorites(Long bookId) {
            Book bookResponse = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ClientSideException(BOOK_NOT_FOUND));

            bookResponse.setIsFavourite(true);
            Book savedBook = bookRepository.save(bookResponse);

            return ApiResponse.<Book>builder()
                    .status(SUCCESS_STATUS_CODE)
                    .message(SUCCESS)
                    .data(savedBook)
                    .build();

    }

    @Override
    public ApiResponse<List<Book>> getAllFavoriteBooks() {
       return ApiResponse.<List<Book>>builder()
               .status(SUCCESS_STATUS_CODE)
               .message(SUCCESS)
               .data(bookRepository.findAllByIsFavouriteTrue())
               .build();
    }

    @Override
    public ApiResponse<Book> deleteBook(Long bookId) {
            Optional<Book> bookResponse = bookRepository.findById(bookId);

            if (bookResponse.isEmpty()) {
                throw new ClientSideException(BOOK_NOT_FOUND);
            }

            Book bookToDelete = bookResponse.get();
            bookRepository.delete(bookToDelete);

            log.info("Deleted category >> {}", bookToDelete);

            return ApiResponse.<Book>builder()
                    .status(SUCCESS_STATUS_CODE)
                    .message(SUCCESS)
                    .data(bookToDelete)
                    .build();
    }
}


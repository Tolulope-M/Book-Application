package com.bookapplication.repository;

import com.bookapplication.entity.Book;
import com.bookapplication.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String name);

    Optional<Book> findByIsbn(String isbn);

    Page<Book> findAllByIsFavouriteTrue(Pageable pageable);
    List<Book> findAllByCategory(Category category);
}

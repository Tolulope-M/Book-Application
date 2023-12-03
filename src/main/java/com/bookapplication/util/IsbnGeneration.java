package com.bookapplication.util;

import com.bookapplication.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class IsbnGeneration {
    private final BookRepository bookRepository;
    public String generateIsbn() {
        Random random = new Random();

        // Generate a random 10-digit number
        StringBuilder randomDigits = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10); // Generate a random digit (0 to 9)
            randomDigits.append(digit);
        }
        if (isExist(randomDigits.toString())){
            generateIsbn();
    }

        return randomDigits.toString();
    }
    public boolean isExist(String isbn) {
        return bookRepository.findByIsbn(isbn).isPresent();
    }
}

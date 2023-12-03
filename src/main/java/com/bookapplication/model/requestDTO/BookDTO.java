package com.bookapplication.model.requestDTO;

import com.bookapplication.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private String title;
    private String author;
    private String description;
}

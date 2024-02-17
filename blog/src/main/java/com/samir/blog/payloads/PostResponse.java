package com.samir.blog.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
public class PostResponse {

    private List<PostDto> content;
    private int PageNumber;
    private int PageSize;
    private boolean lastPage;
    private int totalPages;
}

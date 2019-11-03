package com.bookstore.mutual.domain.bookinfos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookImageLinks {
    private String smallThumbnail;
    private String thumbnail;
}
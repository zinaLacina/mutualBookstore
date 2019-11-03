package com.bookstore.mutual.domain.bookinfos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookItem {
    @Id
    String id;
    String selfLink;
    BookVolumeInfo volumeInfo;
}

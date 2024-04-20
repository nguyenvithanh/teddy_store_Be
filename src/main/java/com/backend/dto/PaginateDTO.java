package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginateDTO<T> {
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean lastPage;
        private T data;
}

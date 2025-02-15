package com.coveo.challenge.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedData<T> {
    private Integer page;
    private Integer totalNumberOfPages;
    private List<T> data;
}

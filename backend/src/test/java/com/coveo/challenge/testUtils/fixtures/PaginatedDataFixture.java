package com.coveo.challenge.testUtils.fixtures;

import java.util.List;

import com.coveo.challenge.models.PaginatedData;

public class PaginatedDataFixture<T> {
    private static final int PAGE = 1;
    private static final int TOTAL_NUMBER_OF_PAGES = 2;

    private final PaginatedData<T> paginatedData;

    public PaginatedDataFixture() {
        paginatedData = new PaginatedData<T>();
        paginatedData.setPage(PAGE);
        paginatedData.setTotalNumberOfPages(TOTAL_NUMBER_OF_PAGES);
    }

    public PaginatedDataFixture<T> withData(List<T> data) {
        paginatedData.setData(data);
        return this;
    }

    public PaginatedData<T> build() {
        return paginatedData;
    }
}

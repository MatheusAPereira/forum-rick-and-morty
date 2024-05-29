package com.rickandmorty.forum.dtos;

public class PaginatedInfoDTO {
    private int pageCount;
    private long totalItemCount;
    private int pageNumber;
    private int pageSize;
    private boolean hasPreviousPage;
    private boolean hasNextPage;

    public PaginatedInfoDTO(int pageCount, long totalItemCount, int pageNumber, int pageSize, boolean hasPreviousPage, boolean hasNextPage) {
        this.pageCount = pageCount;
        this.totalItemCount = totalItemCount;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public long getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(long totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}

package com.rickandmorty.forum.dtos;

import java.util.List;

public class PaginatedDTO<T> {
    private PaginatedInfoDTO info;
    private List<T> items;

    public PaginatedDTO(PaginatedInfoDTO paginatedInfoDTO, List<T> items) {
        this.info = paginatedInfoDTO;
        this.items = items;
    }

    public PaginatedInfoDTO getInfo() {
        return info;
    }

    public void setInfo(PaginatedInfoDTO info) {
        this.info = info;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}


package com.atmlocator.atmlocator.model;

import java.util.List;

public class ApiResponseObject<E> {
    private List<E> list;

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}

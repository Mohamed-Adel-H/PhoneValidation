package com.jumia.contactor.utils;

import org.springframework.data.domain.Sort;

import java.util.List;

public class CustomerGETPayload {
    Boolean state;
    private Integer page, size;
    private String country;
    private List<Sort.Order> sortOrders;

    public CustomerGETPayload(Integer page, Integer size, String country, Boolean state, List<Sort.Order> sortOrders) {
        this.page = page;
        this.size = size;
        this.country = country;
        this.state = state;
        this.sortOrders = sortOrders;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public List<Sort.Order> getSortOrders() {
        return sortOrders;
    }

    public void setSortOrders(List<Sort.Order> sortOrders) {
        this.sortOrders = sortOrders;
    }
}

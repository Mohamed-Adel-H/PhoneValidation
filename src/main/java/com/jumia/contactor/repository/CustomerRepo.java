package com.jumia.contactor.repository;

import com.jumia.contactor.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepo extends PagingAndSortingRepository<Customer, Integer> {
    Page<Customer> findByCountryCodeContaining(String code, Pageable pagingSort);

//    Page<Customer> findByCountryCodeAndStateContaining(String country, String stateRegex, Pageable pagingSort);

}

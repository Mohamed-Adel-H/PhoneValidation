package com.jumia.contactor.service;

import com.jumia.contactor.entity.Customer;
import com.jumia.contactor.repository.CustomerRepo;
import com.jumia.contactor.utils.CustomerGETPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    CustomerRepo customerRepo;


    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Page<Customer> getAllCustomer(CustomerGETPayload customerPayload) {
        Pageable pagingSort = PageRequest.of(customerPayload.getPage(), customerPayload.getSize(), Sort.by(customerPayload.getSortOrders()));

        if (customerPayload.getCountry() != null && customerPayload.getState() != null) {
            Page<Customer> customers = customerRepo.findByCountryCodeContaining(customerPayload.getCountry(), pagingSort);
            return getCustomersByState(customers, customerPayload);
        } else if (customerPayload.getCountry() != null) {
            return customerRepo.findByCountryCodeContaining(customerPayload.getCountry(), pagingSort);
        } else if (customerPayload.getState() != null) {
            Page<Customer> customers = customerRepo.findAll(pagingSort);
            return getCustomersByState(customers, customerPayload);
        } else {
            return customerRepo.findAll(pagingSort);
        }
    }

    private Page<Customer> getCustomersByState(Page<Customer> customers, CustomerGETPayload customerPayload) {
        List<Customer> result = new ArrayList<>();
        StateValidator stateValidator = new StateValidator();

        for (Customer customer : customers.getContent()) {
            Boolean isValid = stateValidator.isValid(customer.getCountryCode(), customer.getState());
            if (isValid && customerPayload.getState())
                result.add(customer);

            if (!isValid && !customerPayload.getState())
                result.add(customer);
        }
        return new PageImpl<>(result);
    }
}

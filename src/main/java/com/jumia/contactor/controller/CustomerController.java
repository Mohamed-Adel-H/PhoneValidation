package com.jumia.contactor.controller;

import com.jumia.contactor.entity.Customer;
import com.jumia.contactor.service.CustomerService;
import com.jumia.contactor.utils.CustomerGETPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {


    ControllerUtil controllerUtil;
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
        controllerUtil = new ControllerUtil();
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Boolean state,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        try {
            List<Order> sortOrders = controllerUtil.getSortOrders(sort);
            CustomerGETPayload customerPayload = new CustomerGETPayload(page, size, country, state, sortOrders);

            Page<Customer> pageTuts = customerService.getAllCustomer(customerPayload);

            List<Customer> customers = pageTuts.getContent();

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("customers", customers);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

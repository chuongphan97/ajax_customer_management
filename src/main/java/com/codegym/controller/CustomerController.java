package com.codegym.controller;

import com.codegym.model.Country;
import com.codegym.model.Customer;
import com.codegym.service.ICountryService;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @Autowired
    ICountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> country(){
        return countryService.findAll();
    }


    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        Optional<Country> country = countryService.findById(customer.getCountry().getCountry_id());
        customer.getCountry().setCountry_name(country.get().getCountry_name());
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }

    @GetMapping("/all-customer")
    public ResponseEntity<Iterable<Customer>> allCustomers(){
        return new ResponseEntity<>(customerService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/list")
    public ModelAndView getAllCustomerPage(){
        ModelAndView modelAndView = new ModelAndView("/customers/list");
        Iterable<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.remove(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setId(customerOptional.get().getId());
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        return ResponseEntity.of(customer);
    }

    @GetMapping("/countries")
    public ResponseEntity<Iterable<Country>> allCountries(){
        return new ResponseEntity<>(countryService.findAll(),HttpStatus.OK);
    }

}

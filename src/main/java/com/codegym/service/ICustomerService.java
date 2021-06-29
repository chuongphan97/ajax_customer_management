package com.codegym.service;

import com.codegym.model.Country;
import com.codegym.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ICustomerService extends IGeneralService<Customer> {

}

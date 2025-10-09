package com.Lujo_Boutique.loginapp_backend.service;

import com.Lujo_Boutique.loginapp_backend.model.Customer;
import com.Lujo_Boutique.loginapp_backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //find customer by email
    public Optional<Customer> findByEmail(String email) {
    return Optional.ofNullable(customerRepository.findByEmail(email));
    }

    //find by phone number
    public Optional<Customer> findByPhoneNum(String PhoneNum){
        return Optional.ofNullable(customerRepository.findByPhoneNum(PhoneNum));

    }

    //verify login
    public boolean checkLogin(String emailOrPhone, String password) {
        Optional<Customer> customer;
        if(emailOrPhone.contains("@")){
            customer = findByEmail(emailOrPhone);
        }
        else{
            customer = findByPhoneNum(emailOrPhone);
        }

        return customer.map(c -> c.getPassword().equals(password)).orElse(false);
    }

    //save new customers
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}

/*
 @Autowired, automatically creates needed object from CustomerRepository to make ue of its methods namely 'queries stated in repository
 public Optional<Customer> findByEmail(String email), searches 'Customer' table by 'Email' so it ssays " extract ecords from 'Customer' table by looking at the 'Email'
 can return containing a customer or not
 return Optional.ofNullable(customerRepository.findByEmail(email)); this returns object, wrapped so that null cases can be handled safely, done through use of keyword 'Optional'
 */



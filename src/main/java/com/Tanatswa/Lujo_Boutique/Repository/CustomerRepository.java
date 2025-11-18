package com.Tanatswa.Lujo_Boutique.Repository;

import com.Tanatswa.Lujo_Boutique.Domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface CustomerRepository extends JpaRepository<Customer, Integer> {
        Customer findByEmail(String email); //note framework automatically translates this to
        //SELECT * FROM CUSTOMER WHERE email = ?", JPA generates SQL queries
       Customer findByPhoneNum(String phoneNum);
    }
package com.Lujo_Boutique.loginapp_backend.repository;

import com.Lujo_Boutique.loginapp_backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email); //note framework automatically translates this to
    //SELECT * FROM CUSTOMER WHERE email = ?", JPA generates SQL queries
    Customer findByPhoneNum(String phoneNum);
}

//Notes:
//public , ensures that this interafce can be accessed anywhere in teh project, which is especially important
// because it is used in the CustomerService.java file to query Db

// In "public interface CustomerRepository " CustomerRepository is the name of the respository, often names after teh entity it manages in this case 'Customer'

//JPA is a springboot interface that offers all basic CRUD operations,
/*
e.g save(entity) insert or update table
findBy.. fetches data by entered attribute e.g findByEmail, fetches data by email
<Customer, Integer> Customer refers to entity name so javav knwos which enetity it is dealing with and "Integer" is teh data type of the primary key
 */


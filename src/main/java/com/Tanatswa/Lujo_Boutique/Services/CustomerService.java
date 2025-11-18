package com.Tanatswa.Lujo_Boutique.Services;

import com.Tanatswa.Lujo_Boutique.Domain.Customer;
import com.Tanatswa.Lujo_Boutique.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Find by email
    public Optional<Customer> findByEmail(String email) {
        return Optional.ofNullable(customerRepository.findByEmail(email));
    }

    // Find by phone
    public Optional<Customer> findByPhoneNum(String phoneNum) {
        return Optional.ofNullable(customerRepository.findByPhoneNum(phoneNum));
    }

    // ✅ Register new customer
    public Customer registerCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            throw new RuntimeException("Email already exists!");
        }

        if (customerRepository.findByPhoneNum(customer.getPhoneNum()) != null) {
            throw new RuntimeException("Phone number already registered!");
        }

        // Encrypt password before saving
        String encryptedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encryptedPassword);

        // Auto set createdAt if null
        if (customer.getCreatedAt() == null)
            customer.setCreatedAt(java.time.LocalDateTime.now());

        return customerRepository.save(customer);
    }

    // ✅ For Google signup (token-based users)
    public Customer registerWithGoogle(Customer customer) {
        // Usually, Google users don’t set a password manually
        customer.setPassword(passwordEncoder.encode("google_user_" + System.currentTimeMillis()));
        if (customer.getCreatedAt() == null)
            customer.setCreatedAt(java.time.LocalDateTime.now());
        return customerRepository.save(customer);
    }

    // ✅ Authenticate customer
    public Customer authenticatedCustomer(String email, String password) {
        Optional<Customer> customerOpt = email.contains("@")
                ? findByEmail(email)
                : findByPhoneNum(email);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            // Check password
            if (passwordEncoder.matches(password, customer.getPassword())) {
                return customer; //  Successful authentication
            }
        }

        return null; //  Return null if not authenticated
    }
}

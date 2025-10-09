package com.Lujo_Boutique.loginapp_backend.controller;
import com.Lujo_Boutique.loginapp_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //login endpoint
    @PostMapping("/login")
    public String login(@RequestParam("email") String emailOrPhone,
                        @RequestParam("pass") String password)
    {
        boolean success = customerService.checkLogin(emailOrPhone, password);
        if (success) {
            return "Login successful!";
        }
        else {
            return "Login failed!";
        }
    }
}

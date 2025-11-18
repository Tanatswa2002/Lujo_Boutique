package com.Tanatswa.Lujo_Boutique.Controller;

import com.Tanatswa.Lujo_Boutique.DTO.LoginRequest;
import com.Tanatswa.Lujo_Boutique.Domain.Customer;
import com.Tanatswa.Lujo_Boutique.Services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //create account endpoint:
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        try {
            Customer savedCustomer = customerService.registerCustomer(customer);
            return ResponseEntity.ok(savedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Google registration (optional)
    @PostMapping("/google")
    public ResponseEntity<?> registerWithGoogle(@RequestBody Customer customer) {
        Customer saved = customerService.registerWithGoogle(customer);
        return ResponseEntity.ok(saved);
    }



    //login endpoint
    @PostMapping("/login")
    public ResponseEntity <?> login(@RequestBody LoginRequest loginRequest)
    {
        // Authenticate using service layer
        //
        Customer customer = customerService.authenticatedCustomer(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        if (customer != null) {
            //get Jwt Token
            // in service is String token = jwtService.generateToken(customer);
            String token = "Mock_Jwt_For" + customer.getCustomerId();

            return ResponseEntity.ok(
                    Map.of(
                            "token", token,
                            "userId", customer.getCustomerId().toString(),
                            "name",customer.getFirstName()

                    )
            );
        }
        else {
            //return unauthorized error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid Credentials. Authentication failed"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logout Successful");
    }
}

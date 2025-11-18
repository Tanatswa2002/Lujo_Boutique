package com.Tanatswa.Lujo_Boutique.Controller;
import com.Tanatswa.Lujo_Boutique.Domain.Customer;
import com.Tanatswa.Lujo_Boutique.Domain.CustomerOrder;
import com.Tanatswa.Lujo_Boutique.Domain.Refund;
import com.Tanatswa.Lujo_Boutique.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    //Get profile
    @GetMapping("/{customerId}")
    public Customer getCustomerProfile(@PathVariable Integer customerId) {
        return profileService.getCustomerProfile(customerId);
    }

    //Fetch track orders
    @GetMapping("{customerId}/track-orders")
    public List<CustomerOrder> getTrackOrders(@PathVariable Integer customerId) {
        return profileService.getTrackOrders(customerId);
    }

    //fetch order history
    @GetMapping("{customerId}/order-history")
    public List<CustomerOrder> getOrderHistory(@PathVariable Integer customerId) {
        return profileService.getOrderHistory(customerId);
    }

    @GetMapping("{customerId}/refunds")
    public List<Refund> getRefunds(@PathVariable Integer customerId) {
        return profileService.getRefunds(customerId);
    }


}

package com.Tanatswa.Lujo_Boutique.Services;
import com.Tanatswa.Lujo_Boutique.Domain.Customer;
import com.Tanatswa.Lujo_Boutique.Domain.CustomerOrder;
import com.Tanatswa.Lujo_Boutique.Domain.Refund;
import com.Tanatswa.Lujo_Boutique.Repository.CustomerOrderRepository;
import com.Tanatswa.Lujo_Boutique.Repository.CustomerRepository;
import com.Tanatswa.Lujo_Boutique.Repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class ProfileService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private RefundRepository refundRepository;

    //retrieve customer profile
    public Customer getCustomerProfile(Integer customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Something Went Wrong!"));
    }

    //Get customer orders(eligible for tracking
    public List<CustomerOrder> getTrackOrders(Integer customerId){
        return customerOrderRepository.findByCustomer_CustomerId(customerId);

    }

    //Order History
    public List<CustomerOrder> getOrderHistory(Integer customerId){
        return customerOrderRepository.findByCustomer_CustomerIdAndOrderStatus(customerId,"COMPLETED");
    }

    //get all refunds/returns
    public List<Refund> getRefunds(Integer customerId){
        return refundRepository.findByCustomer_CustomerId(customerId);
    }

}

package com.Tanatswa.Lujo_Boutique.Services;

import com.Tanatswa.Lujo_Boutique.Domain.Product;
import com.Tanatswa.Lujo_Boutique.Domain.Review;
import com.Tanatswa.Lujo_Boutique.Domain.Customer;
import com.Tanatswa.Lujo_Boutique.DTO.ReviewDTO;
import com.Tanatswa.Lujo_Boutique.Repository.ReviewRepository;
import com.Tanatswa.Lujo_Boutique.Repository.ProductRepository;
import com.Tanatswa.Lujo_Boutique.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         ProductRepository productRepository,
                         CustomerRepository customerRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Product product = productRepository.findById(reviewDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Customer customer = customerRepository.findById(reviewDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Review review = new Review();
        review.setReviewDescription(reviewDTO.getReviewDescription());
        review.setCreatedAt(LocalDateTime.now());
        review.setProduct(product);
        review.setCustomer(customer);

        Review saved = reviewRepository.save(review);

        reviewDTO.setReviewId(saved.getReviewId());
        return reviewDTO;
    }

    public List<ReviewDTO> getReviewsByProduct(String productId) {
        return reviewRepository.findByProductProductId(productId)
                .stream()
                .map(r -> new ReviewDTO(r.getReviewId(), r.getReviewDescription(),
                        r.getCustomer().getCustomerId(), r.getProduct().getProductId()))
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByCustomer(Integer customerId) {
        return reviewRepository.findByCustomerCustomerId(customerId)
                .stream()
                .map(r -> new ReviewDTO(r.getReviewId(), r.getReviewDescription(),
                        r.getCustomer().getCustomerId(), r.getProduct().getProductId()))
                .collect(Collectors.toList());
    }
}


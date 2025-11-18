package com.Tanatswa.Lujo_Boutique.Domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

//tell class that this is a entity class
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
//What is the table we are mapping to
@Table(name = "ORDER_ITEM")
public class OrderItem {
    //map entities
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @Column(name = "price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    //foreign key columns use 'manytoone
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    @JsonBackReference
    private CustomerOrder order;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Lob
    @Column(name = "product_image",nullable = false)
    private byte[] productImage;

    @Column(name = "product_description")
    private String ProductDescription;

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefundItem> refundItems;


}

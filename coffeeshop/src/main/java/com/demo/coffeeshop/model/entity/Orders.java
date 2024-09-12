package com.demo.coffeeshop.model.entity;

import com.demo.coffeeshop.model.entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity(name = "orders")
@Table(indexes = {
        @Index(name = "idx_email", columnList = "email")
})
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", columnDefinition = "BINARY(36)")
    private UUID orderId;

    @Column(length = 50)
    @Size(min = 4, max = 50)
    private String email;

    @Column(length = 200)
    private String address;

    @Column(length = 200)
    private String postcode;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItems> orderItems = new ArrayList<>();

    @CreationTimestamp
    @Column(length = 6)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(length = 6)
    private LocalDateTime updatedAt;

    public Orders(String email, String address, String postcode) {
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = OrderStatus.ACCEPTED;
    }

    public static Orders createOrder(String email, String address, String postcode, List<OrderItems> orderItems) {
        Orders order = new Orders(email,address,postcode);
        for (OrderItems orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    public void addOrderItem(OrderItems orderItems){
        this.orderItems.add(orderItems);
        orderItems.setOrders(this);
    }

    public void changeInfo(String address, String postcode){
        if(this.orderStatus == OrderStatus.ACCEPTED || this.orderStatus == OrderStatus.PAYMENT_CONFIRMED){
            this.address = address;
            this.postcode = postcode;
        }else{
            throw new IllegalStateException("배송이 시작되어 주문 변경이 불가합니다.");
        }
    }

    public void changeStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }
    
    public void cancel(){
        if(!(this.orderStatus == OrderStatus.PAYMENT_CONFIRMED || this.orderStatus == OrderStatus.ACCEPTED))
            throw new IllegalStateException("배송을 취소할 수 없는 상태 입니다");
        this.orderStatus = OrderStatus.CANCELLED;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @PrePersist
    @PreUpdate
    private void validateEmail() {
        if (!checkAddress(this.email)) {
            throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
        }
    }

    // 이메일 유효성 검사 메서드
    private static boolean checkAddress(String address) {
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", address);
    }
}

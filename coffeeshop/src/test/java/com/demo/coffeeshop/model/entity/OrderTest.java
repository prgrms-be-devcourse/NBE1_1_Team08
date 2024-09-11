package com.demo.coffeeshop.model.entity;

import com.demo.coffeeshop.model.entity.enums.OrderStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class OrderTest {

    @PersistenceContext
    EntityManager em;

    private Random random = new Random();
    private List<Products> existingProducts;

    @BeforeEach
    public void setUp() {
        existingProducts = em.createQuery("SELECT p FROM products p", Products.class)
                .setMaxResults(5)
                .getResultList();

        assertNotNull(existingProducts);
        assert !existingProducts.isEmpty() : "No existing products found in the database";

        for (int i = 4; i <= 23; i++) {
            String email = "test" + i + "@naver.com";
            String address = "st" + (i - 3);
            String postcode = generateRandomPostcode();

            List<OrderItems> orderItems = new ArrayList<>();
            int itemCount = random.nextInt(3) + 1;
            for (int j = 0; j < itemCount; j++) {
                Products randomProduct = existingProducts.get(random.nextInt(existingProducts.size()));
                OrderItems item = new OrderItems(randomProduct, random.nextInt(5) + 1);
                orderItems.add(item);
            }

            Orders order = Orders.createOrder(email, address, postcode, orderItems);

            // 랜덤한 주문 상태 설정 (ACCEPTED 제외)
            OrderStatus[] statuses = OrderStatus.values();
            OrderStatus randomStatus;
            do {
                randomStatus = statuses[random.nextInt(statuses.length)];
            } while (randomStatus == OrderStatus.ACCEPTED);
            order.changeStatus(randomStatus);

            em.persist(order);
        }
        em.flush();
        em.clear();
    }

    //5자리 랜덤숫자 생성기
    private String generateRandomPostcode() {
        return String.format("%05d", random.nextInt(100000));
    }

    //
    private OrderStatus getRandomOrderStatus() {
        OrderStatus[] statuses = OrderStatus.values();
        return statuses[random.nextInt(statuses.length)];
    }

    @Test
    public void testOrderInit() {
        long orderCount = (long) em.createQuery("SELECT COUNT(o) FROM orders o").getSingleResult();
        assertEquals(25, orderCount);


        long itemCount = (long) em.createQuery("SELECT COUNT(oi) FROM order_items oi").getSingleResult();
        assertTrue(itemCount >= 20 && itemCount <= 60);

    }


}

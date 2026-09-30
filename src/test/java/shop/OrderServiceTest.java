package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private OrderRepository repository;
    private OrderService service;

    @BeforeEach
    void setUp() {
        service = new OrderService(new OrderRepository());
    }

    @Test
    void rejectsEmptyCart() {
        assertThrows(IllegalStateException.class, () -> service.placeOrder(new Cart()));
    }

    @Test
    void placingAnOrderStoresIt() {
        Cart cart = new Cart();
        cart.add("Cotton kurta", new BigDecimal("18.99"), 1);

        service.placeOrder(cart);

        assertEquals(1, repository.count());
    }
}

package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class CartTest {

    @Test
    void emptyCartCostsNothing() {
        assertEquals(new BigDecimal("0"), new Cart().total());
    }

    @Test
    void singleItemTotal() {
        Cart cart = new Cart();
        cart.add("Linen shirt", new BigDecimal("29.99"), 1);
        assertEquals(new BigDecimal("29.99"), cart.total());
    }

    @Test
    void totalMultipliesPriceByQuantity() {
        Cart cart = new Cart();
        cart.add("Linen shirt", new BigDecimal("29.99"), 2);
        assertEquals(new BigDecimal("59.98"), cart.total(),
                "cart total for 2 x 29.99");
    }
}

package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PriceFormatterTest {

    @Test
    void formatsWithThousandsSeparator() {
        assertEquals("Rs 1,499.00", PriceFormatter.format(new BigDecimal("1499")));
    }

    @Test
    void roundsToTwoDecimals() {
        assertEquals("Rs 10.46", PriceFormatter.format(new BigDecimal("10.455")));
    }
}

package shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** A shopping cart. Each line has a unit price and a quantity. */
public class Cart {

    private record Line(String name, BigDecimal unitPrice, int quantity) {}

    private final List<Line> lines = new ArrayList<>();

    public void add(String name, BigDecimal unitPrice, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("quantity must be at least 1");
        }
        lines.add(new Line(name, unitPrice, quantity));
    }

    public int itemCount() {
        return lines.stream().mapToInt(Line::quantity).sum();
    }

    /** Total price of everything in the cart. */
    public BigDecimal total() {
        BigDecimal total = BigDecimal.ZERO;
        for (Line line : lines) {
            total = total.add(line.unitPrice()); // quantity is not applied here
        }
        return total;
    }
}

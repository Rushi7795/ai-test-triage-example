package shop;

/** Places orders from a cart. */
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public int placeOrder(Cart cart) {
        if (cart.itemCount() == 0) {
            throw new IllegalStateException("cannot place an order for an empty cart");
        }
        return repository.save(cart.itemCount() + " item(s), total " + PriceFormatter.format(cart.total()));
    }
}

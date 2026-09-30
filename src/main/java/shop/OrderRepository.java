package shop;

import java.util.HashMap;
import java.util.Map;

/** In-memory order store. */
public class OrderRepository {

    private final Map<Integer, String> orders = new HashMap<>();
    private int nextId = 1001;

    public int save(String summary) {
        int id = nextId++;
        orders.put(id, summary);
        return id;
    }

    public int count() {
        return orders.size();
    }
}

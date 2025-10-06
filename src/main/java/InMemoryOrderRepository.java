import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, Order> store = new LinkedHashMap<>();


    private static void requireNonBlank(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(name + " must not be null or blank");
        }
    }

    @Override
    public boolean save(Order order) {
        // GUARDS
        if (order == null) {
            throw new IllegalArgumentException("order must not be null");
        }
        if (order.id == null || order.id.isBlank()) {
            throw new IllegalArgumentException("order.id must not be null or blank");
        }

        boolean isNew = !store.containsKey(order.id);
        store.put(order.id, order);
        return isNew;
    }

    @Override
    public Order getByIdOrThrow(String id) {
        // GUARDS
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be null or blank");
        }

        Order o = store.get(id);
        if (o == null) throw new java.util.NoSuchElementException("unknown id " + id);
        return o;
    }

    @Override
    public boolean deleteById(String id) {
        // GUARDS
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be null or blank");
        }

        return store.remove(id) != null;
    }

    @Override
    public List<Order> listAll() {
        // nie null zurückgeben
        return new ArrayList<>(store.values());
    }
}
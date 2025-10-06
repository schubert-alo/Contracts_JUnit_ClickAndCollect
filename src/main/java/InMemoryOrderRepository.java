import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, Order> store = new LinkedHashMap<>();

    @Override
    public boolean save(Order order) {
        // GUARDS
      //  if (/* TODO: order ? */) throw new IllegalArgumentException("order");
      //  if (/* TODO: order.id ? */) throw new IllegalArgumentException("order.id");

        boolean isNew = !store.containsKey(order.id);
        store.put(order.id, order);
        return isNew;
    }

    @Override
    public Order getByIdOrThrow(String id) {
        // GUARDS
        //if (/* TODO: id ? */) throw new IllegalArgumentException("id");

        Order o = store.get(id);
        if (o == null) throw new java.util.NoSuchElementException("unknown id " + id);
        return o;
    }

    @Override
    public boolean deleteById(String id) {
        // GUARDS
        //if (/* TODO: id ? */) throw new IllegalArgumentException("id");

        return store.remove(id) != null;
    }

    @Override
    public List<Order> listAll() {
        // nie null zurückgeben
        return new ArrayList<>(store.values());
    }
}
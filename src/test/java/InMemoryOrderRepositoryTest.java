import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

final class InMemoryOrderRepositoryTest {

    @Test void save_and_get_returns_same_order() {
        // Arrange
        OrderRepository repo = new InMemoryOrderRepository();
        repo.save(new Order("A1", 2500));
        // Act
        Order loaded = repo.getByIdOrThrow("A1");
        // Assert
        assertEquals("A1", loaded.id);
        assertEquals(2500, loaded.totalInCents);
    }

    @Test void save_same_id_returns_false_on_update() {
        OrderRepository repo = new InMemoryOrderRepository();
        assertTrue(repo.save(new Order("A1", 2500)));
        assertFalse(repo.save(new Order("A1", 3000))); // Update
    }

    @Test void listAll_never_null_and_contains_saved() {
        OrderRepository repo = new InMemoryOrderRepository();
        assertNotNull(repo.listAll());
        assertEquals(0, repo.listAll().size());
        repo.save(new Order("A1", 123));
        assertEquals(1, repo.listAll().size());
    }

    @Test void delete_twice_true_then_false() {
        OrderRepository repo = new InMemoryOrderRepository();
        repo.save(new Order("A1", 100));
        assertTrue(repo.deleteById("A1"));
        assertFalse(repo.deleteById("A1"));
    }

    @Test void get_unknown_id_throws_nsee() {
        OrderRepository repo = new InMemoryOrderRepository();
        var ex = assertThrows(java.util.NoSuchElementException.class,
                () -> repo.getByIdOrThrow("Quatsch_ID"));
        assertTrue(ex.getMessage().contains("unknown id"));
    }

    @Test void delete_by_id_null_throws_iae_with_message() {
        OrderRepository repo = new InMemoryOrderRepository();
        var ex = assertThrows(IllegalArgumentException.class, () -> repo.deleteById(null));
        assertTrue(ex.getMessage().contains("id must not be null or blank"));
    }

    @Test void save_null_throws_iae_with_message() {
        OrderRepository repo = new InMemoryOrderRepository();
        var ex = assertThrows(IllegalArgumentException.class, () -> repo.save(null));
        assertTrue(ex.getMessage().contains("order must not be null"));
    }
}
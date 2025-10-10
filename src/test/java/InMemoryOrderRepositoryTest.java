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
}
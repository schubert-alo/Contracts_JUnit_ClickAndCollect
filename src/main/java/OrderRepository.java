/**
 * Repository für Click-&-Collect-Bestellungen.
 * Verantwortlich für Anlegen, Lesen, Löschen, Auflisten.
 * Invariante: Methoden geben niemals null zurück (Listen ggf. leer).
 */
public interface OrderRepository {

    /**
     * Legt eine Bestellung an oder aktualisiert sie.
     *
     * @param order darf nicht null sein; auch order.id darf nicht null (und nicht leer) sein
     * @return true, wenn neu angelegt; false, wenn bestehende Bestellung aktualisiert wurde
     * @throws IllegalArgumentException wenn order oder order.id null/leer sind
     */
    boolean save(Order order);

    /**
     * Liefert die Bestellung zur ID oder signalisiert, dass sie nicht existiert.
     *
     * @param id darf nicht null (und nicht leer) sein
     * @return nie null
     * @throws IllegalArgumentException wenn id null/leer ist
     * @throws java.util.NoSuchElementException wenn keine Bestellung zu dieser id existiert
     */
    Order getByIdOrThrow(String id);

    /**
     * Löscht die Bestellung zur ID.
     *
     * @param id darf nicht null (und nicht leer) sein
     * @return true, wenn gelöscht; false, wenn keine Bestellung vorhanden (Business, keine Exception)
     * @throws IllegalArgumentException wenn id null/leer ist
     */
    boolean deleteById(String id);

    /**
     * Gibt alle Bestellungen in Einfügereihenfolge zurück.
     *
     * @return nie null; ggf. leere Liste
     */
    java.util.List<Order> listAll();
}
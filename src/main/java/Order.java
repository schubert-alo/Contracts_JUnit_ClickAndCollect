/**
 * Minimales Domain-Objekt für Click-&-Collect-Bestellungen.
 * Einheiten:
 * - Geldbeträge in Cent (totalInCents).
 *
 * Contract:
 * - id darf nicht null/leer sein.
 * - totalInCents >= 0 (kein negativer Betrag).
 */
final class Order {
    final String id;
    final int totalInCents;

    /**
     * @param id            darf nicht null/leer sein
     * @param totalInCents  Betrag in Cent, muss >= 0 sein
     * @throws IllegalArgumentException wenn id null/leer oder totalInCents < 0
     */
    Order(String id, int totalInCents) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id");
        if (totalInCents < 0) throw new IllegalArgumentException("totalInCents");
        this.id = id;
        this.totalInCents = totalInCents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order other = (Order) o;
        return this.totalInCents == other.totalInCents
                && this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Integer.hashCode(totalInCents);
        return result;
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', totalInCents=" + totalInCents + "}";
    }
}
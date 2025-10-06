public class Main {
    public static void main(String[] args) {
        OrderRepository repo = new InMemoryOrderRepository(); // Hier Polymorphie angelegt, weil wir nur den Vertrag (Interface) kennen – nicht die Klasse. --> Dependency Inversion light™ – weil wir Verträge lieben, nicht Implementierungen

        // 🟢 Happy Path: Bestellung anlegen und abrufen
        System.out.println("== Happy Path ==");
        Order o1 = new Order("A1", 2500);
        boolean created = repo.save(o1);
        System.out.println("Save returned: " + created);

        Order loaded = repo.getByIdOrThrow("A1");
        System.out.println("Loaded: id=" + loaded.id + ", total=" + loaded.totalInCents);

        // 🟢 Update gleicher ID
        System.out.println("\n== Update ==");
        boolean updated = repo.save(new Order("A1", 3000));
        System.out.println("Save returned: " + updated);

        // 🔵 Liste ausgeben
        System.out.println("\n== List all ==");
        for (Order o : repo.listAll()) {
            System.out.println(o.id + " -> " + o.totalInCents + " ct");
        }

        // 🔵 Delete (Business-Ergebnis: false beim 2. Versuch)
        System.out.println("\n== Delete test ==");
        System.out.println("First delete: " + repo.deleteById("A1"));
        System.out.println("Second delete: " + repo.deleteById("A1"));

        // 🔴 Fehlerfälle: ungültige Eingaben / nicht gefunden
        System.out.println("\n== Fehlerpfade ==");
        try {
            repo.getByIdOrThrow("Quatsch_ID");
        } catch (Exception e) {
            System.out.println("Expected: " + e);
        }

        try {
            repo.deleteById(null);
        } catch (Exception e) {
            System.out.println("Expected: " + e);
        }

        try {
            repo.save(null);
        } catch (Exception e) {
            System.out.println("Expected: " + e);
        }

        System.out.println("\n== Done ==");
    }
}
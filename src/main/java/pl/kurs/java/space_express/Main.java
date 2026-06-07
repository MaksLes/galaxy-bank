package pl.kurs.java.space_express;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ── 1. Budowanie kurierów przez Builder ──────────────────────────────
        SpaceCourier jan = SpaceCourier.builder()
                .firstName("Jan").lastName("Kowalski")
                .rating(5).employeeId("EMP-001").build();

        SpaceCourier anna = SpaceCourier.builder()
                .firstName("Anna").lastName("Nowak")
                .rating(3).employeeId("EMP-002").build();

        SpaceCourier piotr = SpaceCourier.builder()
                .firstName("Piotr").lastName("Kowalski")
                .rating(4).employeeId("EMP-003").build();

        SpaceCourier noName = SpaceCourier.builder()
                .firstName("Anonim").lastName(null)
                .rating(2).employeeId("EMP-004").build();

        // ── 2. Sortowanie naturalne (Comparable) — malejąco po ratingu ───────
        List<SpaceCourier> byRating = new ArrayList<>(List.of(jan, anna, piotr, noName));
        Collections.sort(byRating);
        System.out.println("Sortowanie po ocenie (malejąco):");
        byRating.forEach(c -> System.out.printf("  %s %s — rating: %d%n",
                c.getFirstName(), c.getLastName(), c.getRating()));

        // ── 3. Sortowanie przez Comparator — alfabetycznie, null na końcu ────
        List<SpaceCourier> byName = new ArrayList<>(List.of(jan, anna, piotr, noName));
        byName.sort(SpaceCourier.BY_NAME_COMPARATOR);
        System.out.println("\nSortowanie alfabetyczne (nullsLast):");
        byName.forEach(c -> System.out.printf("  %s %s%n",
                c.getLastName(), c.getFirstName()));

        // ── 4. Kalkulator kosztów + Text Block ───────────────────────────────
        ShippingCostCalculator calculator = new ShippingCostCalculator();

        DeliveryMethod standard = new StandardDelivery(
                new BigDecimal("99.99"), "Mars", true);
        DeliveryMethod express = new ExpressDelivery(
                new BigDecimal("200.00"), "Orion Belt", false, new BigDecimal("50.00"));

        BigDecimal standardCost = calculator.calculate(standard);
        BigDecimal expressCost = calculator.calculate(express);

        calculator.printSummary(jan, standard, standardCost);
        calculator.printSummary(anna, express, expressCost);

        // ── 5. Przydział misji przez DeliveryLogistics ────────────────────────
        AuthorizationService auth = new AuthorizationService();
        DeliveryLogistics logistics = new DeliveryLogistics(auth);

        // Kurier z rating=5 → przejdzie filtr
        logistics.assignMission(jan, standard);

        // Kurier z rating=3 → nie przejdzie → orElseGet pobierze rezerwowego
        logistics.assignMission(anna, express);

        // null → Optional.ofNullable(null) → orElseGet pobierze rezerwowego
        logistics.assignMission(null, express);
    }
}
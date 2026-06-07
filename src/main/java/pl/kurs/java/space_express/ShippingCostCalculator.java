package pl.kurs.java.space_express;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShippingCostCalculator {

    private static final  BigDecimal INTERPLANETARY_SURCHARGE = new BigDecimal("250.00");
    private static final BigDecimal VAT_MULTIPLIER = new BigDecimal("1.23");

    /**
     * Oblicza końcową cenę brutto dostawy.
     */
    public BigDecimal calculate(DeliveryMethod method) {
        if (method instanceof StandardDelivery sd) {
            BigDecimal cost = sd.getBaseCost();
            if (sd.isInterplanetary()) {
                cost = cost.add(INTERPLANETARY_SURCHARGE);
            }
            return cost.setScale(2, RoundingMode.HALF_UP);

        } else if (method instanceof ExpressDelivery ed) {
            return ed.getBaseCost()
                    .add(ed.priorityFee())
                    .multiply(VAT_MULTIPLIER)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        throw new IllegalArgumentException("Nieznany sposób dostawy: " + method);
    }

    //Wyświetlenie podsumowania w konsoli przy użyciu TextBlock.
    public void printSummary(SpaceCourier courier, DeliveryMethod method, BigDecimal finalCost) {
        System.out.printf("""
                =======================================
                        PODSUMOWANIE DOSTAWY
                =======================================
                
                    Kurier  : %s %s (ID: %s)
                    Ocenca  : %d / 5
                    Cel     : %s
                    Kwota   : %.2f PLN (brutto)
                    
                =======================================
                """,
                courier.getFirstName(),
                courier.getLastName(),
                courier.getEmployeeId(),
                courier.getRating(),
                method.getDestination(),
                finalCost
        );


    }

}

package pl.kurs.java.cinefest.service;

import pl.kurs.java.cinefest.model.FestivalTicket;
import pl.kurs.java.cinefest.model.MovieShow;
import pl.kurs.java.cinefest.model.StandardTicket;
import pl.kurs.java.cinefest.model.VIPPass;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TicketPriceCalculator {

    private static final BigDecimal GLASSES_FEE = new BigDecimal("15.00");
    private static final BigDecimal VAT_MULTIPLIER = new BigDecimal("1.23");

    public BigDecimal calculateFinalPrice(FestivalTicket ticket) {
        BigDecimal finalPrice;

        if (ticket instanceof StandardTicket st) {
            boolean has3D = st.selectedShows()
                    .stream()
                    .anyMatch(MovieShow::is3D);

            BigDecimal price = st.basePrice();
            if (has3D) {
                price = price.add(GLASSES_FEE);
            }
            finalPrice = price.setScale(2, RoundingMode.HALF_UP);

        } else if (ticket instanceof VIPPass vip) {
            finalPrice = vip.basePrice()
                    .add(vip.cateringSurcharge())
                    .multiply(VAT_MULTIPLIER)
                    .setScale(2, RoundingMode.HALF_UP);

        } else {
            throw new IllegalArgumentException("Nieznany typ biletu: " + ticket.getClass());
        }

        System.out.printf("""
                ===================================
                        PODSUMOWANIE BILETU
                ===================================       
                    Miejsce: %s
                    Kwota końcowa: %.2f zł
                ===================================
                """.formatted(ticket.seat(), finalPrice));
        return finalPrice;
    }
}


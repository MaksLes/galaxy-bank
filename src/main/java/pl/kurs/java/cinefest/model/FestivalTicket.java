package pl.kurs.java.cinefest.model;

import java.math.BigDecimal;

public sealed interface FestivalTicket permits StandardTicket, VIPPass {
    BigDecimal basePrice();
    String seat();
}

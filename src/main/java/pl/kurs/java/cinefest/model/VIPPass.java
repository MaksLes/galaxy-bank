package pl.kurs.java.cinefest.model;

import java.math.BigDecimal;

public record VIPPass(BigDecimal basePrice, String seat, BigDecimal cateringSurcharge, boolean backstageAccess)
implements FestivalTicket {}

package pl.kurs.java.cinefest.model;

import java.math.BigDecimal;
import java.util.List;

public record StandardTicket(BigDecimal basePrice, String seat, List<MovieShow> selectedShows)
        implements FestivalTicket {}

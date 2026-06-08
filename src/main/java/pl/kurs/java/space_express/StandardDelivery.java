package pl.kurs.java.space_express;

import java.math.BigDecimal;


public record StandardDelivery(BigDecimal baseCost, String destination, boolean isInterplanetary) implements
DeliveryMethod{

    @Override
    public BigDecimal getBaseCost() { return baseCost; }

    @Override
    public String getDestination() { return destination; }
}

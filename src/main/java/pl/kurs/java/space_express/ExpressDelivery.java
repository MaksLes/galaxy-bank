package pl.kurs.java.space_express;

import java.math.BigDecimal;

public record ExpressDelivery(BigDecimal baseCost, String destination, boolean instantTeleportation,
                              BigDecimal priorityFee) implements DeliveryMethod {

    @Override
    public BigDecimal getBaseCost() { return baseCost; }

    @Override
    public String getDestination() { return destination; }
}

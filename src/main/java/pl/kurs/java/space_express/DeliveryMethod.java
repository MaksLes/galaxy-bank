package pl.kurs.java.space_express;

import java.math.BigDecimal;

public sealed interface DeliveryMethod permits StandardDelivery, ExpressDelivery {
    BigDecimal getBaseCost();
    String getDestination();
}

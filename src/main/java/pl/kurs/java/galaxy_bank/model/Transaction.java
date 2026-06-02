package pl.kurs.java.galaxy_bank.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Transaction implements Comparable<Transaction>{

    private String id;
    private BigDecimal amount;
    private String sender;
    private LocalDateTime timestamp;

    @Override
    public int compareTo(Transaction other){
        return this.timestamp.compareTo(other.timestamp);
    }
}

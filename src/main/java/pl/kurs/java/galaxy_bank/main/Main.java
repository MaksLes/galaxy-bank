package pl.kurs.java.galaxy_bank.main;

import pl.kurs.java.galaxy_bank.model.Transaction;
import pl.kurs.java.galaxy_bank.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        //Tworzenie przykładowych transakcji (użycie builder z Lombok)
        Transaction t1 = Transaction.builder()
                .id("TX-001")
                .amount(new BigDecimal("250.00"))
                .sender("Mars Colony Alpha")
                .timestamp(LocalDateTime.of(2157, 3, 15, 10, 30))
                .build();

        Transaction t2 = Transaction.builder()
                .id("TX-002")
                .amount(new BigDecimal("1500.75"))
                .sender("Jupiter Station Gamma")
                .timestamp(LocalDateTime.of(2157, 3 ,15, 8, 0))
                .build();

        Transaction t3 = Transaction.builder()
                .id("TX-003")
                .amount(new BigDecimal("1500.75"))
                .sender("Earth HQ")
                .timestamp(LocalDateTime.of(2157, 3, 15, 12, 45))
                .build();

        List<Transaction> transactions = Arrays.asList(t1,t2,t3);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        //Zadanie 2: Test raportu StringBuilder
        TransactionService service = new TransactionService();
        String report = service.generateSenderReport(transactions);
        System.out.println("Raport nadawców");
        System.out.println(report);

        //Zadanie 1: Test naturalnego sortowania
        List<Transaction> sortedByDate = transactions.stream()
                .sorted()
                .toList();
        System.out.println("\nSortowanie naturalne po dacie, rosnąco");
        sortedByDate.forEach(t -> System.out.println(t.getTimestamp().format(dtf) + " | " + t.getSender()));

        //Zadanie 3: Zewnętrzny Comparator
        Comparator<Transaction> byAmountDescending = Comparator
                .comparing(Transaction::getAmount)
                .reversed();

        List<Transaction> sortedByAmount = transactions.stream()
                .sorted(byAmountDescending)
                .toList();

        System.out.println("\nSortowanie po kwocie (malejąco)");
        sortedByAmount.forEach(t -> System.out.println(t.getAmount() + " | " + t.getSender()));

        //Zadanie 4: Demonstracja compareTo() vs equals() dla BigDecimal
        BigDecimal a = new  BigDecimal("2.0");
        BigDecimal b = new BigDecimal("2.00");
        System.out.println("\nBigDecimal: equals() vs compareTo()");
        System.out.println("equals():   " + a.equals(b)); //false - rózna skala
        System.out.println("compareTo()==0: " + (a.compareTo(b) == 0)); // true - ta sama wartość
    }
}

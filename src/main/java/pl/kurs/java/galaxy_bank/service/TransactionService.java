package pl.kurs.java.galaxy_bank.service;

import pl.kurs.java.galaxy_bank.model.Transaction;
import java.util.List;

/**
 * =================================================================================================================
 * TEORIA PAMIĘCI - ODPOWIEDZI DO ZADANIA 4
 * =================================================================================================================
 *
 * 1. CO DZIEJE SIĘ NA STOSIE (STACK) PODCZAS WYWOŁANIA generateSenderReport()?
 *
 * Stos (Stack) w JVM przechowuje ramki wywołań metod (stack frames).
 * W momencie wywołania generateSenderReport():
 * Na wierzchołek stosu trafia nowa ramka zawierająca:
 * a) referencję do obiektu TransactionService (this),
 * b) referencję do parametru 'transactions' (lista),
 * c) zmienne lokalne: referencję do obiektu StringBuilder ('builder'), zmienną iteracyjną 'i' typu int
 * Wszystkie te dane żyją wyłącznie w stosie lub są do niego przyczepione przez referencje (same obiekty leżą na
 * Stercie / Heap).
 *
 * 2. ZASADA LIFO A ZWALNIANIE PAMIĘCI STOSU
 *
 * 3. compareTo() VS equals() DLA BigDecimal
 *
 * BigDecimal rozróżnia wartości numeryczne od ich reprezentacji.
 * Przykładowo: new BigDecimal("2.0").equals(new BigDecimal("2.00")) -> FALSE
 * Powodem jest to, że equals() porównuje WARTOŚĆ i SKALĘ.
 * "2.0" ma skalę 1, "2.00" ma skalę 2 co oznacza że obiekty są nierówne.
 * compareTo() porównuje wyłącznie wartośc matematyczną:
 * new BigDecimal("2.0").compareTo(newBigDecimal("2.00") -> 0
 * =================================================================================================================
 */

public class TransactionService {
    public String generateSenderReport(List<Transaction> transactions){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < transactions.size(); i++){
            builder.append(transactions.get(i).getSender());
            if(i < transactions.size()-1){
                builder.append(", ");
            }
        }

        return builder.toString();
    }
}

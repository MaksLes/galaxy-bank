package pl.kurs.java.cinefest.service;

import pl.kurs.java.cinefest.model.FestivalTicket;
import pl.kurs.java.cinefest.staff.CinemaStaff;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class ReservationService {

    private final String paymentRegistry; //Zewnętrzny rejestr płatności

    public void processReservation(CinemaStaff staff, FestivalTicket ticket){

        CinemaStaff resolvedStaff = Optional.ofNullable(staff)
                .filter(CinemaStaff::isOnDuty)
                .filter(s -> s.getSkillLevel() >= 3)
                .orElseGet(() -> CinemaStaff.builder()
                        .name("Domyślny bileter rezerwowy")
                        .skillLevel(3)
                        .build());

        Optional.ofNullable(resolvedStaff).ifPresentOrElse(
                s -> {
                    String reservedSeat = new StringBuilder(ticket.seat())
                            .reverse()
                            .toString();

                    int randomCode = (int) (Math.random() * 90_00) + 10_000;

                    StringBuilder sb = new StringBuilder();
                    sb.append(s.getName())
                            .append("-")
                            .append(reservedSeat)
                            .append("-")
                            .append(randomCode);

                    String verificationKey = sb.toString();
                    sb.setLength(0);

                    System.out.println("Weryfikacja zakończona sukcesem. Kod: " + verificationKey);
                },
                () -> {
                    throw new NoSuchElementException("Brak dostępnego biletera - rezerwacja niemożliwa!");
                }
        );
    }
}

package pl.kurs.java.cinefest;

import pl.kurs.java.cinefest.model.FestivalTicket;
import pl.kurs.java.cinefest.model.MovieShow;
import pl.kurs.java.cinefest.model.StandardTicket;
import pl.kurs.java.cinefest.model.VIPPass;
import pl.kurs.java.cinefest.service.ReservationService;
import pl.kurs.java.cinefest.service.TicketPriceCalculator;
import pl.kurs.java.cinefest.staff.CinemaStaff;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MovieShow film1 = new MovieShow("Dune: Part Three", 2.5, true);
        MovieShow film2 = new MovieShow("Sezon na misia", 3.0, false);

        FestivalTicket standard = new StandardTicket(
                new BigDecimal("45.00"), "A12", List.of(film1, film2)
        );

        FestivalTicket vip = new VIPPass(
                new BigDecimal("200.00"), "VIP-01", new BigDecimal("80.00"), true
        );

        TicketPriceCalculator calculator = new TicketPriceCalculator();
        calculator.calculateFinalPrice(standard);
        calculator.calculateFinalPrice(vip);

        ReservationService service = new ReservationService("https://payments.cinefest.pl");

        CinemaStaff qualified = CinemaStaff.builder()
                .name("Anna Kowalska")
                .skillLevel(5)
                .build();

        CinemaStaff unqualified = new CinemaStaff("Bartek Nowak", 2, false);

        System.out.println("Bileter wykwalifikowany");
        service.processReservation(qualified, standard);

        System.out.println("Bileter rezerwowy");
        service.processReservation(unqualified, vip);

        System.out.println("Przekazanie null");
        service.processReservation(null, standard);

        CinemaStaff staff = new CinemaStaff("Jan", 4, false);
        modidyStaff(staff);
        System.out.println("onDuty po moodifyStaff " + staff.isOnDuty());



    }
    public static void modidyStaff(CinemaStaff s){
        s = CinemaStaff.builder()
                .name(s.getName())
                .skillLevel(s.getSkillLevel())
                .onDuty(true)
                .build();
    }
}

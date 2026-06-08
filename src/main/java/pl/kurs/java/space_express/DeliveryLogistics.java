package pl.kurs.java.space_express;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class DeliveryLogistics {

    //@RequiedArgsConstructor generuje konstruktor przyjmujący to final-owe pole.
    private final AuthorizationService authorizationService;

    public void assignMission(SpaceCourier courier, DeliveryMethod method){
        StringBuilder codeBUilder = new StringBuilder();

        //Krok 1: filtrowaniel jeśli kurier nie przejdzie warunków
        // orElseGet() symuluje kosztownme pobranie kuriera rezerwowego z bazy.
        SpaceCourier verifiedCourier = Optional.ofNullable(courier)
                .filter(SpaceCourier::isActive)
                .filter(c -> c.getRating() != null && c.getRating() >= 4)
                .orElseGet(() -> {
                    log.warn("Kurier nie spełnia kryteriów misji. " + "Pobieranie kuriera rezerwowego z bazy danych...");
                    return fetchBackupCourier();
                });

        //Krok 2: wykonanie akcji na finalnym kurierze przez ifPresentOrElse.
        Optional.ofNullable(verifiedCourier)
                .ifPresentOrElse(
                        c -> {
                            //Generowanie kodu: employeeId + odwrócony cel (maskowanie) + losowa liczba
                            String reversedDestination = codeBUilder
                                    .append(method.getDestination())
                                    .reverse()
                                    .toString();
                            codeBUilder.setLength(0); //czyszczenie bufora po użyciu

                            String trackingCode = c.getEmployeeId() + "-" + reversedDestination + "-" + new Random()
                                    .nextInt(10_000);

                            log.info("Misja przydzielona | Kurier: {} {} | Kod: {}", c.getFirstName(), c.getLastName(), trackingCode);
                        },
                        () -> {
                            throw new IllegalStateException(
                                    "Krytyczny błąd systemu: brak dostępnego kuriera."
                            );
                        }
                );
    }

    //Symulacja kosztownego zapytania do zewnętrznej bazy danych.
    private SpaceCourier fetchBackupCourier(){
        log.info("[DB] Wykonuję kosztowne zapytanie do bazy kurierów rezerwowych...");
        return SpaceCourier.builder()
                .firstName("Backup")
                .lastName("Nowak")
                .rating(5)
                .employeeId("BACKUP-001")
                .build();

    }
}

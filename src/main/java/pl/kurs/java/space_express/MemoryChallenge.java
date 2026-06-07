package pl.kurs.java.space_express;

public class MemoryChallenge {
    public static void main(String[] args) {
        SpaceCourier courier = new SpaceCourier("Jan", "Kowalski", 3, "ID-991", true);
        optimizeRoute(courier);

        /** WYPISZE: 3 NIE 5.
        //
        // UZASADNIENIE
        // 1. Stack metody main: zmienna courier przechowuje REFERENCJĘ (adres
        //    w pamięci) do obiektu SpaceCourier{rating=3} znajdującego się na Heap.
        //
        // 2. Wywołanie optimizeRoute(courier): Java kopiuje WARTOŚĆ referencji
        //    do parametru `c` na stosie metody optimizeRoute.
        //    Na tym etapie `courier` i `c` wskazują TEN SAM obiekt na Heap.
        //
        // 3. Wewnątrz optimizeRoute: `c = SpaceCourier.builder()...rating(5).build()`
        //    → na Heap powstaje NOWY obiekt {rating=5}, a jego adres jest przypisany
        //    do LOKALNEJ zmiennej `c`. Zmienna `courier` w main NIE zostaje dotknięta
        //    — nadal wskazuje na pierwotny obiekt {rating=3}.
        //
        // 4. Zasada: Java to WYŁĄCZNIE pass-by-value.
        //    Dla typów referencyjnych przekazywana "wartość" to KOPIA referencji.
        //    Reassign lokalnego parametru (c = ...) nie wpływa na zmienną w wywołującym.
        //    Gdyby zamiast tego wywołać c.setRating(5), zmieniłoby to obiekt na Heap
        //    i wtedy courier.getRating() zwróciłoby 5.
         **/
        System.out.println(courier.getRating()); // Co tu się wypisze i dlaczego?
    }

    public static void optimizeRoute(SpaceCourier c) {
        // c to lokalna kopia referencji - przypisanie nowego obiektu widoczne jest tylko wewnątrz tej metody.
        c = SpaceCourier.builder()
                .firstName(c.getFirstName())
                .lastName(c.getLastName())
                .rating(5)
                .employeeId(c.getEmployeeId())
                .active(c.isActive())
                .build();
    }
}

package pl.kurs.java.cinefest.questions;

public class Questions {

    /**
     *================================================================================================================
     *                Pytanie A)
     *================================================================================================================
     * public static void main(String[] args) {
     *     CinemaStaff staff = new CinemaStaff("Jan", 4, false);
     *     modifyStaff(staff);
     *     System.out.println(staff.isOnDuty()); // Co wypisze i dlaczego?
     * }
     *
     * public static void modifyStaff(CinemaStaff s) {
     *     s = CinemaStaff.builder()
     *             .name(s.getName())
     *             .skillLevel(s.getSkillLevel())
     *             .onDuty(true)
     *             .build();
     * }
     *
     * Odp: Wypisze FALSE ponieważ Java działa na zasadzie Pass-by-Value to znaczy że do metody trafia kopia referencji
     * wskazującej na obiekt na stercie. Wewnątrz metody builder tworzy nowy obiekt na Stercie i przypisuje jego adres
     * do lokalnej zmiennej s na Stack.
     * ================================================================================================================
     *
     * ================================================================================================================
     *  Pytanie B)
     *  ===============================================================================================================
     *  Które porównania wypiszą true, a które false? Uzasadnij różnicę między r3 a r4.
     *
     * String r1 = "FESTIVAL-2026";
     * String r2 = "FESTIVAL" + "-2026";
     * String p = "FESTIVAL";
     * String r3 = p + "-2026";
     * String r4 = new String("FESTIVAL-2026").intern();
     *
     * System.out.println(r1 == r2); // ??? -> TRUE
     * System.out.println(r1 == r3); // ??? -> FALSE
     * System.out.println(r1 == r4); // ??? -> TRUE
     *
     * Odp: r3 powstaje przez konkatenację w runtime - zmienna p nie jest final, więc kompilator nie może rozwiązać
     * jej wartości w czasie kompilacji. JVM tworzy nowy obiekt przez StringBuilder i umieszcza go na Heap poza
     * String Pool. r4 również zaczyna jako nowy obiekt na Heap, ale wywołanie .intern() zwraca referencję do
     * istniejącego obiektu na String Pool. Różnica sprowadza się do tego że r3 zostaje na Heap, a r4 dzięki intern()
     * zostaje przekierowany do Pool'a
     *=================================================================================================================
     */
}

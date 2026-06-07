package pl.kurs.java.space_express;

import java.util.Objects;

public class StringPoolDiagnostic {
    public static void main(String[] args) {

        String token1 = "GALAXY-EXP";                       //literał -> trafia do String Pool
        String token2 = "GALAXY" + "-EXP";                  // obie strony to stałe literały -> kompilator wykonuje constant folding
                                                            // i tworzy "GALAXY-EXP" w Pool juz w czasie kompilacji
        String part1 = "GALAXY";                            // part1 jest zmienną -> konkatenacja w runtime
        String token3 = part1 + "-EXP";                     // przez StringBuilder -> nowy obiekt na Heap
                                                            // jawny new String() -> zawsze nowy obiekt na Heap
        String token4 = new String("GALAXY-EXP");    // nawet jeśli identyczny literał jest już w Pool
                                                            // intern() -> zwraca referencję z Pool
        String token5 = token4.intern();                    // lub umieszcza tam i zwraca, jeśli brak

        // TAK - constantfolding: kompilator scala dwa literały w jeden stały "GALAXY-EXP".
        // Oba tokeny wskazują ten sam obiekt w String Pool. Operator == porównuje referencje.
        System.out.println("Czy token1 == token2? " + (token1 == token2)); // TAK

        // NIE - part1 to zmienna (nie final), więc JVM wykonuje konkatenację w runtime
        // przy pomocy StringBuilder.append(...)toString(). Wynik to nowy obiekt na Heap,
        // który nie trafia automatycznie do Pool. token1 (Pool) != token3 (Heap)
        System.out.println("Czy token1 == token3? " + (token1 == token3)); // NIE

        // NIE - new String("...") zawsze alokuje oddzielny obiekt na Heap, ignorując Pool.
        // token1 (Pool) i token4 (Heap) to dwa odrębne obiekty w pamięci.
        System.out.println("Czy token1 == token4? " + (token1 == token4)); // NIE

        // TAK - intern() sprawdza Pool: "GALAXY-EXP" już tam istnieje więc zwraca tą samą referencję.
        System.out.println("Czy token1 == token5? " + (token1 == token5)); // TAK

        //Bezpieczne porównanie wartości z zabezpieczeniem przed null:
        // Objects.equals(a, b) zwraca false gdy a lub b jest null, dla wartości bez null'a deleguje do a.equals(b)
        //które porównuje treść nie referencje.
        System.out.println("Bezpieczne porównanie token4 i token1:");
        System.out.println(Objects.equals(token4, token1)); // true - treść identyczna
    }

    /** Odpowiedzi na pytania z GC
     * 1) W której części pamięci (Young Generation, Old Generation, czy Metaspace) wylądują metadane o strukturze
     * Twoich klas i rekordów, a gdzie same instancje obiektów utworzone przez new lub Builder?
     *
     *  ODP: Metadane klas wylądują w Metaspace a instancje obiektów wylądują na Heap
     *
     *  2) Wyobraź sobie, że w Twojej aplikacji logistycznej stworzyłeś klasę z polem
     *  private static final List<SpaceCourier> HISTORY = new ArrayList<>(); i zapomniałeś usuwać z niej
     *  nieużywanych kurierów. Dlaczego Garbage Collector (GC) nigdy nie usunie tych obiektów z pamięci?
     *  Jak nazywa się to zjawisko?
     *
     *  ODP: Pole static final jest GC Root czyli punktem startowym grafu osiągalności a GC usuwa
     *  tylko obiekty nieosiągalne. Zjawisko to nazywa się wyciekiem pamięci i może prowadzić do OutOfMemoryError
     */
}

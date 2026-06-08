package pl.kurs.java.space_express;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

import java.util.Comparator;

@Data
@AllArgsConstructor
@Builder
public class SpaceCourier implements Comparable<SpaceCourier> {

    private String firstName;
    private String lastName;
    private Integer rating;
    private String employeeId;

    @Builder.Default
    private boolean active = true;

    //Naturalne sortowanie malejąco wg. oceny (najwyższa pierwsza).
    @Override
    public int compareTo(SpaceCourier other) {
        return Integer.compare(other.rating, this.rating);
    }

    /**Komparator alfabetyczny: po nazwisku, potem po imieniu.
     * Kurierzy z null-owym nazwiskiem trafiają na koniec listy (nullsLast).
     */
    public static final Comparator<SpaceCourier> BY_NAME_COMPARATOR =
            Comparator.comparing(SpaceCourier::getLastName, Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(SpaceCourier::getFirstName, Comparator.nullsLast(Comparator.naturalOrder()));

}

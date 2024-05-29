import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class DateSorterTest {
    private final DateSorter dateSorter = new DateSorter();

    @Test
    public void testSortDates_MixedDates() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.of(2004, 7, 1),
                LocalDate.of(2005, 1, 2),
                LocalDate.of(2007, 1, 1),
                LocalDate.of(2032, 5, 3)
        );
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        List<LocalDate> expected = List.of(
                LocalDate.of(2005, 1, 2),
                LocalDate.of(2007, 1, 1),
                LocalDate.of(2032, 5, 3),
                LocalDate.of(2004, 7, 1)
        );
        assertEquals(expected, new ArrayList<>(sortedDates));
    }

    @Test
    public void testSortDates_AllDatesWithR() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.of(2004, 10, 1),
                LocalDate.of(2005, 11, 2),
                LocalDate.of(2007, 4, 1),
                LocalDate.of(2032, 12, 3)
        );
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        List<LocalDate> expected = List.of(
                LocalDate.of(2004, 10, 1),
                LocalDate.of(2005, 11, 2),
                LocalDate.of(2007, 4, 1),
                LocalDate.of(2032, 12, 3)
        );
        assertEquals(expected, new ArrayList<>(sortedDates));
    }

    @Test
    public void testSortDates_AllDatesWithoutR() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.of(2004, 5, 1),
                LocalDate.of(2005, 6, 2),
                LocalDate.of(2007, 7, 1),
                LocalDate.of(2032, 8, 3)
        );
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        List<LocalDate> expected = List.of(
                LocalDate.of(2032, 8, 3),
                LocalDate.of(2007, 7, 1),
                LocalDate.of(2005, 6, 2),
                LocalDate.of(2004, 5, 1)
        );
        assertEquals(expected, new ArrayList<>(sortedDates));
    }

    @Test
    public void testSortDates_EmptyList() {
        List<LocalDate> unsortedDates = List.of();
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        List<LocalDate> expected = List.of();
        assertEquals(expected, new ArrayList<>(sortedDates));
    }

    @Test
    public void testSortDates_SingleDateWithR() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.of(2004, 9, 1)
        );
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        List<LocalDate> expected = List.of(
                LocalDate.of(2004, 9, 1)
        );
        assertEquals(expected, new ArrayList<>(sortedDates));
    }

    @Test
    public void testSortDates_SingleDateWithoutR() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.of(2004, 6, 1)
        );
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        List<LocalDate> expected = List.of(
                LocalDate.of(2004, 6, 1)
        );
        assertEquals(expected, new ArrayList<>(sortedDates));
    }

    @Test
    public void testSortDates_DuplicateDatesWithR() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.of(2004, 10, 1),
                LocalDate.of(2004, 10, 1),
                LocalDate.of(2004, 10, 1)
        );
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        List<LocalDate> expected = List.of(
                LocalDate.of(2004, 10, 1),
                LocalDate.of(2004, 10, 1),
                LocalDate.of(2004, 10, 1)
        );
        assertEquals(expected, new ArrayList<>(sortedDates));
    }

    @Test
    public void testSortDates_DuplicateDatesWithoutR() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.of(2004, 6, 1),
                LocalDate.of(2004, 6, 1),
                LocalDate.of(2004, 6, 1)
        );
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        List<LocalDate> expected = List.of(
                LocalDate.of(2004, 6, 1),
                LocalDate.of(2004, 6, 1),
                LocalDate.of(2004, 6, 1)
        );
        assertEquals(expected, new ArrayList<>(sortedDates));
    }

    @Test
    public void testSortDates_BoundaryMonths() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.of(2004, 4, 30), // April
                LocalDate.of(2004, 5, 1),  // May
                LocalDate.of(2004, 9, 30), // September
                LocalDate.of(2004, 10, 1)  // October
        );
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        List<LocalDate> expected = List.of(
                LocalDate.of(2004, 4, 30),
                LocalDate.of(2004, 9, 30),
                LocalDate.of(2004, 10, 1),
                LocalDate.of(2004, 5, 1)
        );
        assertEquals(expected, new ArrayList<>(sortedDates));
    }

    @Test
    public void testSortDates_CombinationOfMonths() {
        List<LocalDate> unsortedDates = List.of(
                LocalDate.of(2004, 4, 1),  // April
                LocalDate.of(2004, 5, 1),  // May
                LocalDate.of(2004, 6, 1),  // June
                LocalDate.of(2004, 7, 1),  // July
                LocalDate.of(2004, 1, 1),  // January
                LocalDate.of(2004, 8, 1),  // August
                LocalDate.of(2004, 3, 1),  // March
                LocalDate.of(2004, 12, 1)  // December
        );
        Collection<LocalDate> sortedDates = dateSorter.sortDates(unsortedDates);
        List<LocalDate> expected = List.of(
                LocalDate.of(2004, 1, 1),
                LocalDate.of(2004, 3, 1),
                LocalDate.of(2004, 4, 1),
                LocalDate.of(2004, 12, 1),
                LocalDate.of(2004, 8, 1),
                LocalDate.of(2004, 7, 1),
                LocalDate.of(2004, 6, 1),
                LocalDate.of(2004, 5, 1)
        );
        assertEquals(expected, new ArrayList<>(sortedDates));
    }
}

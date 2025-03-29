package comparators;

import entities.*;
import java.util.*;

public class LandingComparator<E extends Airplane> implements Comparator<E> {
    public int compare(E a, E b) {
        if (a.getUrgent() || b.getUrgent()) {
            return b.getUrgent().compareTo(a.getUrgent());
        }
        return a.getWantedTime().compareTo(b.getWantedTime());
    }
}

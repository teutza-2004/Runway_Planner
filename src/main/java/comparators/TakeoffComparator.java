package comparators;

import entities.*;
import java.util.*;

public class TakeoffComparator<E extends Airplane> implements Comparator<E> {
    public int compare(E a, E b) {
        return a.getWantedTime().compareTo(b.getWantedTime());
    }
}

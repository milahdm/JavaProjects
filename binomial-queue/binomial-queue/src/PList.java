import java.util.function.BiPredicate;

/**
 * Persistent Linked List
 * A simple implementation of an immutable linked list.
 * Once created, the list cannot be modified, ensuring persistence.
 */
public class PList<T> {
    private final T data;           // The data stored in the current node
    private final PList<T> next;    // Reference to the next node in the list

    // Constructor for creating a new PList node
    public PList(T d, PList<T> nxt) {
        data = d;
        next = nxt;
    }

    /**
     * Converts the list into a string representation.
     * Displays all elements separated by commas.
     * @return String representation of the list
     */
    public String toString() {
        if (next == null) {
            return data.toString();
        } else {
            return data.toString() + ", " + next.toString();
        }
    }

    /**
     * Adds a new element to the front of the list.
     * @param first The element to add
     * @param rest The rest of the list
     * @param <T> The type of elements in the list
     * @return A new list with the element added to the front
     */
    public static <T> PList<T> addFront(T first, PList<T> rest) {
        if (first == null) {
            return rest;
        } else {
            return new PList<>(first, rest);
        }
    }

    /**
     * Retrieves the first element of the list.
     * @param n The list to retrieve from
     * @param <T> The type of elements in the list
     * @return The first element of the list
     */
    public static <T> T getFirst(PList<T> n) {
        return n.data;
    }

    /**
     * Retrieves the next node of the list.
     * @param n The list to retrieve from
     * @param <T> The type of elements in the list
     * @return The next node in the list
     */
    public static <T> PList<T> getNext(PList<T> n) {
        return n.next;
    }

    /**
     * Removes the first occurrence of an element from the list.
     * @param n The element to remove
     * @param ns The list to remove from
     * @param <E> The type of elements in the list
     * @return A new list without the specified element
     */
    public static <E> PList<E> remove(E n, PList<E> ns) {
        if (ns == null) {
            return null;
        } else {
            if (getFirst(ns) == n) {
                return getNext(ns);
            } else {
                return addFront(getFirst(ns), remove(n, getNext(ns)));
            }
        }
    }

    /**
     * Reverses the list.
     * @param ns The list to reverse
     * @param out The accumulator for the reversed list
     * @param <E> The type of elements in the list
     * @return The reversed list
     */
    public static <E> PList<E> reverse(PList<E> ns, PList<E> out) {
        if (ns == null) {
            return out;
        } else {
            return reverse(getNext(ns), addFront(getFirst(ns), out));
        }
    }

    /**
     * Finds the maximum element in the list based on a comparator.
     * @param ns The list to search
     * @param lessEq A comparator that defines the order
     * @param <E> The type of elements in the list
     * @return The maximum element in the list
     */
    public static <E> E find_max(PList<E> ns, BiPredicate<E, E> lessEq) {
        if (ns == null) {
            return null;
        } else {
            return find_max_helper(getNext(ns), getFirst(ns), lessEq);
        }
    }

    /**
     * Helper function for finding the maximum element in the list.
     * @param ns The remaining list to search
     * @param best The current best element
     * @param lessEq A comparator that defines the order
     * @param <E> The type of elements in the list
     * @return The maximum element in the list
     */
    public static <E> E find_max_helper(PList<E> ns, E best, BiPredicate<E, E> lessEq) {
        if (ns == null) {
            return best;
        } else if (lessEq.test(getFirst(ns), best)) {
            return find_max_helper(getNext(ns), best, lessEq);
        } else {
            return find_max_helper(getNext(ns), getFirst(ns), lessEq);
        }
    }
}

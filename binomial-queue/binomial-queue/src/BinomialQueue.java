import java.util.function.BiPredicate;

/**
 * Represents a Binomial Heap Node.
 * Each node maintains a key, height, children (subtrees), and a comparator for ordering.
 *
 * @param <K> The type of the keys.
 */
class BinomialHeap<K> {
    K key;                          // Key of the node
    int height;                     // Height of the tree rooted at this node
    PList<BinomialHeap<K>> children; // Children of the node as a persistent linked list
    BiPredicate<K, K> lessEq;       // Comparator to enforce the heap property

    // Constructor
    BinomialHeap(K k, int h, PList<BinomialHeap<K>> kids, BiPredicate<K, K> le) {
        this.key = k;
        this.height = h;
        this.children = kids;
        this.lessEq = le;
    }

    /**
     * Links this heap with another heap of the same height, forming a single larger heap.
     * @param other The other heap to link with.
     * @return A new BinomialHeap resulting from the link operation.
     */
    BinomialHeap<K> link(BinomialHeap<K> other) {
        if (this.height != other.height) {
            throw new UnsupportedOperationException("Attempt to link trees of different height.");
        }

        if (lessEq.test(this.key, other.key)) {
            PList<BinomialHeap<K>> kids = PList.addFront(this, other.children);
            return new BinomialHeap<>(other.key, other.height + 1, kids, lessEq);
        } else {
            PList<BinomialHeap<K>> kids = PList.addFront(other, this.children);
            return new BinomialHeap<>(this.key, this.height + 1, kids, lessEq);
        }
    }

    /**
     * Checks whether this heap satisfies the heap property.
     * @return True if the heap property is satisfied; otherwise, false.
     */
    boolean isHeap() {
        if (this.children == null) {
            return true;
        }

        PList<BinomialHeap<K>> curr = this.children;
        while (curr != null) {
            BinomialHeap<K> child = PList.getFirst(curr);
            if (!lessEq.test(child.key, this.key)) {
                return false;
            }
            if (!child.isHeap()) {
                return false;
            }
            curr = PList.getNext(curr);
        }
        return true;
    }

    @Override
    public String toString() {
        String ret = "(" + key.toString();
        if (children != null) {
            ret += " " + children.toString();
        }
        return ret + ")";
    }
}

/**
 * Represents a Binomial Queue (a collection of Binomial Heaps).
 *
 * @param <K> The type of the keys.
 */
public class BinomialQueue<K> {
    PList<BinomialHeap<K>> forest;  // Forest of binomial heaps
    BiPredicate<K, K> lessEq;      // Comparator to enforce heap property

    // Constructor
    public BinomialQueue(BiPredicate<K, K> le) {
        forest = null;
        lessEq = le;
    }

    /**
     * Inserts a new key into the binomial queue.
     * @param key The key to insert.
     */
    public void push(K key) {
        BinomialHeap<K> heap = new BinomialHeap<>(key, 0, null, lessEq);
        this.forest = insert(heap, this.forest);
    }

    /**
     * Removes and returns the maximum key from the binomial queue.
     * @return The maximum key.
     */
    public K pop() {
        BinomialHeap<K> max = PList.find_max(this.forest, (h1, h2) -> this.lessEq.test(h1.key, h2.key));
        this.forest = PList.remove(max, this.forest);
        PList<BinomialHeap<K>> kids = PList.reverse(max.children, null);
        this.forest = merge(this.forest, kids);
        return max.key;
    }

    /**
     * Checks whether the queue is empty.
     * @return True if the queue is empty; otherwise, false.
     */
    public boolean isEmpty() {
        return forest == null;
    }

    /**
     * Checks whether the binomial queue satisfies the heap property.
     * @return True if the heap property is satisfied; otherwise, false.
     */
    public boolean isHeap() {
        PList<BinomialHeap<K>> curr = this.forest;
        while (curr != null) {
            BinomialHeap<K> first = PList.getFirst(curr);
            if (!first.isHeap()) {
                return false;
            }
            curr = PList.getNext(curr);
        }
        return true;
    }

    @Override
    public String toString() {
        return forest == null ? "" : forest.toString();
    }

    /**
     * Inserts a node into a sorted list of binomial heaps, maintaining the order by height.
     * @param n The node to insert.
     * @param ns The list of heaps to insert into.
     * @param <K> The type of the keys.
     * @return A new sorted list of heaps including the node.
     */
    static <K> PList<BinomialHeap<K>> insert(BinomialHeap<K> n, PList<BinomialHeap<K>> ns) {
        if (ns == null || n.height < PList.getFirst(ns).height) {
            return PList.addFront(n, ns);
        } else if (n.height > PList.getFirst(ns).height) {
            return PList.addFront(PList.getFirst(ns), insert(n, PList.getNext(ns)));
        } else {
            BinomialHeap<K> merged = n.link(PList.getFirst(ns));
            return insert(merged, PList.getNext(ns));
        }
    }

    /**
     * Merges two sorted lists of binomial heaps into one sorted list.
     * @param ns1 The first list of heaps.
     * @param ns2 The second list of heaps.
     * @param <K> The type of the keys.
     * @return A merged and sorted list of heaps.
     */
    static <K> PList<BinomialHeap<K>> merge(PList<BinomialHeap<K>> ns1, PList<BinomialHeap<K>> ns2) {
        if (ns1 == null) {
            return ns2;
        }
        if (ns2 == null) {
            return ns1;
        }
        if (PList.getFirst(ns1).height < PList.getFirst(ns2).height) {
            return PList.addFront(PList.getFirst(ns1), merge(PList.getNext(ns1), ns2));
        } else {
            return PList.addFront(PList.getFirst(ns2), merge(ns1, PList.getNext(ns2)));
        }
    }
}

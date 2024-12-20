import org.junit.jupiter.api.Test;
//import sequences.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;

public class StudentTest {

    @Test
    public void test() {
        testEmptyArray();
        testSingleElement();
        testSortedArray();
        testReverseSortedArray();
        testCharacterArray();
        testDuplicates();
        testIterSwap();
    }

    private void testEmptyArray() {
        ArraySequence<Integer> sequence = new ArraySequence<>(new Integer[]{});
        QuickSort.quicksort(sequence.begin(), sequence.end());
        assertTrue(isSorted(sequence.begin(), sequence.end()));
    }

    private void testSingleElement() {
        ArraySequence<Integer> sequence = new ArraySequence<>(new Integer[]{1});
        QuickSort.quicksort(sequence.begin(), sequence.end());
        assertTrue(isSorted(sequence.begin(), sequence.end()));
    }

    private void testSortedArray() {
        ArraySequence<Integer> sequence = new ArraySequence<>(new Integer[]{1, 2, 3, 4, 5});
        QuickSort.quicksort(sequence.begin(), sequence.end());
        assertTrue(isSorted(sequence.begin(), sequence.end()));
    }

    private void testReverseSortedArray() {
        ArraySequence<Integer> sequence = new ArraySequence<>(new Integer[]{5, 4, 3, 2, 1});
        QuickSort.quicksort(sequence.begin(), sequence.end());
        assertTrue(isSorted(sequence.begin(), sequence.end()));

        Iterator<Integer> i = sequence.begin();
        Iterator<Integer> end = sequence.end();
        while (!i.equals(end)) {
            System.out.println(i.get());
            i.advance();
        }
    }

    private void testCharacterArray() {
        ArraySequence<Character> sequence = new ArraySequence<>(new Character[]{'e', 'd', 'c', 'b', 'a'});
        QuickSort.quicksort(sequence.begin(), sequence.end());
        assertTrue(isSorted(sequence.begin(), sequence.end()));
    }

    private void testDuplicates() {
        ArraySequence<Integer> sequence = new ArraySequence<>(new Integer[]{2, 3, 2, 3, 2});
        QuickSort.quicksort(sequence.begin(), sequence.end());
        assertTrue(isSorted(sequence.begin(), sequence.end()));
    }

    private void testIterSwap() {
        ArraySequence<Integer> sequence = new ArraySequence<>(new Integer[]{2, 1});
        Iterator<Integer> begin = sequence.begin();
        Iterator<Integer> next = sequence.begin().clone();
        next.advance();
        Algorithms.iter_swap(begin, next);
        assertTrue(begin.get().equals(1) && next.get().equals(2));
    }

    private <E extends Comparable<E>> boolean isSorted(Iterator<E> begin, Iterator<E> end) {
        if (!begin.equals(end)) {
            Iterator<E> prev = begin.clone();
            Iterator<E> current = begin.clone();
            current.advance();
            while (!current.equals(end)) {
                if (prev.get().compareTo(current.get()) > 0) {
                    return false;
                }
                prev.advance();
                current.advance();
            }
        }
        return true;
    }
}

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiPredicate;

public class StudentTest {

    BiPredicate<Integer,Integer> lessEq = (Integer x, Integer y) -> x <= y;

    @Test
    public void test() {
        testInsertAndIsHeap();
        testMergeAndIsHeap();
        testPushPopLargeSequence();
        testDuplicateKeys();

        testPListReverse();
        testPListFindMax();
        testPListAddRemove();

        testHeightAfterSingleInsertion();
        testHeightAfterMergingTwoHeaps();
        testHeightWithMultipleMerges();
    }

    private void testInsertAndIsHeap() {
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        queue.push(50);
        queue.push(30);
        queue.push(20);
        assertTrue(queue.isHeap(), "Queue should maintain heap property after inserts.");

        // Ensuring the max element is returned and the heap property is maintained post-pop
        Integer max = queue.pop();
        assertEquals(Integer.valueOf(50), max, "Pop should return the max element.");
        assertTrue(queue.isHeap(), "Queue should maintain heap property after pop.");
    }

    private void testMergeAndIsHeap() {
        BinomialQueue<Integer> queue1 = new BinomialQueue<>(lessEq);
        BinomialQueue<Integer> queue2 = new BinomialQueue<>(lessEq);
        queue1.push(100);
        queue1.push(50);
        queue2.push(75);
        queue2.push(25);

        PList<BinomialHeap<Integer>> mergedForest = BinomialQueue.merge(queue1.forest, queue2.forest);
        BinomialQueue<Integer> mergedQueue = new BinomialQueue<>(lessEq);
        mergedQueue.forest = mergedForest;

        assertTrue(mergedQueue.isHeap(), "Merged queue should satisfy the heap property.");
    }

    private void testPushPopLargeSequence() {
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        for (int i = 1; i <= 1000; i++) {
            queue.push(i);
        }

        for (int expectedMax = 1000; expectedMax >= 1; expectedMax--) {
            Integer max = queue.pop();
            assertEquals(Integer.valueOf(expectedMax), max, "Pop should return the next max element in sequence.");
        }
    }

    private void testDuplicateKeys() {
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        queue.push(40);
        queue.push(40); // Duplicate key
        queue.push(30);
        assertTrue(queue.isHeap(), "Queue should maintain heap property with duplicate keys.");

        Integer firstMax = queue.pop();
        assertEquals(Integer.valueOf(40), firstMax, "First pop should return one of the duplicate max elements.");

        Integer secondMax = queue.pop();
        assertEquals(Integer.valueOf(40), secondMax, "Second pop should return the other duplicate max element.");
    }

    private void testPListReverse() {
        PList<Integer> list = PList.addFront(1, PList.addFront(2, PList.addFront(3, null)));
        PList<Integer> reversedList = PList.reverse(list, null);
        assertEquals(Integer.valueOf(3), PList.getFirst(reversedList), "First element should be 3 after reverse.");
        assertEquals(Integer.valueOf(2), PList.getFirst(PList.getNext(reversedList)), "Second element should be 2 after reverse.");
        assertEquals(Integer.valueOf(1), PList.getFirst(PList.getNext(PList.getNext(reversedList))), "Third element should be 1 after reverse.");
    }

    private void testPListFindMax() {
        PList<Integer> list = PList.addFront(10, PList.addFront(20, PList.addFront(5, null)));
        Integer max = PList.find_max(list, lessEq);
        assertEquals(Integer.valueOf(20), max, "Should find the maximum element correctly.");
    }

    private void testPListAddRemove() {
        PList<Integer> list = PList.addFront(1, null);
        list = PList.addFront(2, list);
        list = PList.remove(1, list);
        assertEquals(Integer.valueOf(2), PList.getFirst(list), "Element should be removed correctly.");
    }

    private void testHeightAfterSingleInsertion() {
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        queue.push(10);
        BinomialHeap<Integer> rootHeap = PList.getFirst(queue.forest);
        assertEquals(0, rootHeap.height, "Height should be 0 after single insertion.");
    }

    private void testHeightAfterMergingTwoHeaps() {
        BinomialQueue<Integer> queue1 = new BinomialQueue<>(lessEq);
        BinomialQueue<Integer> queue2 = new BinomialQueue<>(lessEq);
        queue1.push(10);
        queue2.push(20);

        PList<BinomialHeap<Integer>> mergedForest = BinomialQueue.merge(queue1.forest, queue2.forest);
        BinomialHeap<Integer> mergedRootHeap = PList.getFirst(mergedForest);
        assertEquals(1, mergedRootHeap.height, "Height should be 1 after merging two single-element heaps.");
    }


    private void testHeightWithMultipleMerges() {
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        for (int i = 1; i <= 4; i++) {
            queue.push(i);
        }
        BinomialHeap<Integer> rootHeap = PList.getFirst(queue.forest);
        assertEquals(2, rootHeap.height, "Height should be 2 after inserting 4 elements.");
    }


}
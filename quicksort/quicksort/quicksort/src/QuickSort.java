import java.util.Random;

public class QuickSort {

    private static final Random rand = new Random();


    public static <E extends Comparable<? super E>>
    void quicksort(Iterator<E> begin, Iterator<E> end)
    {
        // begin is null
        if(begin == null)
        {
            return;
        }
        Iterator<E> next = begin.clone();
        next.advance();
        // base case, 1 item
        if(begin.equals(end))
        {
            return;
        }

        E pivot = Algorithms.last(begin, end).get(); // choose a random pivot with function
        Iterator<E> partitionPoint = partition(begin, end, pivot); // returns iterator at the partition point

        quicksort(begin, partitionPoint); // sort from beginning to partition point

        Iterator<E> newStart = partitionPoint.clone();
        newStart.advance();
        quicksort(newStart, end);


    }




    private static <E extends Comparable<? super E>> Iterator<E> partition(Iterator<E> begin, Iterator<E> end, E pivot) {
        Iterator<E> left = begin.clone();
        Iterator<E> right = begin.clone();

        while (!right.equals(Algorithms.last(begin,end))) {
            if (right.get().compareTo(pivot) < 1) {
                Algorithms.iter_swap(left, right);
                left.advance();
            }

            right.advance();
        }


        Algorithms.iter_swap(Algorithms.last(begin, end), left);

        return left;
    }
}




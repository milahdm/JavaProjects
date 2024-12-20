public interface ReverseSequence<T> {
    // The rbegin() method returns an iterator whose position is the last element
    // of the sequence, if there is one. Otherwise, it's position is the same as rend().
    public ReverseIterator<T> rbegin();

    // The rend() method returns an iterator whose position is conceptually one prior
    // to the first element.
    public ReverseIterator<T> rend();
}

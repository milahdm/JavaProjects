public interface ReverseIterator<T> {

        // The get() method returns the element at the current position.
        public T get();

        // The retreat() method moves the iterator to the previous position.
        public void retreat();

        // The equals() method tests whether this iterator is at the same position
        // as the other iterator.
        public boolean equals(Object other);

        // The clone() method creates a new iterator at the same position as this iterator.
        public Iterator<T> clone();
}

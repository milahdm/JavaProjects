public interface Iterator<T> {
	
	// The get() method returns the element at the current position.
    public T get();
    
    // The advance() method moves the iterator to the next position.
    public void advance();
    
    // The equals() method tests whether this iterator is at the same position
    // as the other iterator.
    @Override
    public boolean equals(Object other);
    
    // The clone() method creates a new iterator at the same position as this iterator.
    public Iterator<T> clone();

}
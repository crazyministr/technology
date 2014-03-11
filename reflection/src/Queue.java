public interface Queue<E> {
    void add(E elem); // Appends the element to the end of this queue
    E first(); // Returns the first element in this queue
    boolean contains(E elem); // Returns {@code true} if this queue contains the specified element.
    E poll(); // Returns and removes the head (first element) of this queue.
}

import java.util.NoSuchElementException;

public class CircleQueue<E> implements Queue<E> {
    private Node<E> tail = null;
    private int count = 0;

    /**
     * Appends the element to the end of this queue
     * @param elem element to be appended to this queue
     */
    @Override
    public void add(E elem) {
        count++;
        if (tail == null) {
            tail = new Node<E>(null, elem);
            tail.next = tail;
        } else {
            Node<E> newTail = new Node<E>(tail.next, elem);
            tail.next = newTail;
            tail = newTail;
        }
    }

    /**
     * @return the first element in this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public E first() {
        if (empty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return tail.next.element;
    }

    /**
     * Returns {@code true} if this queue contains the specified element.
     *
     * @param elem element whose presence in this queue is to be tested
     * @return {@code true} if this queue contains the specified element
     */
    @Override
    public boolean contains(E elem) {
        if (empty()) {
            return false;
        }
        int cnt = 0;
        for (Node<E> x = tail; cnt < count; x = x.next, cnt++) {
            if (x.element.equals(elem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns and removes the head (first element) of this queue.
     *
     * @return the head of this queue
     */
    @Override
    public E poll() {
        if (empty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        count--;
        Node<E> head = tail.next;
        E result = head.element;
        tail.next = head.next;
        return result;
    }

    /**
     * @return {@code true} if this queue is empty
     */
    private boolean empty() {
        return count == 0;
    }

    /**
     * Class contains element of queue
     */
    private class Node<E> {
        Node<E> next;
        E element;

        public Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }
    }
}

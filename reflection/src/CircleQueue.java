import java.util.NoSuchElementException;

public class CircleQueue<E> implements Queue<E> {
    private Node<E> first = null;
    private Node<E> last = null;

    /**
     * Appends the element to the end of this queue
     * @param elem element to be appended to this queue
     */
    @Override
    public void add(E elem) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(l, elem, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
    }

    /**
     * @return the first element in this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public E first() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.item;
    }

    /**
     * Returns {@code true} if this queue contains the specified element.
     *
     * @param elem element whose presence in this queue is to be tested
     * @return {@code true} if this queue contains the specified element
     */
    @Override
    public boolean contains(E elem) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item.equals(elem)) {
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
        final E elem = first();
        final Node<E> next = first.next;
        first.item = null;
        first.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        return elem;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}

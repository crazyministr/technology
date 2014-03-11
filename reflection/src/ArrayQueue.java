import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private E[] queue;
    private int head;
    private int tail;
    private int count;

    public ArrayQueue() {
        this(42);
    }

    public ArrayQueue(int size) {
        head = 0;
        tail = 0;
        count = 0;
        queue = (E[]) new Object[size];
    }

    /**
     * Appends the element to the end of this queue
     * @param elem element to be appended to this queue
     */
    @Override
    public void add(E elem) {
        if (count == queue.length) {
            throw new IndexOutOfBoundsException("Queue full");
        }
        queue[tail] = elem;
        tail = (tail + 1) % queue.length;
    }

    /**
     * @return the first element in this queue
     */
    @Override
    public E first() {
        if (empty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue[head];
    }

    /**
     * Returns {@code true} if this queue contains the specified element.
     *
     * @param s start number of queue
     * @param f finish number of queue
     * @param x element whose presence in this queue is to be tested
     * @return {@code true} if this queue contains the specified element
     */
    private boolean inside(int s, int f, E x) {
        for (int i = s; i <= f; i++) {
            if (queue[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns {@code true} if this queue contains the specified element.
     *
     * @param elem element whose presence in this queue is to be tested
     * @return {@code true} if this queue contains the specified element
     */
    @Override
    public boolean contains(E elem) {
        return head <= tail ? inside(head, tail, elem) : inside(0, tail, elem) || inside(head, queue.length - 1, elem);
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
        E q = queue[head];
        head = (head + 1) % queue.length;
        return q;
    }

    /**
     * @return {@code true} if this queue is empty
     */
    private boolean empty() {
        return count == 0;
    }
}

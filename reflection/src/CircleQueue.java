import java.util.NoSuchElementException;

public class CircleQueue<E> implements Queue<E> {
    private final int defaultSize = 42;
    private E[] queue;
    private int head;
    private int tail;
    private int count = 0;

    public CircleQueue() {
        this(42);
    }

    public CircleQueue(int size) {
        head = 0;
        tail = 0;
        count = 0;
        queue = (E[]) new Object[size];
    }

    /**
     * Creates a new array to store the contents of the queue with twice the capacity of the old one.
     */
    private void resize() {
        E[] newArray = (E[]) new Object[queue.length << 1];
        for (int i = 0; i < count; i++) {
            newArray[i] = queue[head];
            head = (head + 1) % queue.length;
        }
        head = 0;
        tail = count;
        queue = newArray;
    }

    /**
     * Appends the element to the end of this queue
     * @param elem element to be appended to this queue
     */
    @Override
    public void add(E elem) {
        if (count == queue.length) {
            resize();
        }
        queue[tail] = elem;
        tail = (tail + 1) % queue.length;
        count++;
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
        E result = queue[head];
        head = (head + 1) % queue.length;
        count--;
        return result;
    }

    /**
     * @return {@code true} if this queue is empty
     */
    private boolean empty() {
        return count == 0;
    }
}

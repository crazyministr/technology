public class ArrayQueue<E> implements Queue<E> {
    private E[] queue;
    private int head;
    private int tail;
    private int size;

    public ArrayQueue() {
        this(42);
    }

    public ArrayQueue(int size) {
        head = 0;
        tail = 0;
        this.size = size;
        queue = (E[]) new Object[size];
    }


    /**
     * Appends the element to the end of this queue
     * @param elem element to be appended to this queue
     */
    @Override
    public void add(E elem) {
        queue[tail++] = elem;
        if (tail == size) {
            tail = 0;
        }
    }

    /**
     * @return the first element in this queue
     */
    @Override
    public E first() {
        return queue[head];
    }

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
        return head <= tail ? inside(head, tail, elem) : inside(0, tail, elem) || inside(head, size - 1, elem);
    }

    /**
     * Returns and removes the head (first element) of this queue.
     *
     * @return the head of this queue
     */
    @Override
    public E poll() {
        E q = queue[head++];
        if (head == size) {
            head = 0;
        }
        return q;
    }
}

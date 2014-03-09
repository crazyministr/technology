public interface Queue<E> {
    void add(E elem);
    E first();
    boolean contains(E elem);
    E poll();
}

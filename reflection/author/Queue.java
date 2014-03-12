package queue;

public interface Queue<E> {
	/**
	 * Добавление нового элемента в конец очереди
	 * @param elem	Новый элемент
	 */
    void add(E elem);
    
    /**
     * Выдает первый элемент из очереди, не удаляя его
     * @return	Первый элемент
     */
    E first();
    
    /**
     * Проверяет, есть ли заданный элемент в очереди
     * @param elem	Проверяемый элемент
     * @return		true, если элемент есть в очереди, false в противном случае.
     */
    boolean contains(E elem);
    
    /**
     * Удаляет из очереди первый элемент и выдает его в качестве результата.
     * @return	Удаленный первый элемент
     */
    E poll();
}
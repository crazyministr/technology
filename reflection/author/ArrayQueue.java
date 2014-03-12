package queue;

/**
 * Author: Alexandre Koubenski
 *
 * Реализация представления очереди в виде массива элементов
 *
 */
public class ArrayQueue<E> implements Queue<E> {
	E[] body;  // массив элементов очереди
	int head = 0,  // указатель на головной элемент очереди
			count = 0;  // счетчик числа элементов в очереди

	/**
	 * Конструктор очереди на заданное количество элементов
	 * @param maxSize - количество элементов
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue(int maxSize) {
		body = (E[]) new Object[maxSize];
	}

	/**
	 * Конструктор очереди на 100 элементов
	 */
	public ArrayQueue() { this(100); }

	/**
	 * Удаление элемента из очереди
	 * @return удаленный элемент
	 */
	@Override
	public E poll() {
		if (count == 0) throw new IndexOutOfBoundsException();
		count--;
		if (head == body.length) head = 0;
		return body[head++];
	}

	/**
	 * Постановка элемента в очередь.
	 * @param obj - элемент, который мы ставим в очередь.
	 */
	@Override
	public void add(E obj) {
		if (count == body.length) {
			throw new RuntimeException("Queue overflow");
		}
		body[(head+count) % body.length] = obj;
		count++;
	}

	/**
	 * Выдача первого элемента в очереди без его удаления
	 * @return первый элемент из очереди
	 */
	@Override
	public E first() {
		// Проверка пустоты очереди; если пуста - возникает исключительная ситуация.
		if (count == 0) throw new IndexOutOfBoundsException();
		// Если указатель head сдвинут за пределы массива, устанавливаем его в начало
		if (head == body.length) head = 0;
		// выдача первого элемента
		return body[head];
	}

	@Override
	public boolean contains(E elem) {
		for (int i = head; i < (head+count); ++i) {
			E element = body[i % body.length];
			if (elem == null && element == null) {
				return true;
			} else if (element != null && element.equals(elem)) {
				return true;
			}
		}
		return false;
	}
}

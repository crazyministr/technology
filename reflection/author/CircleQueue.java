package queue;

/**
 * Реализация очереди в виде кольцевого списка элементов
 *
 * @param <E> Тип элементов очереди
 */
public class CircleQueue<E> implements Queue<E> {

	/**
	 * Элемент кольцевого списка
	 * @param <E> Тип содержимого элемента
	 */
    private static class ListElement<E> {

        private E info = null;			// Содержимое элемента списка
        private ListElement<E> next = null;	// Ссылка на следующий элемент

        public ListElement(E info, ListElement<E> next) {
            this.info = info;
            this.next = next;
        }
        
        public ListElement(E info) {
        	this(info, null);
        }
    }
    
    // Указатель на последний элемент очереди, который
    // замыкается на первый элемент.
    private ListElement<E> tail = null;

    @Override
    public void add(E elem) {
        if (tail == null) {
            tail = new ListElement<E>(elem);
            tail.next = tail;
        } else {
        	tail.next = new ListElement<E>(elem, tail.next);
            tail = tail.next;
        }
    }

    @Override
    public E first() {
        if (tail == null) {
            throw new RuntimeException("Queue underflow");
        } else {
            return tail.next.info;
        }
    }

    @Override
    public boolean contains(E elem) {
        if (tail == null) {
            return false;
        }
        ListElement<E> test = tail.next;
        do {
        	if (elem == null && test.info == null) {
        		return true;
        	} else if (elem != null && elem.equals(test.info)) {
                return true;
            }
            test = test.next;
        } while (!test.equals(tail.next));
        return false;
    }

    @Override
    public E poll() {
        if (tail == null) {
            throw new RuntimeException("Queue underflow");
        } else {
        	E result = tail.next.info;
        	if (tail.next == tail) {
	            tail = null;
	        } else {
	            tail.next = tail.next.next;
	        }
        	return result;
        }
    }
}
/**
 * @autor Малашенков Антон
 * @group 2743
 *
 * Вариант 2. (К - М). Интерфейс простой очереди типа FIFO имеет следующее описание:
 * interface Queue<E> {
 * void add(E elem);   // Добавляет элемент в очередь.
 * E first();          // Выдает первый элемент из очереди, не удаляя его
 * boolean contains(E elem); // Проверяет, есть ли элемент в очереди.
 * E poll();           // Удаляет из очереди первый элемент и выдает его в качестве результата.
 * }
 * Надо написать две реализации этого интерфейса - в виде массива элементов (ArrayQueue)
 * и в виде кольцевого списка элементов (CircleQueue).
 * Программа запускается с параметрами - имя класса реализации и (в случае реализации в виде массива)
 * максимальный размер очереди (массива).
 * Программа должна динамически загрузить класс с нужной реализацией и замерить время выполнения
 * достаточно большой последовательности из операций добавления и удаления элементов
 * (скажем, для оценки производительности реализации).
 */
import java.util.Random;

public class Main {

    private static long run(Class<?> clazz, Object object, int size) throws Exception {
        Random rnd = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            clazz.getMethod("add", Object.class).invoke(object, rnd.nextInt(13));
        }
        for (int i = 0; i < size; i++) {
            clazz.getMethod("first").invoke(object);
        }
        for (int i = 0; i < size; i++) {
            clazz.getMethod("contains", Object.class).invoke(object, rnd.nextInt(13));
        }
        for (int i = 0; i < size; i++) {
            clazz.getMethod("poll").invoke(object);
        }
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1 || args.length > 2) {
            System.out.println("[ERROR] Incorrect number arguments");
            return;
        }
        if (args[0].equals("ArrayQueue")) {
            if (args.length < 2 || args[1].equals("")) {
                System.out.println("[ERROR] Argument #2 not found");
                return;
            }
            Class<?> clazz = ArrayQueue.class;
            Object object = clazz.getConstructor(int.class).newInstance(Integer.parseInt(args[1]));
            System.out.println("ArrayQueue: " + run(clazz, object, Integer.parseInt(args[1])) + " ms");
        } else if (args[0].equals("CircleQueue")) {
            Class<?> clazz = CircleQueue.class;
            Object object = clazz.newInstance();
            System.out.println("CircleQueue: " + run(clazz, object, 1000000) + " ms");
        } else {
            System.out.println("[ERROR] Class not found");
        }
    }
}

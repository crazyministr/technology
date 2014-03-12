package queue;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Main {
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
    	// Количество повторений цикла добавлений / удалений.
    	final int REPEAT = 5000000;
    	
    	if (args.length == 0) {
    		System.out.println("Usage: Main <class name> [<maximal size>]");
    		System.exit(-1);
    	}
        // по первому параметру вызывается нужный класс, точнее записывается в переменную имя
        String nameOfClass = "queue." + args[0];
        Queue<Integer> queue = null;
        int size = 0;
        if (args.length > 1) {
        	try {
        		size = Integer.valueOf(args[1]);
        	} catch (NumberFormatException ex) {
        		System.out.println("Size is not a number");
        		System.exit(-2);
        	}
        	if (size <= 0) {
        		System.out.println("Non-positive size");
        		System.exit(-3);
        	}
        }
        try {
            // Загрузка нужного класса
            Class<?> clazz = Class.forName(nameOfClass);
            // Проверка на то, какой из конструкторов вызывать.
            if (size > 0) {
                // Вызов конструктора с аргументом
                queue = (Queue<Integer>) clazz.getConstructor(int.class).newInstance(size);
            } else {
                // Вызов конструктора по умолчанию
                queue = (Queue<Integer>) clazz.newInstance();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
        		 IllegalArgumentException | InvocationTargetException |
        		 NoSuchMethodException | SecurityException ex) {
            System.out.println(ex.toString());
            System.exit(-4);
		}
        
        long timeStart = System.currentTimeMillis();
        // Процедуры добаления и удаления элементов выполняются REPEAT раз.
        int count = 0;
        for (int i = 0; i < REPEAT; i++) {
            int s = new Random().nextInt(100);
            boolean add = s < 50 ? size == 0 || count < size : count == 0;
            if (add) {
                queue.add(s);
                count++;
            } else {
                queue.poll();
                count--;
            }
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("Result: " + (timeEnd-timeStart) + " milliseconds.");
    }

}

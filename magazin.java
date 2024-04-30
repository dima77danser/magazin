/*
 * Необходимо написать проект, для розыгрыша в магазине игрушек. Функционал должен содержать добавление новых игрушек и задания веса для выпадения игрушек.
1) Напишите класс-конструктор у которого принимает минимум 3 строки, содержащие три поля id игрушки, текстовое название и частоту выпадения игрушки
2) Из принятой строки id и частоты выпадения(веса) заполнить минимум три массива.
3) Используя API коллекцию: java.util.PriorityQueue добавить элементы в коллекцию
4) Организовать общую очередь 5) Вызвать Get 10 раз и записать результат в файл

 */
import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.PriorityQueue;


public class Toy { //класс-конструктор у которого принимает минимум 3 строки, содержащие три поля id игрушки, название игрушки и частоту выпадения игрушки
    String id;
    String name;
    int frequency;

    public Toy(String id,String name, int frequency){
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getFrequency(){
        return frequency;
    }
   

    public static void main(String[]args){// создаю список игрушек 'toys',заполняю его 3 элементами и далее перебираю каждую игрушку, чтобы заполнить массивы weights,ids,names
        List<Toy> toys= new ArrayList<>();
        int[] weights = new int [3];
        String[] ids =  new String[3];
        String[] names =  new String[3];
        toys.add(new Toy("id1","doll",1));
        toys.add(new Toy("id2","robot",5));
        toys.add(new Toy("id3","boll",1));
        
        for (int i=0; i<3;i++){
            Toy toy = toys.get(i);
            ids[i] = toy.getId();
            names[i] = toy.getName();
            weights[i] = toy.getFrequency();
        }
  
        Queue<Toy> queue = new PriorityQueue<>(3,Comparator.comparingInt(Toy::getFrequency));// Создаю очередь. Компаратор сравнивает элементы по частоте выпадения(чем меньше частота, тем раньше элемент будет выбран из очереди)
        
        for (int i=0;i<3;i++){
            queue.offer(toys.get(i));
        }

        try (PrintWriter writer = new PrintWriter(new File("zapis.txt"))){ //запись в файл
            for(int i =0; i<10; i++){
                Toy toy1 = queue.poll();//получаю элементы по очереди и записываю в файл
                writer.println(toy1.getId()+ " " + toy1.getName() + " " + toy1.getFrequency());
                queue.offer(toy1);// после снова добавляю элемент в очередь
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

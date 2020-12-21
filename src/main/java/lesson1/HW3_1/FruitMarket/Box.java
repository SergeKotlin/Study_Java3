package lesson1.HW3_1.FruitMarket;


import java.util.ArrayList;
import java.util.Collections;

public class Box {
    // F - приемлимо, мне думается

    private ArrayList fruitBox;
    private int currentSize; // На самом деле, заменяю всегда fruitBox.size() Не знаю, как правильнее?
    private String nameOfFruit;
    private float weightOfFruit;

    public Box(String name, float weight, int numberOfFruit) {
        this.fruitBox = new ArrayList(numberOfFruit);
        nameOfFruit = name;
        weightOfFruit = weight;
        for (int i = 0; i < numberOfFruit; i++) {
            fruitBox.add(i, weight);
        }
        currentSize = numberOfFruit;
    }

    public void addFewPieceInBox(int numberAddedFruit) {
        for (int i = 0; i < numberAddedFruit; i++) {
            fruitBox.add(weightOfFruit);
            currentSize++;
        }
        System.out.println("В коробку добавили " + numberAddedFruit + " фруктов типа: " + nameOfFruit);
        System.out.println(", осталось в коробке (штук) " + currentSize);
    }

    public void remove(int numberDeletedFruit) {
        for (int i = 0; i < numberDeletedFruit; i++) {
            fruitBox.remove(numberDeletedFruit);
            currentSize--;
        }
        System.out.println("Из коробки удалили " + numberDeletedFruit + " фруктов типа: " + nameOfFruit);
        System.out.println(", осталось в коробке (штук) " + currentSize);
    }

    public float getWeight() {
        return weightOfFruit * fruitBox.size();
    }

    public void compare(Box comparisonBox) {
        if (Math.abs(this.getWeight() - comparisonBox.getWeight()) < 0.001) {
            // Дробным, с плавающей, не верю. Ставлю дельту 0.001, и сравниваю значения,
            // вычитая друг из друга
            // Это не то if (this.weightOfFruit == comparisonBox.getWeight()) {}
            // как и equals(), как и compareTo()
            System.out.println("Взвешивание: Одинаковые коробки (по весу)");
        } else {
            System.out.println("Взвешивание: Коробки - разного веса");
        }
    }

    public Box fruitMotion () {
//        switch (nameOfFruit) {
//            case "Orange":
//                //...
//                break;
//            default:
//                /..
//        }
        Box newFruitBox = new Box(this.nameOfFruit, this.weightOfFruit, this.fruitBox.size());
        int sizeFruitBox = this.fruitBox.size();
        for (int i = sizeFruitBox - 1; i >= 0; i--) {
            this.fruitBox.remove(i);
        }
        currentSize = 0;
        return newFruitBox;
    }

//    public void showType() {
//        System.out.println("Тип E: " + data);
//    }



//    public E[] swap(int id1, int id2) {
//        E swapStorage = data[id2];
//        data[id2] = data[id1];
//        data[id1] = swapStorage;
//        return data;
//    }

//    public ArrayList CastToArrayList() {
//        ArrayList<E> result = new ArrayList<E>();
//        Collections.addAll(result, data);
//
//        return result;
//    }

//    public void show(E data) {
//        System.out.println(data);
//    }
}

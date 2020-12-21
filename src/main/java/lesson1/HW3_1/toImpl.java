package lesson1.HW3_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class toImpl<E> {

    private E[] data;
    private int currentSize;

    public toImpl(E[] data) {
        this.data = data;
    }

    public E get(int index) {
        return data[index];
    }

    public void add(E value, int index) {
        data[index] = value;
        currentSize++;
    }

    public void add(E value) {
        add(value, currentSize);
    }

    public void showType() {
        System.out.println("Тип E: " + data);
    }

    public E[] swap(int id1, int id2) {
        E swapStorage = data[id2];
        data[id2] = data[id1];
        data[id1] = swapStorage;
        return data;
    }

    public ArrayList CastToArrayList() {
        ArrayList<E> result = new ArrayList<E>();
        Collections.addAll(result, data);
        /* <- упрощено методом коллекции
        for (E datum : data) {
            result.add(datum);
        }*/
        return result;
    }

    public void show(E data) {
        System.out.println(data);
    }
}

package lesson1.generics;

//import org.omg.CORBA.Object;

public class StorageImpl<E> {

    private E[] data;
    private int currentSize;

    public StorageImpl(int size){
    // Нельзя просто так взять - и создать массив типа E[]
    // Мы этот тип инициализировали, но JVM ещё неизвестно, что это..
    // this.data = new E[size];
        this.data = (E[])new Object[size];
    }

//    public Object get(int index) {
    // Моя IDEA не даёт так делать!
    public E get(int index) {
        return data[index];
    }

    public void add(E value) {
        add(value, currentSize);
    }

    public void add(E value, int index) {
        data[index] = value;
        currentSize++;
    }

    public void remove(int index) {
        data[index] = null;
        currentSize--;
    }

    public boolean find(E value) {
        for (int i = 0; i < currentSize; i++) {
            if (value.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    public void display() {
//        for (Object datum : data) {
        // Моя IDEA не даёт так делать!
        for (E datum : data) {
            System.out.print(datum + "");
            System.out.println();
        }
    }

    public void sort() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - 1 -i; j++) {
                Comparable a = (Comparable)data[i];
                Comparable b = (Comparable)data[j];
                if (a.compareTo(b) > 0) {
                    exchange(i, j);
                }
            }
        }
    }

    private void exchange(int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public int getCurrentSize() {
        return currentSize;
    }
}

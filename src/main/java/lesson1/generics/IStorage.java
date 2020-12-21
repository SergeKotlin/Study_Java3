package lesson1.generics;

public interface IStorage<E> {
    // E-magic параметризированный тип "E"-element для коллекции
    // "K/V"-key/value для map
    // А так - без разницы)) Но максимально сокращать. T...U, рядом стоящие

    void add(E value);

    void add(E value, int index);

    void remove(int index);

    E get(int index);

    boolean find(E value);

    void display();

    void sort();

    void getCurrentSize();

}

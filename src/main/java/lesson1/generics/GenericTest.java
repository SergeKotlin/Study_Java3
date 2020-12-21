package lesson1.generics;

public class GenericTest {

    public static void main(String[] args) {
        genericTest();
    }

    private static void genericTest() {
//        StorageImpl<String> stringStorage = new StorageImpl<>(5);
        // Ругается так.. нет модуля пустых дженериков для импорта в этой версиии ИДЕА
        StorageImpl<String> stringStorage = new StorageImpl<String>(5);

        stringStorage.add("A");
        stringStorage.add("B");
        stringStorage.add("C");

        stringStorage.display();
        System.out.println("Find 2: " + stringStorage.find("B"));
        String valueLast = stringStorage.get(stringStorage.getCurrentSize() - 1);
        System.out.println("Last value: " + valueLast);


//        var intStorage = new StorageImpl<Integer>(5);
        StorageImpl<Integer> intStorage = new StorageImpl<Integer>(5);

        intStorage.add(1);
        intStorage.add(2);
        intStorage.add(3);
//        intStorage.add(3.0);

        intStorage.display();
        System.out.println("Find 2: " + intStorage.find(2));
        int valueLast1 = intStorage.get(intStorage.getCurrentSize() - 1);
        // не хочет Object приводить к int у меня!
        System.out.println("Last value: " + valueLast1);
    }
}

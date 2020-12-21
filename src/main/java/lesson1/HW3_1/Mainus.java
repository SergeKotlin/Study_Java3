package lesson1.HW3_1;

import lesson1.HW3_1.FruitMarket.Apple;
import lesson1.HW3_1.FruitMarket.Box;
import lesson1.HW3_1.FruitMarket.Fruit;
import lesson1.HW3_1.FruitMarket.Orange;

import java.util.ArrayList;
import java.util.Arrays;

public class Mainus {

    public static void main(String[] args) {
        testMultipleSwap();
        testFruitMarket();

    }

    private static void testMultipleSwap() {
//        toImpl<String> stringToImpl = new toImpl<>(5);
        // Нельзя у меня в версии ИДЕА импортировать бетипный Дженерик..

        toImpl<String> strSwap = new toImpl<String>(new String[] {"Michael", "Jackson"});
        toImpl<Integer> integerSwap = new toImpl<Integer>(new Integer[] {02, 03});
        Integer[][] arrayExammple = {{4, 4}, {0, 12}};
        toImpl<Integer[]> arraySwap = new toImpl<Integer[]>(arrayExammple);

        strSwap.swap(0, 1);
        integerSwap.swap(0, 1);
        arraySwap.swap(0, 1);

        strSwap.CastToArrayList();
    }

    private static void testFruitMarket() {
        Fruit orange = new Orange(5);
        Fruit orange2 = new Orange(5);
        Fruit antonovka = new Apple(5);
        Box orangeBox = new Box(orange.getName(), orange.getWeight(), orange.getNumberOfFruit());
        Box orangeBox2 = new Box(orange2.getName(), orange2.getWeight(), orange2.getNumberOfFruit());
        Box appleBox = new Box(antonovka.getName(), antonovka.getWeight(), antonovka.getNumberOfFruit());
        // <- Тимифой говорил, мутное дело - передавать объект на вход (и у меня не видны методы Fruit тогда в Box, это второй момент)
        System.out.println("Вес коробки " + orangeBox.getWeight());
        System.out.println("Вес коробки " + orangeBox2.getWeight());
        System.out.println("Вес коробки " + appleBox.getWeight());

        orangeBox2.compare(orangeBox);
        orangeBox2.compare(appleBox);
        System.out.println();
        orangeBox2.remove(1);
        appleBox.addFewPieceInBox(1);
        orangeBox2.compare(appleBox);

        System.out.println();
        Box newBoxOrange = orangeBox.fruitMotion();
        System.out.println("Вес новой коробки " + newBoxOrange.getWeight());
        System.out.println("Вес освобожденной коробки " + orangeBox.getWeight());
        orangeBox.addFewPieceInBox(1);
        System.out.println("Вес недавно освобожденной коробки уже " + orangeBox.getWeight());
    }

}

/* ✓ 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 ✓ 2. Написать метод, который преобразует массив в ArrayList;
 ✓ 3. Большая задача:
 ✓ Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
 ✓ Класс Box, в который можно складывать фрукты.
 ✓ Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 ✓ Для хранения фруктов внутри коробки можно использовать ArrayList;
 ✓ Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
    (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
 ✓ Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
    которую подадут в compare в качестве параметра,
    true – если она равны по весу, false – в противном случае
    (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
 ✓ Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую
    (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами).
    Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
 ✓ Не забываем про метод добавления фрукта в коробку.
 * */
//Serega, sure
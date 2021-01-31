package lesson6.HW3_6Parts2and3;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static final int item = 4;

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 4, 2, 3, 4, 1, 7};
//        int[] arr1 = {1, 2, 1, 7};

        System.out.println(transformation1(arr1));
        String s = String.valueOf(transformation1((arr1)));

        int[] arr2 = {1, 1, 4, 1, 1, 1, 4, 1, 1};
//        int[] arr2 = {1, 1};
//        int[] arr2 = {4, 4};

        System.out.println(transformation2(arr2, new int[]{1, 4}));

    }

    // Напоминание - static поле принадлежит классу, также и с методом

    static ArrayList transformation1(int[] arr) {
        ArrayList indexArray = new ArrayList();
        int firstItemBeforeResult = enrichingTheTransformation(arr, indexArray);
        indexArray.clear();
        if (firstItemBeforeResult != -1) {
            for (int i = firstItemBeforeResult+1; i < arr.length; i++) {
                indexArray.add(arr[i]);
            }
        }
        return indexArray;
    }

    private static int enrichingTheTransformation(int[] arr, ArrayList indexArray) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == item) {
                indexArray.add(i);
            }
        }

        if (!indexArray.isEmpty()) {
            int firstItemBeforeResult = (int) indexArray.get( indexArray.size() - 1 );
            return firstItemBeforeResult;
        }
        return -1;
    }


    static boolean transformation2(int[] arr, int[] items) {
        boolean isTrueFirst = enrichingTheTransformation2(arr, items[0]);
        boolean isTrueSecond = enrichingTheTransformation2(arr, items[1]);

        return (isTrueFirst && isTrueSecond);
    }

    private static boolean enrichingTheTransformation2(int[] arr, int item) {
        ArrayList indexArray = new ArrayList();
        for (int i : arr) {
            if (i == item) {
                return true;
//                indexArray.add(i);
            }
        }

//        return !(indexArray.isEmpty());
        return false;
    }

}

/**2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
 * Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
 * идущих после последней четверки.
 * Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 * Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
 *
 3. Написать метод, который проверяет состав массива из чисел 1 и 4.
 Если в нем нет хоть одной четверки или единицы, то метод вернет false;
 Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 * **/
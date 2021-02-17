package lesson5.HW3_5;

import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {

        System.out.println("Объявление >>> Дамы и Господа, добро пожаловать на наш чемпионат!" +
                           "\nЗанимайте места, участники скоро выйдут на старт.");
        System.out.println("ОБЪЯВЛЕНИЕ УЧАСТНИКАМ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40, CARS_COUNT));
        Car[] cars = new Car[CARS_COUNT];
        CyclicBarrier organizationOfStarts = new CyclicBarrier(4, startRacing());

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), organizationOfStarts);
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

    }

    private static Thread startRacing() {
        return new Thread(() -> {
            System.out.println("ОБЪЯВЛЕНИЕ ВСЕМ >>> Гонщики завели моторы. Мы начинаем соревнования!" +
                    "\nСТАРТОВЫЙ ОТЧЁТ:");
            for (int i = 3; i > 0; i--) {
                System.out.println(i);
            }
            System.out.println("GO!!!");
            System.out.println("Объявление >>> Гонка началась.");
        });
    }
}
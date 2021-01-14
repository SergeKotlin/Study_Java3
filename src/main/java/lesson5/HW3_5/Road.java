package lesson5.HW3_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Road extends Stage {
    private final int numOftheParticipants;
    private static int finisher = 1;
    private static ArrayList finishers = new ArrayList();
    Semaphore semaphore = new Semaphore(1);

    public Road(int length, int numOftheParticipants) {
        this.length = length;
        this.numOftheParticipants = numOftheParticipants;
        this.description = "Дорога " + length + " метров";
    }

    public Road(int length) {
        this(length, 0);
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);

            completionOfTheRacing(c);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void completionOfTheRacing(Car c) {
        if (numOftheParticipants != 0) {
//                System.out.println(c.getName() + " финишировал "  + finisher + "!");
            try {
                semaphore.acquire();
                if (finisher == 1) {

                        System.out.println("\u001B[33m" + c.getName() + " финишировал 1-ым!" + "\u001B[0m");
                        finisher++;

                }
                semaphore.release();
            } catch (InterruptedException e) {
            e.printStackTrace();
        }

            finishers.add(c.getName());

            if (finishers.size() == numOftheParticipants) {
                System.out.println("Объявление >>> Гонка завершилась!");
                System.out.println("Список финишировавших:");
                for (int i = 1; i <= finishers.size(); i++) {
                    System.out.println(i + " место " + finishers.get(i-1));
                }

                viewFinishMachine();

            }

//                finisher++;
        }
    }

    private void viewFinishMachine() {

        System.out.println("────────────█\n" +
                "───────────██\n" +
                "─▄▀▀▀▄─██─███\n" +
                "█──▄──█████─█\n" +
                "█─███─█████─█\n" +
                "█──▀──███─███\n" +
                "─▀▄▄▄▀─▄██─█\n" +
                "─██████▀\n" +
                "─████████▄\n" +
                "─█████████▄▐\n" +
                "─███████████\n" +
                "─████████\n" +
                "─███████▀\n" +
                "─██████▀\n" +
                "─██████\n" +
                "─█████▒▒▒\n" +
                "─████░░▒▒\n" +
                "─█████▒▒\n" +
                "─██████\n" +
                "─██████\n" +
                "─██████\n" +
                "─▀████\n" +
                "─▄▀▀▀▄\n" +
                "█──▄──█\n" +
                "█─███─█\n" +
                "█──▀──█\n" +
                "─▀▄▄▄▀\n" +
                "─█████\n" +
                "─████\n" +
                "─████\n" +
                "─███\n" +
                "─███\n" +
                "─██");

    }
}

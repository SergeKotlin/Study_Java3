package lesson6.lesson.Calculator;

public class Calculator {

//    public static void main(String[] args) {
//        var calc = new Calculator();
//        System.out.println(calc.add(1, 2));
//        System.out.println(calc.div(1, 2));
//    }

    public int div(int a, int b) {
        if (a == 16) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return a / b;

    }

    public int add(int a, int b) {

        return a + b;

    }
}

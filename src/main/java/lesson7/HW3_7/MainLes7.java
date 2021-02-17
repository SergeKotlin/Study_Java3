package lesson7.HW3_7;

import java.lang.annotation.Retention;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainLes7 {
    static Some1 some = new Some1();

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        System.out.println("Go");

//            start(Class.forName("Some1"));
//            start(Some1.getClass());
//        Testing.start(Some1.class);
        Testing.start("MainLes7.Some1");


        testExampleForHardSyntaxFromReflectionLesson7();
    }

    private static class Testing {

        static void start(Class<?> testClass) throws InvocationTargetException, IllegalAccessException {

            Method[] methods = testClass.getDeclaredMethods();
            announceBeforeSuite(methods);

            HashMap<String, Integer> testExamples = new HashMap<String, Integer>();
            // Исследующий прогон
            research(methods, testExamples);
            // Запускающий прогон
            launch(methods, testExamples);

            announceAfterSuite(methods);

//            @BeforeSuite
//            @Test
//            @AfterSuite
        }

        static void start(String strin) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
            start(Class.forName(strin));
        }

        private static void announceBeforeSuite(Method[] methods) throws InvocationTargetException, IllegalAccessException {
            boolean failRepeatBefore = false;
            for (Method method : methods) {
                if(method.getAnnotation(BeforeSuite.class) != null) {
                    catchRepeat(failRepeatBefore);
                    System.out.println(1);
                    method.invoke(some);
                    failRepeatBefore = true;
                }
            }
        }

        private static void research(Method[] methods, HashMap<String, Integer> testExamples) {
            for (Method method : methods) {
                if(method.getAnnotation(Test.class) != null) {
                    Test annotation = method.getAnnotation(Test.class);
                    testExamples.put(method.getName(), annotation.priority());
                    System.out.println(2);
                }
            }
        }

        private static void launch(Method[] methods, HashMap<String, Integer> testExamples) throws IllegalAccessException, InvocationTargetException {
            if (!testExamples.isEmpty()) {
                for (int i = 1; i <= 10; i++) {
                    if (testExamples.containsValue(i)) {
                        for (Method method : methods) {
                            if (method.getAnnotation(Test.class) != null && method.getAnnotation(Test.class).priority() == i) {
                                method.invoke(some);
                            }
                        }
                    }
                }
            }
        }

        private static void announceAfterSuite(Method[] methods) throws InvocationTargetException, IllegalAccessException {
            boolean failRepeatEnd = false;
            for (Method method : methods) {
                if(method.getAnnotation(AfterSuite.class) != null) {
                    catchRepeat(failRepeatEnd);
                    System.out.println(3);
                    method.invoke(some);
                    failRepeatEnd = true;
                }
            }
        }

        private static void catchRepeat(boolean failRepeat) {
            if (failRepeat == true) {
                throw new RuntimeException("Ой-ёй, одиночная аннотация прописана неоднократно в коде!");
            }
        }

    }

    protected static class Some1 {
        @Test(priority = 1)
        protected void showSomething1() {
            System.out.println("Что-нибудь Some1 Приоритет 1");
        }
        @Test(priority = 2)
        protected void showSomething2() {
            System.out.println("Что-нибудь Some2 Приоритет 2");
        }
        @Test(priority = 1)
        protected void showSomething3() {
            System.out.println("Что-нибудь Some3 Приоритет 1");
        }

        @BeforeSuite
        protected void showBefore() {
            System.out.println("Что-нибудь перед началом Some1");
        }
        @AfterSuite
        protected void showEnd() {
            System.out.println("Что-нибудь по завершению Some1");
        }
        @AfterSuite
        protected void showEndFail() {
            System.out.println("Ошибочный. Вы не должны это видеть. Что-нибудь по завершению Some1");
        }
    }

    private static void testExampleForHardSyntaxFromReflectionLesson7() {
        try {
            Cat cat = new Cat();
            Field fieldName = cat.getClass().getField("name");
            System.out.println(fieldName.get(cat));
            fieldName.set(cat, "Мурзик");
            Field fieldAge = cat.getClass().getField("age");
            System.out.println(fieldAge.get(cat));
            System.out.println(fieldName.get(cat));
            // То есть такое масло масляное? Lol
            System.out.println(cat.getClass().getField("oilyOil").get(cat));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
/** Создать класс, который может выполнять «тесты».
 * ✓ Для этого у него должен быть статический метод start(),
 * ✓    которому в качестве параметра передается или объект типа Class,
 * ✓+-      или имя класса.
 * ✓ В качестве тестов выступают классы с наборами методов с аннотациями @Test.
 * ✓ Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется.
 * ✓ Далее запущены методы с аннотациями @Test.
 * ✓ К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10), в соответствии с которыми
 * ✓ будет выбираться порядок их выполнения.
 * ✓ Если приоритет одинаковый, то порядок не имеет значения.
 * ✓ По завершении всех тестов – вызывается метод с аннотацией @AfterSuite.
 * ✓ Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
 * ✓ иначе необходимо бросить RuntimeException при запуске «тестирования».
 *
 P.s. Это домашнее задание никак не связано с темой тестирования через JUnit и использованием этой библиотеки:
 * проект пишется с нуля.
 * **/
//Serega, sure
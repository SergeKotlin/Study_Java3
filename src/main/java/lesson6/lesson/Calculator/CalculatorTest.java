package lesson6.lesson.Calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void startUp() {
        calculator = new Calculator();
        System.out.println("тест начался");
    }
    @AfterEach
    void shutdown() {
        System.out.println("тест завершён");
    }

    @BeforeAll
    static void init() {
        System.out.println("ГЛАВНОЕ НАЧАЛО");
    }

    @AfterAll
    static void shutdownAfterAll() {
        System.out.println("ГЛАВНЫЙ КОНЕЦ");
    }

    @Test
    public void testAdd1() {
//        calculator = new Calculator();
//        Assertions.assertEquals(3, 3);
        Assertions.assertEquals(3, calculator.add(1, 2));
    }

    @Test
    public void testAdd2() {
        int a = 33;
        Assertions.assertTrue(a == 33, () -> "System.out.println()");

        Assertions.assertEquals(197, calculator.add(41, 156));
    }

    @DisplayName("test add 7 + 5")
    @Test
    public void testAdd3() {
//        Assertions.assertEquals(12, calculator.add(7, 5));
        Assertions.assertEquals(11, calculator.add(7, 5));
    }

    @Test
    public void testDiv1() {
//        Assertions.assertEquals(4, calculator.div(16, 4));

        // Предположения, т.е неточные утверждения:
        int a = 33;
//        Assumptions.assumeTrue(a == 33, () -> "assume");
        Assumptions.assumeFalse(a == 33, () -> "assume");

        Assertions.assertEquals(4, calculator.div(a, 4));
    }

    @Test
    public void testDiv2() {
        Assertions.assertThrows(ArithmeticException.class, () ->{
//            Assertions.assertEquals(4, calculator.div(16, 0));
            Assertions.assertEquals(4, calculator.div(3, 0));
        });
    }

    @Test
    public void testDiv3() {
        // Таймаут на 1 секунду выполнения:
        Assertions.assertTimeout(Duration.ofSeconds(1), () -> {
            Assertions.assertEquals(4, calculator.div(16, 4));
        });
    }

    @Test
    @Disabled
    public void testDiv4() {
        Assertions.assertEquals(4, calculator.div(16, 4));
    }

    // Параметризированные тесты
    @DisplayName("ParamTest")
    @ParameterizedTest
    @MethodSource("data")
    public void paramTestAdd(int valA, int valB, int sum) {
        Assertions.assertEquals(sum, calculator.add(valA, valB));
    }

    static Stream<Arguments> data() {
        return Stream.of(
            Arguments.arguments(1, 2, 3),
            Arguments.arguments(3, 66, 69),
            Arguments.arguments(55, 22, 88),
            Arguments.arguments(-1, 1, 0),
            Arguments.arguments(-133, 55, 1)
        );
    }
}
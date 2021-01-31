package lesson6.HW3_6Parts2and3;

import lesson6.lesson.Calculator.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

public class MainTest {

    private Main testForMain;

    @BeforeEach
    void startUp() {
        testForMain = new Main();
        System.out.println("тест начался");
    }

    @AfterEach
    void shutdown() {
        System.out.println("тест завершён");
    }

    @BeforeAll
    static void init() {
        System.out.println("НАЧАЛО ТЕСТОВ");
    }

    @AfterAll
    static void shutdownAfterAll() {
        System.out.println("ТЕСТИРОВАНИЕ ОКОНЧЕНО");
    }

    // Параметризированные тесты
    @DisplayName("ParamTestFor_transformation1")
    @ParameterizedTest
    @MethodSource("data1")
    public void paramTestFor_transformation1(int[] array, String result) {
        Assertions.assertEquals(result, String.valueOf(testForMain.transformation1(array)).toString());
    }

    static Stream<Arguments> data1() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new String("[1, 7]")),

                Arguments.arguments(new int[]{1, 2, 1, 7}, new String("[]")),
                Arguments.arguments(new int[]{4, 2, 14, 41, 44, 1, 7}, new String("[2, 14, 41, 44, 1, 7]")),
                Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 4, 4, 4}, new String("[]")),
                Arguments.arguments(new int[]{4, 4}, new String("[]"))
        );
    }

    @DisplayName("ParamTestFor_transformation2")
    @ParameterizedTest
    @MethodSource("data2")
    public void paramTestFor_transformation2(int[] array, int[] comparedItems, String result) {
        Assertions.assertEquals(result, String.valueOf(testForMain.transformation2(array, comparedItems)).toString());
    }

    static Stream<Arguments> data2() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 1, 4, 1, 1, 1, 4, 1, 1}, new int[]{1, 4}, "true"),

                Arguments.arguments(new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 4}, "false"),
                Arguments.arguments(new int[]{4, 4, 4}, new int[]{1, 4}, "false"),
                Arguments.arguments(new int[]{1, 1, 4, 14}, new int[]{1, 4}, "true")
                // +special
        );
    }

    @Test
    public void specialTestFor_transformation2() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Assertions.assertEquals("false",
                    String.valueOf(testForMain.transformation2(new int[]{1, 1, 4, 1, 1, 1, 4, 1, 1},
                            new int[]{1})).toString());
        });
    }
}

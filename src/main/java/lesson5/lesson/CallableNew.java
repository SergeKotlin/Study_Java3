package lesson5.lesson;

import java.util.concurrent.*;

public class CallableNew {
    // В отличие от Runable - Callable ещё и возвращает что-то (а не просто запускает)

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {6, 7, 8, 9, 0};

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (int i : arr1) {
                    result += i;
                }
                return result;
            }
        };

        Future<Integer> f1 = executorService.submit(callable);
        Future<?> f2 = executorService.submit(() -> {
            int result = 0;
            for (int i : arr2) {
                result += i;
            }
            return result;
        });
        System.out.println(f1.get() + " " + f2.get());
        executorService.shutdown(); // Не забывает тушить фарбику
    }
}

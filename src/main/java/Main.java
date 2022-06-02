import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        new CustomLogger();
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите длину трассы в метрах");
            int trackLength = scanner.nextInt();
            System.out.println("Введите количество автомобилей, учавствующих в гонке");
            int carNumber = scanner.nextInt();
            ExecutorService executorService = Executors.newFixedThreadPool(carNumber);
            for (int i = 1; i <= carNumber; i++) {
                Random speed = new Random();
                Car car = new Car(i, speed.nextInt(100) + 100);
                executorService.execute(new Race(car, trackLength, countDownLatch));
            }
            startRace();
            executorService.shutdown();
            if (executorService.awaitTermination(1, TimeUnit.HOURS))
            System.out.println("Гонка завершена!");
        } catch (InputMismatchException e) {
            System.out.println("Вводимые данные не соответствуют своему типу");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startRace() {
        try {
            Thread.sleep(1000);
            System.out.println("Гонка начинается!");
            for (int i = 3; i > 0; i--) {
                System.out.println(i + "...");
                Thread.sleep(1000);
            }
            System.out.println("Поехали!");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

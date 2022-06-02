import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class Race implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(Race.class.getName());
    private final Car car;
    int trackLength;
    private final CountDownLatch countDownLatch;

    public Race(Car car, int trackLength, CountDownLatch countDownLatch) {
        this.car = car;
        this.trackLength = trackLength;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            while (trackLength > 0) {
                Thread.sleep(1000);
                trackLength -= car.getSpeed() * 1000 / 3600;
                if (trackLength > 0) {
                    LOGGER.info("Машине №" + car.getCarNumber() + " до финиша осталось " + trackLength + "м.");
                } else {
                    System.out.println("Машина №" + car.getCarNumber() + " добралась до финиша!");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

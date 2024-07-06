package WEEK_11_ASSIGNMENT;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class ClockApplication {
    public static void main(String[] args) {
        Clock clock = new Clock();
        clock.start();

        // Demonstrate stopping the clock after 10 seconds
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        clock.stop();
        System.out.println("\nClock stopped.");
    }
}

class Clock {
    private final AtomicReference<Date> currentTime;
    private final ThreadLocal<SimpleDateFormat> formatter;
    private volatile boolean running;

    public Clock() {
        this.currentTime = new AtomicReference<>(new Date());
        this.formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH:mm:ss dd-MM-yyyy"));
        this.running = true;
    }

    public void start() {
        Thread updateThread = new Thread(this::updateTime, "UpdateThread");
        Thread displayThread = new Thread(this::displayTime, "DisplayThread");

        updateThread.setPriority(Thread.MIN_PRIORITY);
        displayThread.setPriority(Thread.MAX_PRIORITY);

        updateThread.start();
        displayThread.start();
    }

    private void updateTime() {
        while (running) {
            currentTime.set(new Date());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Update thread interrupted: " + e.getMessage());
                break;
            }
        }
    }

    private void displayTime() {
        while (running) {
            try {
                System.out.print("\r" + formatter.get().format(currentTime.get()));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Display thread interrupted: " + e.getMessage());
                break;
            } catch (Exception e) {
                System.err.println("Error in display thread: " + e.getMessage());
            }
        }
    }

    public void stop() {
        running = false;
    }
}
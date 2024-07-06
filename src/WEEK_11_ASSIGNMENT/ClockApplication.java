package WEEK_11_ASSIGNMENT;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockApplication {
    public static void main(String[] args) {
        Clock clock = new Clock();
        clock.start();
    }
}

class Clock {
    private volatile Date currentTime;
    private final SimpleDateFormat formatter;
    private volatile boolean running;

    public Clock() {
        this.currentTime = new Date();
        this.formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        this.running = true;
    }

    public void start() {
        // Create and start the update thread (lower priority)
        Thread updateThread = new Thread(this::updateTime);
        updateThread.setPriority(Thread.MIN_PRIORITY);
        updateThread.start();

        // Create and start the display thread (higher priority)
        Thread displayThread = new Thread(this::displayTime);
        displayThread.setPriority(Thread.MAX_PRIORITY);
        displayThread.start();
    }

    private void updateTime() {
        while (running) {
            currentTime = new Date();
            try {
                Thread.sleep(100); // Update every 100 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Update thread interrupted: " + e.getMessage());
            }
        }
    }

    private void displayTime() {
        while (running) {
            System.out.print("\r" + formatter.format(currentTime));
            try {
                Thread.sleep(1000); // Display every second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Display thread interrupted: " + e.getMessage());
            }
        }
    }

    public void stop() {
        running = false;
    }
}
package net.pla1.googlevisiontesting01;

public class StopWatch {
    private long start = System.currentTimeMillis();

    public StopWatch() {

    }

    public String toString() {
        long now = System.currentTimeMillis();
        long diff = now - start;
        return String.format("%d seconds", diff / 1000);
    }
}

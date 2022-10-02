package mipt.baranov.docks;

import mipt.baranov.ships.Ship;

import java.util.concurrent.Semaphore;

public class ConcurrentDock<T extends Ship> implements Dock<T> {
    private final Object lock;
    private final int loadSpeed;

    public ConcurrentDock(int loadSpeed) {
        lock = new Object();
        this.loadSpeed = loadSpeed;
    }

    @Override
    public void load(T ship) throws InterruptedException {
        synchronized (lock) {
            int loadAmount = ship.getCapacity();
            Thread.sleep((loadAmount / loadSpeed) * 1000L);
        }
    }
}

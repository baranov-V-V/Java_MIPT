package mipt.baranov.tonnels;

import mipt.baranov.ships.Ship;

import java.util.concurrent.Semaphore;

public class TicketTonnel implements Tonnel {
    private final Semaphore semaphore;
    private final int passTime;

    public TicketTonnel(int maxShipCount, int passTime) {
        semaphore = new Semaphore(maxShipCount);
        this.passTime = passTime;
    }

    @Override
    public void pass(Ship ship) throws InterruptedException {
        semaphore.acquire();
        Thread.sleep(passTime);
        semaphore.release();
    }
}

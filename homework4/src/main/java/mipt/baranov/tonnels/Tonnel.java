package mipt.baranov.tonnels;

import mipt.baranov.ships.Ship;

public interface Tonnel {
    void pass(Ship ship) throws InterruptedException;
}

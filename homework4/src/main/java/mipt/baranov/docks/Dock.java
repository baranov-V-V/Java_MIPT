package mipt.baranov.docks;

import mipt.baranov.ships.Ship;

public interface Dock<T extends Ship> {
    void load(T ship) throws InterruptedException;
}

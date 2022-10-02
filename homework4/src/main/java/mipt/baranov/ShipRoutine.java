package mipt.baranov;

import lombok.AllArgsConstructor;
import mipt.baranov.docks.ConcurrentDock;
import mipt.baranov.docks.Dock;
import mipt.baranov.ships.Ship;
import mipt.baranov.tonnels.Tonnel;

import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
public class ShipRoutine implements Runnable {
    private final Ship ship;
    private final Dock<Ship> dock;
    private final Tonnel tonnel;
    private static final Logger shipRoutineLogger;

    static {
        shipRoutineLogger = Logger.getLogger(ShipRoutine.class.getSimpleName());
    }

    @Override
    public void run() {
        try {
            
            tonnel.pass(ship);

        } catch (InterruptedException e) {
            shipRoutineLogger.log(Level.SEVERE, "InterruptedExeption happened");
            Thread.currentThread().interrupt();
        }
    }
}

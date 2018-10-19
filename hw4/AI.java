/**
 * Abstract class that defines AI.
 * @author slee3245
 * @version 1.0
 */
public abstract class AI {

    private boolean destructed;
    private Coordinates cannonTarget;
    private Coordinates secretHQ;

    /**
     * Constructor of AI class. Set destructed to false by default.
     * @param cannonTarget coordinates of the target which the AI is aiming at.
     * @param secretHQ coordinates of the secret headquarter.
     */
    public AI(Coordinates cannonTarget, Coordinates secretHQ) {
        this.cannonTarget = cannonTarget;
        this.secretHQ = secretHQ;
        this.destructed = false;
    }

    /**
     * Getter for destructed variable.
     * @return boolean of whether this AI is destructed.
     */
    public boolean getDestructed() {
        return destructed;
    }

    /**
     * Getter for cannonTarget variable.
     * @return coordinates of cannonTarget.
     */
    public Coordinates getCannonTarget() {
        return cannonTarget;
    }

    /**
     * Getter for secretHQ variable.
     * @return coordinates of secretHQ.
     */
    public Coordinates getSecretHQ() {
        return secretHQ;
    }

    /**
     * Swap target of the canon to new coordinates after logic.
     * Only swap when not destructed, new coordinates not equal to
     * previous coordinates, and shouldSwapCannonTarget() return true.
     * @param newCoords new coordinates.
     * @return true if target of the canon was swapped.
     */
    public boolean swapCanonTarget(Coordinates newCoords) {
        if (!destructed && (!(newCoords.equals(cannonTarget)))) {
            if (shouldSwapCannonTarget()) {
                cannonTarget = newCoords;
                return true;
            } else if (shouldSelfDestruct()) {

                selfDestruct();
            }
        }
        return false;
    }

    /**
     * Abstract method that determine whether target should be swapped.
     * @return true if target should be swapped.
     */
    public abstract boolean shouldSwapCannonTarget();

    /**
     * Abstract method that checks whether this AI should be self destructed
     * due to virus.
     * @return true if should be destructed.
     */
    public abstract boolean shouldSelfDestruct();

    /**
     * Method that makes the AI self destruct due to virus.
     */
    public void selfDestruct() {
        destructed = true;
    }

    /**
     * toString method that overrides Object class.
     * @return String representation of AI status (cannonTarget).
     */
    @Override
    public String toString() {
        return String.format("Dr. Chipotle’s guacamole cannon"
                + " is currently pointed at %s.", cannonTarget.toString());
    }


}

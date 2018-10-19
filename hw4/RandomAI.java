import java.util.Random;

/**
 * Subclass of AI class that defines random AI.
 * @author slee3245
 * @version 1.0
 */
public class RandomAI extends AI {

    private static final Random randomizer = new Random();

    /**
     * Constructor of RandomAI class.
     * @param cannonTarget coordinates of the target which the AI is aiming at.
     * @param secretHQ coordinates of the secret headquarter.
     */
    public RandomAI(Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
    }

    /**
     * Method that determines whether it should swap target.
     * It overrides abstract method in AI class.
     * @return true if target should be swapped by 50% chance.
     */
    @Override
    public boolean shouldSwapCannonTarget() {
        if (randomizer.nextInt(2) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Method that determines whether it should be self destructed.
     * It overrides abstract method in AI class.
     * @return true if should be self destructed by 50% chance.
     */
    @Override
    public boolean shouldSelfDestruct() {
        if (randomizer.nextInt(2) == 0) {
            return true;
        }
        return false;
    }
}

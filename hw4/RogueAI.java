/**
 * Subclass of AI class that defines RogueAI.
 * @author slee3245
 * @version 1.0
 */
public class RogueAI extends AI {

    private int firewallProtection;
    private int alertLevel;
    private final int maxAlert;

    /**
     * Constructor for RogueAI.
     * @param firewallProtection level of firewallProtection.
     * @param alertLevel level of alertLevel.
     * @param maxAlert level of maxAlert.
     * @param cannonTarget coordinates of the target which the AI is aiming at.
     * @param secretHQ coordinates of the secret headquarter.
     */
    public RogueAI(int firewallProtection, int alertLevel, int maxAlert,
                   Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
        this.firewallProtection = firewallProtection;
        this.alertLevel = alertLevel;
        this.maxAlert = maxAlert;
    }

    /**
     * Constructor for RogueAI. Set alertLevel to 0 by default.
     * @param firewallProtection level of firewallProtection.
     * @param maxAlert level of maxAlert.
     * @param cannonTarget coordinates of the target which the AI is aiming at.
     * @param secretHQ coordinates of the secret headquarter.
     */
    public RogueAI(int firewallProtection, int maxAlert,
                   Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
        this.firewallProtection = firewallProtection;
        this.alertLevel = 0;
        this.maxAlert = maxAlert;
    }

    /**
     * Constructor for RogueAI. Set alertLevel to 0, maxAlert to 10 by default.
     * @param firewallProtection level of firewallProtection.
     * @param cannonTarget coordinates of the target which the AI is aiming at.
     * @param secretHQ coordinates of the secret headquarter.
     */
    public RogueAI(int firewallProtection, Coordinates cannonTarget,
                   Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
        this.firewallProtection = firewallProtection;
        this.alertLevel = 0;
        this.maxAlert = 10;
    }

    /**
     * Getter for firewallProtection variable.
     * @return level of firewallProtection.
     */
    public int getFirewallProtection() {
        return firewallProtection;
    }

    /**
     * Getter for alertLevel variable.
     * @return level of alertLevel.
     */
    public int getAlertLevel() {
        return alertLevel;
    }

    /**
     * Getter for maxAlert.
     * @return level of maxAlert.
     */
    public int getMaxAlert() {
        return maxAlert;
    }

    /**
     * Method that lowers the firewall and increases the alertLevel.
     */
    public void lowerFirewall() {
        firewallProtection -= 2;
        alertLevel += 1;
    }

    /**
     * Method that determines whether it should swap target.
     * It overrides abstract method in AI class.
     * @return true if firewallProtection is less or equal to 0.
     */
    @Override
    public boolean shouldSwapCannonTarget() {
        return (firewallProtection <= 0);
    }

    /**
     * Method that determines whether it should be self destructed.
     * It overrides abstract method in AI class.
     * @return true if alertLevel is greater or equal to maxAlert.
     */
    @Override
   public boolean shouldSelfDestruct() {
        return (alertLevel >= maxAlert);
    }

    /**
     * toString method that overrides AI class.
     * @return String representation of AI status
     * (alertLevel, firewallProtection).
     */
    @Override
    public String toString() {
        return String.format("%s, and is at level %d with"
                + " firewall protection %d.",
                super.toString().substring(0, super.toString().length() - 1)
                , alertLevel, firewallProtection);
    }
}

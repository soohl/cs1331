/**
 * Analysis questions & answers:
 * DoctorCS’s saveTheDay method forces you to use instanceof.
 *
 * --- Why is this not good style?
 * Use of multiple "instanceof" in saveTheDay method is
 * not a good style of programming since whenever a new subclass of AI is added,
 * saveTheDay method needs to be modified so that it could handle
 * new types of AI.
 *
 * --- What changes could we make to AI and it’s subclasses
 * --- to avoid using instanceof?
 * We could avoid the use of instanceof by creating
 * an abstract method in AI class, which then each subclass could override
 * it with its specific behaviors. By doing so, we do not need to
 * use instanceof nor casting, and be able to reduce the code
 * just with calling a single abstract method inside saveTheDay().
 *
 * DoctorCS class that defines doctorCS.
 * @author slee3245
 * @version 1.0
 */
public class DoctorCS {

    private AI ai;
    private final String secretIdentity;
    private final int jlaid;
    private boolean safe;

    /**
     * Constructor for DoctorCS class. Set safe to false by default.
     * @param ai type of ai.
     * @param secretIdentity hidden identity of doctorCS.
     * @param jlaid justice league of america id of doctorCS
     */
    public DoctorCS(AI ai, String secretIdentity, int jlaid) {
        this.ai = ai;
        this.secretIdentity = secretIdentity;
        this.jlaid = jlaid;
        this.safe = false;
    }

    /**
     * Getter for AI variable.
     * @return ai.
     */
    public AI getAI() {
        return ai;
    }

    /**
     * Getter for jlaid.
     * @return jlaid.
     */
    public int getJlaid() {
        return jlaid;
    }

    /**
     * Method that defines doctor cs saving gt.
     * Method act differently depends on the type of the AI object.
     */
    public void saveTheDay() {
        if (ai instanceof RogueAI) {
            while (((RogueAI) ai).getFirewallProtection() > 0) {
                ((RogueAI) ai).lowerFirewall();
            }
        }
        safe = ai.swapCanonTarget(ai.getSecretHQ());
    }

    /**
     * Return status of GT.
     * @return Appropriate string depending on the results.
     */
    public String getStatus() {
        if (safe) {
            return "Doctor CS has saved the day!";
        }
        if (!safe && ai.getDestructed()) {
            return "Dr. Chipotle has succeeded in his plan…";
        } else {
            return "Georgia Tech is still in danger!";
        }
    }

    /**
     * toString method that overrides Object class.
     * @return string representation of doctor CS identity
     * (secretIdentity, jlaid).
     */
    @Override
    public String toString() {
        return  String.format("%s aka Doctor CS with JLAID: %d"
                , secretIdentity, jlaid);
    }

}

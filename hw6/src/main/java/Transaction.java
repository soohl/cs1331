
import java.util.Optional;

/**
 * @author slee3245
 * @version 1.0
 */
public class Transaction {
    private TransactionType type; //enum
    private double amount;
    private Optional<String> comment;

    /**
     * Constructor for transaction class.
     * @param type type of the transaction (WITHDRAWAL, DEPOSIT).
     * @param amount amount of money in transaction.
     */
    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.comment = Optional.empty();
    }

    /**
     * 2nd constructor for transaction class.
     * @param type type type of the transaction (WITHDRAWAL, DEPOSIT).
     * @param amount amount amount of money in transaction.
     * @param comment comment on the transaction.
     */
    public Transaction(TransactionType type, double amount, String comment) {
        this.type = type;
        this.amount = amount;
        this.comment = Optional.ofNullable(comment);
    }

    /**
     * Getter for type variable.
     * @return type of the transaction
     */
    public TransactionType getType() {
        return this.type;
    }

    /**
     * Getter for amount variable.
     * @return amount in the transaction
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Getter for comment variable.
     * @return comment of the transaction
     */
    public Optional<String> getComment() {
        return this.comment;
    }

    /**
     * Method that checks if the transaction has comment.
     * @return true if the transaction has comment.
     */
    public boolean hasComment() {
        return comment.isPresent();
    }
}

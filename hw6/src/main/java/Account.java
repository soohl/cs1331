
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author slee3245
 * @version 1.0
 */
public class Account {
    private List<Transaction> pastTransactions;

    /**
     * Constructor for the account class.
     * @param transactions list of past transactions with the account.
     */
    public Account(List<Transaction> transactions) {
        pastTransactions = transactions;
    }

    /**
     * Get specific (n) transaction from pastTransactions.
     * @param n index of transaction to get.
     * @return single transaction from pastTransactions.
     */
    public Transaction getTransaction(int n) {
        return pastTransactions.get(n);
    }

    /**
     * Filter list of transactions and retturn list of transactions
     * that apply for specific criteria.
     * @param predicate predicate object that specify criteria.
     * @return list of transactions that apply for predicate criteria.
     */
    public List<Transaction> findTransactionsByPredicate(
            Predicate<Transaction> predicate) {
//        return pastTransactions.stream()
//                .filter(predicate)
//                .collect(Collectors.<Transaction>toList());
        List<Transaction> temp = new ArrayList<>();
        for (Transaction transaction : pastTransactions) {
            if (predicate.test(transaction)) {
                temp.add(transaction);
            }
        }
        return temp;
    }

    //Inner class
    /**
     * Get list of transactions with amount equal to given amount.
     * @param amount amount given as a criteria.
     * @return list of transactions with amount equal to given amount.
     */
    public List<Transaction> getTransactionsByAmount(double amount) {
        class PredicateByAmount implements Predicate<Transaction> {
            @Override
            public boolean test(Transaction transaction) {
                return transaction.getAmount() == amount;
            }
        }
        return findTransactionsByPredicate(new PredicateByAmount());
    }

    //Anonymous inner class
    /**
     * Get list of transactions that is withdrawal.
     * @return list of transactions that is withdrawal.
     */
    public List<Transaction> getWithdrawals() {
        return findTransactionsByPredicate(new Predicate<Transaction>() {
            @Override
            public boolean test(Transaction transaction) {
                return transaction.getType() == TransactionType.WITHDRAWAL;
            }
        });
    }

    //Lambda Expression
    /**
     * Get list of transactions that is deposit.
     * @return list of transactions that is deposit.
     */
    public List<Transaction> getDeposits() {
        return findTransactionsByPredicate(transaction ->
                transaction.getType() == TransactionType.DEPOSIT);
    }

    /**
     * Get list of transactions that has comment longer than given length.
     * @param length given length as a criteria.
     * @return list of transactions that has comment longer than given length.
     */
    public List<Transaction> getTransactionsWithCommentLongerThan(int length) {
        return findTransactionsByPredicate(transaction -> {
                if (transaction.hasComment()) {
                    if (transaction.getComment().get().length() > length) {
                        return true;
                    }
                }
                return false;
            });
    }

//    Lambda expression of getTransactionsWithComment()
//    public List<Transaction> getTransactionsWithCommentLambda() {
//        return findTransactionsByPredicate(transaction
//                  -> transaction.hasComment());
//    }

    //Method reference. (Object that contain method :: name of the method)
    /**
     * Get list of transactions that has comment.
     * @return return list of transactions that has comment.
     */
    public List<Transaction> getTransactionsWithComment() {
        return findTransactionsByPredicate(Transaction::hasComment);
    }

    /**
     * Get all the past transactions in this account.
     * @return list of all transactions in this account.
     */
    public List<Transaction> getPastTransactions() {
        return pastTransactions;
    }
}

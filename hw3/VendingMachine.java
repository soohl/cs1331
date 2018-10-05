import java.util.Random;

/**
 * VendingMachine Class.
 * Contain internal logic of the vending machine.
 * @author slee3245
 * @version 1.0
 */
public class VendingMachine {
    private static double totalSales;
    private VendingItem[][][] shelf;
    private int luckyChance;
    private Random random;

    /**
    * No-arg Constuctor method for VendingMachine class.
    */
    public VendingMachine() {
        this.totalSales = 0;
        this.shelf = new VendingItem[6][3][5]; //row, col, pos
        this.luckyChance = 0;
        this.random = new Random();
        restock();
    }

    /**
    * Vend item from the Vending machine.
    * @param code String code of the position for vending (ex. A1)
    * @return Item to be vended or null if not found or invalid input code.
    */
    public VendingItem vend(String code) {
        if (code.matches("[A-F][1-3]")) {
            int rowI = (int) code.charAt(0) - 65; //Extract Alphabet
            int colI = (int) code.charAt(1) - 49; //Extract Number
            if (this.shelf[rowI][colI][0] != null) {
                VendingItem item = this.shelf[rowI][colI][0];
                double itemPrice = this.shelf[rowI][colI][0].getPrice();
                for (int i = 0; i < 4; i++) {
                    this.shelf[rowI][colI][i] = this.shelf[rowI][colI][i + 1];
                }
                this.shelf[rowI][colI][4] = null;
                if (free()) { //Check whether the item is for free.
                    System.out.printf("%n %s %n",
                        "--- Congrat. You get item for free! ---");
                } else {
                    totalSales += itemPrice;
                }
                return item;
            } else { //if there is no item left in the position.
                System.out.printf("%n %s %n",
                    "--- Sorry. No item left ---");
                return null;
            }
        } else { //if input code is invalid.
            System.out.printf("%n %s %n",
                "--- Your input code is invalid ---");
        }
        return null;
    }

    /**
    * Determine wheather the item to be vended is free based on luckyChance.
    * @return Boolean of wheather item is free or not.
    */
    private boolean free() {
        if (random.nextInt(100) < this.luckyChance) {
            this.luckyChance = 0;
            return true;
        } else {
            this.luckyChance++;
            return false;
        }
    }

    /**
    * Restock vending machine fully with random items.
    */
    public void restock() {
        VendingItem[] listOfItems = VendingItem.values();
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 3; c++) {
                for (int p = 0; p < 5; p++) {
                    this.shelf[r][c][p] = listOfItems[random.nextInt(
                        listOfItems.length)];
                }
            }
        }
    }

    /**
    * Return totalSales value of the two vending machine.
    * @return totalSales value of the two vending machine.
    */
    public static double getTotalSales() {
        return totalSales;
    }

    /**
    * Return number of items left in the vending machine.
    * @return number of items left in the vending machine.
    */
    public int getNumberOfItems() {
        int numItems = 0;
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 3; c++) {
                for (int p = 0; p < 5; p++) {
                    if (this.shelf[r][c][p] != null) {
                        numItems++;
                    }
                }
            }
        }
        return numItems;
    }

    /**
    * Return total value of the items left in the vending machine.
    * @return total value of the items left in the vending machine.
    */
    public double getTotalValue() {
        double totalValue = 0.0;
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 3; c++) {
                for (int p = 0; p < 5; p++) {
                    if (this.shelf[r][c][p] != null) {
                        totalValue += this.shelf[r][c][p].getPrice();
                    }
                }
            }
        }
        return totalValue;
    }

    /**
    * Return lucky chance value.
    * @return lucky chance value.
    */
    public int getLuckyChance() {
        return this.luckyChance;
    }

    /**
    * toString method for VeningMachine instnce.
    * @return String representation of VeningMachine status.
    */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append("                            VendaTron 9000                "
            + "            \n");
        for (int i = 0; i < shelf.length; i++) {
            s.append("------------------------------------------------------"
                + "----------------\n");
            for (int j = 0; j < shelf[0].length; j++) {
                VendingItem item = shelf[i][j][0];
                String str = String.format("| %-20s ",
                    (item == null ? "(empty)" : item.name()));
                s.append(str);
            }
            s.append("|\n");
        }
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append(String.format("There are %d items with a total "
            + "value of $%.2f.%n", getNumberOfItems(), getTotalValue()));
        s.append(String.format("Total sales across vending machines "
            + "is now: $%.2f.%n", getTotalSales()));
        return s.toString();
    }
}
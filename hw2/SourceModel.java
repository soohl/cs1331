import java.io.FileReader;
import java.io.IOException;

/**
* @author SooHyung Lee
* @version 1.0
* @since 1.0
*/
public class SourceModel {

    private int[][] charMatrix; // Integer count of alphabet combination.
    private double[][] probMatrix; // Probability of alphabets combination.
    private String sourceName; // Name of the training source. e.g. english
    private String fileName; // Name of the training file. e.g. english.corpus

    /**
    * Main method to create SourceModel objects based on input from
    * the command line arguments. (train files and test string to be evaluated)
    *
    * @param args pass the list of the name of .corpus files to be traind
    * and the test string for evaluation based on the trained model.
    */
    public static void main(String[] args) {
        SourceModel[] modelObjects = new SourceModel[args.length - 1];

        for (int i = 0; i < args.length - 1; i++) { // Create multiple models
            modelObjects[i] = new SourceModel(args[i].split("[.]")[0], args[i]);
        }

        String quotedStr = args[args.length - 1]; // Given quoted string
        System.out.printf("Analyzing: %s%n", quotedStr);

        String maxLang = ""; // Language with highest probability
        double maxLangProb = 0.0; //Maximum probability of likely language
        double sumProb = 0.0; //Sum of probability of each model evaluation
        for (SourceModel objectFinal : modelObjects) {
            sumProb += objectFinal.probability(quotedStr);
        }
        for (SourceModel objectFinal : modelObjects) { //Normalize probability
            double proModel = objectFinal.probability(quotedStr);
            if (proModel > maxLangProb) {
                maxLang = objectFinal.getName();
                maxLangProb = proModel;
            }
            System.out.printf("Probability that test string is %8s: %.2f%n",
                objectFinal.getName(), proModel / sumProb);
        }
        System.out.printf("Test string is most likely %s.%n", maxLang);
    }

    /**
    * Constructor for sourceModel class that read corpus data
    * from .corpus file character by character and save the count of each
    * alphabets in character matrix (charMatrix).
    *
    * @param sourceName Name of the corpus model. e.g. english
    * @param fileName Name of the corpus file. e.g. english.corpus
    * @exception IOException if file not found.
    * @see main
    */
    public SourceModel(String sourceName, String fileName) {
        this.charMatrix = new int[26][26];
        this.probMatrix = new double[26][26];
        this.sourceName = sourceName;
        this.fileName = fileName;

        System.out.printf("Training %s model ... ", this.sourceName);

        FileReader inputStream = null;
        try {
            inputStream = new FileReader(fileName);
            int a = 0, b; // (previous alphabet, next alphabet)
            while ((b = inputStream.read()) != -1) {
                if (Character.isAlphabetic(b)) { // ONLY consider alphabets
                    b = Character.toLowerCase(b); // Change to lower case char
                    if (a != 0) {
                        this.charMatrix[a - 97][b - 97]++; // a = 97 in ascii
                    }
                    a = b;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            System.out.println("\n** Exception occured while reading file");
            e.printStackTrace();
            return;
        }
        for (int r = 0; r < 26; r++) { // Sum int values of a row
            int sumRow = 0;
            for (int ac = 0; ac < 26; ac++) {
                sumRow += this.charMatrix[r][ac];
            }
            for (int c = 0; c < 26; c++) {
                if (this.charMatrix[r][c] == 0) { // Replace 0 with 0.01
                    this.probMatrix[r][c] = 0.01;
                } else {
                    this.probMatrix[r][c] = (double) this.charMatrix[r][c]
                        / (double) sumRow; // Calculate probability
                }
            }
        }
        System.out.printf("done.%n");
    }

    /**
    * Method that return the name of the training model. e.g. english
    *
    * @return String Name of the training model.
    * @see main
    */
    public String getName() {
        return this.sourceName;
    }

    /**
    * Method that return the representation of probability matrix of the
    * traind model.
    *
    * @return String Representation of probability matrix of the
    * traind model.
    */
    public String toString() {
        final String alphabets = "abcdefghijklmnopqrstuvwxyz";
        String modelStr = "";
        modelStr += String.format("Model: %s%n ", this.sourceName);
        for (int fr = 0; fr < 26; fr++) { // Print first row alphabets
            modelStr += String.format("%5c", alphabets.charAt(fr));
        }
        modelStr += String.format("%n");
        for (int r = 0; r < 26; r++) {
            modelStr += String.format("%c ", alphabets.charAt(r));
            for (int c = 0; c < 26; c++) {
                modelStr += String.format("%.2f ", this.probMatrix[r][c]);
            }
            modelStr += String.format("%n");
        }
        return modelStr;
    }

    /**
    * Method that calculate and return the probability of given string to be
    * a language of the specific model. e.g. english
    *
    * @param quotedStr String to be evaluted for its probability of being
    * the language of the given specific model given.
    * @return Double Probability of given string to be a language of the
    * specific model in double format.
    * @see main
    */
    public double probability(String quotedStr) {
        double prob = 1.0;
        int a, b; // a = previous char, b = next char
        quotedStr = quotedStr.replaceAll("[^a-zA-Z]", "");
        for (int i = 0; i < quotedStr.length() - 1; i++) {
            a = (int) Character.toLowerCase(quotedStr.charAt(i));
            b = (int) Character.toLowerCase(quotedStr.charAt(i + 1));
            prob *= this.probMatrix[a - 97][b - 97];
        }
        return prob;
    }
}
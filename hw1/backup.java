import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Histogram {
    public static void main(String[] args) throws FileNotFoundException {
        int totNum = 101;
        String fileName = args[0];
        int numBin = Integer.parseInt(args[1]); //51
        int ranBin = (int) (totNum / numBin); // 101 / 51 = 1.98 = 1
        int addBin = totNum % numBin;
        System.out.println(addBin);
        int[] arrayBin = new int[numBin];
        Scanner scanner = new Scanner(new File(fileName));
        scanner.useDelimiter("[^0-9]+");

        while (scanner.hasNext()) {
            int i = scanner.nextInt();
            int pos = Math.max(0, i - addBin) / ranBin;
            arrayBin[pos]++;
        }
        scanner.close();

        for (int i = numBin - 1; i >= 0; i--) {
            String boxes = "";
            for (int j = 0; j < arrayBin[i]; j++) {
                boxes += "[]";
            }
            int floor = totNum - ranBin;
            System.out.printf("%3d - %2d | %s%n",
                totNum - 1, (floor <= addBin) ? floor - addBin : floor, boxes);
            totNum -= ranBin;
        }
    }
}
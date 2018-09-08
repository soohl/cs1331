import java.io.File;
import java.util.Scanner;

public class Histogram {
    public static void main(String[] args) throws Exception {

        int totNum = 101;
        String fileName = args[0];
        int numBin = Integer.parseInt(args[1]);
        int ranBin = totNum / numBin;
        int addBin = totNum % numBin;

        int[] cntBin = new int[numBin];

        Scanner input = new Scanner(new File(fileName));
        input.useDelimiter("[^0-9]+");
        while (input.hasNext()) {
            int i = input.nextInt();
            int pos = Math.max(0, i - addBin) / ranBin;
            cntBin[pos]++;
        }
        input.close();

        for (int i = numBin - 1; i >= 0; i--) {
            String boxes = "";
            int floor = totNum - ranBin;
            for (int j = 0; j < cntBin[i]; j++) {
                boxes += "[]";
            }
            System.out.printf("%3d - %2d | %s%n",
                totNum - 1, (floor <= addBin) ? floor - addBin : floor, boxes);
            totNum -= ranBin;
        }
    }
}
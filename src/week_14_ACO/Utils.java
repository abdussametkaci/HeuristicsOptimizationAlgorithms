package week_14_ACO;

// Class for common functions (Read file, calculate distance etc.)
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {

    static int numberOfCities;
    static double[][] adjacencyMatrix;

    // We need to write a function that will read the file.
    public static void readFile(String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fileName));

        int nbrLine = -1;

        // The loop will run until the lines in the file are finished.
        while (reader.hasNext()) {
            String nextLine = reader.nextLine(); // Get Next Line

            if (nbrLine == -1) {
                numberOfCities = Integer.parseInt(nextLine.replace("\n", ""));
                adjacencyMatrix = new double[numberOfCities][numberOfCities];
                nbrLine++;
                continue;
            } // Skip First Row
            
            String[] distances = nextLine.replace("\n", "").split("  ");
            for (int i = 0; i < distances.length; i++) {
                adjacencyMatrix[nbrLine][i] = Double.parseDouble(distances[i]);
            }
            // Split Line
            
            nbrLine++;
        }
    }
    
    

}

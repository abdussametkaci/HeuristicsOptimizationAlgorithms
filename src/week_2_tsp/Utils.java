
package week_2_tsp;

// Class for common functions (Read file, calculate distance etc.)

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    
    public static List<City> readFile(String fileName) throws FileNotFoundException{
        Scanner reader = new Scanner(new File(fileName));
        
        int nbrLine = 0;
        int solutionNumber = 0;
        List<City> cityList = new ArrayList<>();
        while (reader.hasNext()) {
            String nextLine = reader.nextLine(); // Get Next Line
            
            if(nbrLine == 0){ 
                nbrLine++;
                continue;
            } // Skip First Row
            
            // Split Line
            String[] coordinates = nextLine.replace("\n", "").split(" ");
            float xCoord = Float.parseFloat(coordinates[0]); // Get X Coordinate
            float yCoord = Float.parseFloat(coordinates[1]); // Get Y Coordinate
            
            // Create the city based on the information read from the file.
            cityList.add(new City("" + solutionNumber++, xCoord, yCoord)); // Add city
        }
        
        return cityList;
    }
    
    public static float euclideanDistance(City cityOne, City cityTwo){
        // Calculate Euclidean Distance
        // YOUR CODE HERE
        
        // --------------
        return (float) Math.sqrt(Math.pow(cityOne.getX_coord() - cityTwo.getX_coord(), 2) + Math.pow(cityOne.getY_coord() - cityTwo.getY_coord(), 2)); // Return objective
    }
    
}

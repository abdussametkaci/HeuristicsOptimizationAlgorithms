package week_2_knapsack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    
    static int knapsackSize; // Capacity of Knapsack
    
    public static List<Item> readFile(String fileName) throws FileNotFoundException{
        Scanner reader = new Scanner(new File(fileName));
        
        int nbrLine = 0;
        int solutionNumber = 0;
        List<Item> itemList = new ArrayList<>();
        while (reader.hasNext()) {
            String nextLine = reader.nextLine(); // Get Next Line
            
            if(nbrLine == 0){ 
                nbrLine++;
                // Read capacity of knapsack from file
                knapsackSize = Integer.parseInt(nextLine.replace("\n", "").split(" ")[1]); 
                continue;
            } // Skip First Row
            
            // Split Line
            String[] line = nextLine.replace("\n", "").split(" ");
            int value = Integer.parseInt(line[0]); // Get Value of Item
            int weight = Integer.parseInt(line[1]); // Get Weight of Item
            
            // Create the item based on the information read from the file.
            itemList.add(new Item("" + solutionNumber++, value, weight)); // Add city
        }
        
        return itemList;
    }
}

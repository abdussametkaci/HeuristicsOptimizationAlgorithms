package week_11_GA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author zekikus
 */
public class Utils {
    
    
    static int knapsackSize; // Capacity of Knapsack
    static int[] values;
    static int[] weights;
    static int totalValue;
    
    public static void readFile(String fileName) throws FileNotFoundException{
        Scanner reader = new Scanner(new File(fileName));
        
        int nbrLine = 0;
        int itemIdx = 0;
        
        
        // The loop will run until the lines in the file are finished.
        while (reader.hasNext()) {
            String nextLine = reader.nextLine(); // Get Next Line
            
            if(nbrLine == 0){ 
                nbrLine++;
                int nbrItem = Integer.parseInt(nextLine.replace("\n", "").split(" ")[0]);
                // Read capacity of knapsack from file
                knapsackSize = Integer.parseInt(nextLine.replace("\n", "").split(" ")[1]);
                values = new int[nbrItem];
                weights = new int[nbrItem];
                continue;
            } // Skip First Row
            
            // Split Line
            String[] line = nextLine.replace("\n", "").split(" ");
            int value = Integer.parseInt(line[0]); // Get Value of Item
            int weight = Integer.parseInt(line[1]); // Get Weight of Item
            
            values[itemIdx] = value;
            weights[itemIdx] = weight;
            totalValue += value; // Penalty Value
            
            itemIdx++;
        }

    }
    
    
    // You can use many selection operation to create the mating pool
    // 1) Get random n solution from the current population (without replacement)
    // 2) Select random k solution from the current population, k must be equal to the population size
    // If you use the second method possibly more than one copy of some individuals in the mating pool (within replacement)
    
    public static Individual[] createMatingPool(Individual[] population, int poolSize){
        
        // Creates the random numbers without replacement
        // Length of the random selected Indexes array will be equal to poolSize in this example
        int[] randIndexes = new Random().ints(0, population.length - 1).distinct().limit(poolSize).toArray();
        
        // Copy selected Individuals from the current population to the matingPool
        Individual[] matingPool = new Individual[poolSize];
        for (int i = 0; i < matingPool.length; i++) {
            matingPool[i] = population[randIndexes[i]];
        }
        
        return matingPool;
    }
    
}

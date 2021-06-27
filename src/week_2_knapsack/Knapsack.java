
package week_2_knapsack;

import java.io.FileNotFoundException;
import java.util.List;

public class Knapsack {
    
    public static void main(String[] args) throws FileNotFoundException {
        List<Item> items = Utils.readFile("src/data/knapsack/ks_4_0");
        
        Solution solution = new Solution();
        solution.createRndSolution(Utils.knapsackSize, items);
        solution.calculateObjective();
        
        // Print Solution and Objective Value
        System.out.println("Objective: " + solution.getObj());
        solution.printSolution();
    }
    
}

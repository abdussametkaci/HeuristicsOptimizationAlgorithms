package week_3_knapsack_hillclimbing;

import java.io.FileNotFoundException;
import java.util.List;

public class Knapsack {
    
    public static Solution hillClimbing(List<Item> items) throws CloneNotSupportedException{
        // Initial Solution
        Solution currentSolution = new Solution();
        currentSolution.createRndSolution(Utils.knapsackSize, items); // Create Random Feasible Solution
        currentSolution.calculateObjective(); // Calculate objective value of solution
        
        int counter = 0;
        while (counter < 100) {
            List<Solution> neighbors = Utils.getNeighbors(currentSolution); // Get neighbors of the solution
            Solution best_neighbor = Utils.getBestNeighbor(neighbors); // Get best neighbor
            
            // Best Neighbor is better than current solution
            if (best_neighbor.getObj() > currentSolution.getObj()) {
                currentSolution = null; // Clear Memory
                currentSolution = best_neighbor;
            } else
                break;
            counter++;
        }
        
        return currentSolution; // Return Best Solution
    }
    
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        List<Item> items = Utils.readFile("src/data/knapsack/ks_4_0");

        Solution bestSolution = hillClimbing(items); // Start Hill Climbing Algorithm

        // Print Solution and Objective Value
        System.out.println("Objective of Best Solution: " + bestSolution.getObj());
        bestSolution.printSolution();
    }

}

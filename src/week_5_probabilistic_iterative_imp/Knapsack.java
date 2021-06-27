package week_5_probabilistic_iterative_imp;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

public class Knapsack {
    
    public static Solution probabilisticIterativeImp(List<Item> items) throws CloneNotSupportedException{
        
        final double CONSTANT_PROB = 0.5; // Constant Probability

        // Initial Solution
        Solution currentSolution = new Solution();
        currentSolution.createRndSolution(Utils.knapsackSize, items); // Create Random Feasible Solution
        currentSolution.calculateObjective(); // Calculate objective value of solution
        
        Solution bestSolution = new Solution(); // Store Best Solution
        
        int counter = 0;
        // We will repeat these operations n times
        while (counter < 100) {
            List<Solution> neighbors = Utils.getNeighbors(currentSolution); // Get neighbors of the solution
            Collections.shuffle(neighbors);
            Solution s_prime = neighbors.get(0); // Select Random Candidate Soluton
            
            // Calculate Delta
            float delta = s_prime.getObj() - currentSolution.getObj();
            
            // If new solution(s_prime) better than current (s)
            if(delta > 0){
                // Accept New Solution
                currentSolution = s_prime;
                
                // Compare Objective Value of New Solution and Best Solution
                if(s_prime.getObj() > bestSolution.getObj()){
                    bestSolution = s_prime;
                }
            } 
            // If new solution worse than current (s)
            else if(Math.random() > CONSTANT_PROB){
                // Accept with 0.5 probability
                currentSolution = s_prime;
            }
            
            counter++;
        }
        
        return bestSolution; // Return Best Solution
    }
    
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        List<Item> items = Utils.readFile("src/data/knapsack/ks_4_0");

        Solution bestSolution = probabilisticIterativeImp(items); // Start Probabilistic Iterative Improvement Algorithm

        // Print Solution and Objective Value
        System.out.println("Objective of Best Solution: " + bestSolution.getObj());
        bestSolution.printSolution();
    }

}

package week_6_simulated_annealing;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

public class Knapsack {

    public static Solution simulatedAnnealing(List<Item> items) throws CloneNotSupportedException {

        double T = 10; // Initial T value
        final int MIN_T_VALUE = 1; // Minimum Temperature value
        final int MAX_INNER_LOOP = 10; // Maximum inner loop count
        final double cooling_rate = 0.95; // Cooling Rate
        
        Solution s = new Solution(); // Create Initial Solution Object
        s.createRndSolution(Utils.knapsackSize, items); // Create random solution (Solution ‘s’)
        s.calculateObjective(); // Calculate objective value of solution ‘s’.

        Solution bestSolution = new Solution(); // Store the best solution

        while (T > MIN_T_VALUE) {
            int inner_loop = 0;
            while (inner_loop < MAX_INNER_LOOP) {
                
                List<Solution> neighbors = Utils.getNeighbors(s); // Create random neighbor of solution ‘s’.
                Collections.shuffle(neighbors);
                Solution s_prime = neighbors.get(0); // Get Random Neighbor and named this solution s_prime

                float delta = s_prime.getObj() - s.getObj(); // Calculate delta value (Objective value of ‘s_prime’ – Objective value of ‘s’).

                // If new solution (‘s_prime’) better than current (‘s’):
                if (delta > 0) {
                    // Accept New Solution
                    s = s_prime; // Accept solution ‘s_prime’.

                    // Compare objective value of new solution and best solution. 
                    if (s_prime.getObj() > bestSolution.getObj()) {
                        bestSolution = s_prime; // If the new solution is better than the best, change it to the best.
                    }
                } else {
                    // If new solution worse than current (‘s’):
                    if (Math.random() < Math.exp(-(delta / T))) {
                        // Accept new solution (s_prime) with Math.exp(-(delta / T)) probability
                        // Acceptance probability depends on respective corruption in the evaluation function value
                        s = s_prime;
                    }
                }

                inner_loop++;
            }
            
            T = T * cooling_rate; // Reduce the system temperature
            // This steps effect the acceptance probability
            // As the temperature reduces, the likelihood that bad solutions will be accepted decreases.
        }

        return bestSolution; // Return Best Solution
    }

    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {

        List<Item> items = Utils.readFile("src/data/knapsack/ks_4_0");

        Solution bestSolution = simulatedAnnealing(items);

        // Print Best Solution and objective value of this solution
        System.out.println("Objective of Best Solution:" + bestSolution.getObj());
        bestSolution.printSolution();
    }

}

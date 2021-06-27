/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week_6_tsp_simulated_annealing;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

public class TSP {

    public static Solution simulatedAnnealing(List<City> cities, double T, int MAX_INNER_LOOP) throws CloneNotSupportedException {

        //double T = 10000; // Initial T value
        final int MIN_T_VALUE = 1; // Minimum Temperature value
        //final int MAX_INNER_LOOP = 1000; // Maximum inner loop count
        final double cooling_rate = 0.95; // Cooling Rate
        int startPoint = 0; // first cities
        int endPoint = cities.size() - 1;   // last cities

        Solution s = new Solution(cities.size()); // Create Initial Solution Object
        s.createRndVisitOrder(cities, startPoint, endPoint); // Create random solution (Solution ‘s’)
        s.calculateObjective(); // Calculate objective value of solution ‘s’.

        Solution bestSolution = new Solution(cities.size()); // Store the best solution
        bestSolution.createRndVisitOrder(cities, startPoint, endPoint); // Create random solution (Solution ‘s’)
        bestSolution.calculateObjective(); // Calculate objective value of solution ‘s’.

        while (T > MIN_T_VALUE) {
            int inner_loop = 0;
            while (inner_loop < MAX_INNER_LOOP) {

                List<Solution> neighbors = Utils.getRndNeighbors(s); // Create random neighbor of solution ‘s’.
                Collections.shuffle(neighbors);
                Solution s_prime = neighbors.get(0); // Get Random Neighbor and named this solution s_prime

                float delta = s_prime.getObj() - s.getObj(); // Calculate delta value (Objective value of ‘s_prime’ – Objective value of ‘s’).

                // If new solution (‘s_prime’) better than current (‘s’):
                if (delta < 0) {
                    // Accept New Solution
                    s = s_prime; // Accept solution ‘s_prime’.

                    // Compare objective value of new solution and best solution. 
                    if (s_prime.getObj() < bestSolution.getObj()) {
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
        // tsp_5_1, T = 10, MAX_INNER_LOOP = 10, answer = 4.0
        // tsp_70_1, T = 10000, MAX_INNER_LOOP = 1000, answer = 742.28
        
        List<City> cities = Utils.readFile("src/data/tsp/tsp_5_1");
        Solution bestSolution = simulatedAnnealing(cities, 10, 10);

        // Print Best Solution and objective value of this solution
        System.out.println("Objective of Best Solution:" + bestSolution.getObj());
        bestSolution.printVisitingOrder();
        System.out.println("");
        
        
        List<City> cities2 = Utils.readFile("src/data/tsp/tsp_70_1");
        Solution bestSolution2 = simulatedAnnealing(cities2, 10000, 1000);

        // Print Best Solution and objective value of this solution
        System.out.println("Objective of Best Solution:" + bestSolution2.getObj());
        bestSolution2.printVisitingOrder();
        System.out.println("");
    }

}

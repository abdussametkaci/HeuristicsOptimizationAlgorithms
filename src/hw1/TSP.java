/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

public class TSP {

    public static Solution probabilisticIterativeImp(List<City> cities, int N) throws CloneNotSupportedException {
        final double CONSTANT_PROB = 0.5; // Constant Probability
        int startPoint = 0; // first cities
        int endPoint = cities.size() - 1;   // last cities

        Solution currentSolution = new Solution(cities.size());
        currentSolution.createRndVisitOrder(cities, startPoint, endPoint);
        currentSolution.calculateObjective(); // calculate objective value of solution

        Solution bestSolution = new Solution(cities.size()); // store best solution
        bestSolution.createRndVisitOrder(cities, startPoint, endPoint);
        bestSolution.calculateObjective();
        int counter = 0;
        // we will repeat these operations N times
        while (counter < N) {

            List<Solution> neighbors = Utils.getRndNeighbors(currentSolution); // get random neighbors of the solution
            Collections.shuffle(neighbors);
            Solution s_prime = neighbors.get(0); // select random solution

            float delta = s_prime.getObj() - currentSolution.getObj();  // calcultae delta
            // if new solution (s_prime) better than current (s)
            if (delta < 0) {    // minimization problem
                // accept new solution
                currentSolution = s_prime;
                // compare objective value of new solution and best solution
                if (s_prime.getObj() < bestSolution.getObj()) { // if objective value of s_prime is lower than objective value of best solution
                    bestSolution = s_prime;
                }
            } else if (Math.random() > CONSTANT_PROB) {  // if new solution worse than current (s)
                currentSolution = s_prime; // accept with 0.5 probability
            }

            counter++;

        }
        
        return bestSolution; // return best solution
    }

    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        // tsp_5_1   -> 50 iteration, answer = 4.0
        // tsp_70_1  -> 10000000 iteration, answer = 2377.16
        // tsp_100_1 -> 10000000 iteration, answer = 115470.2
        // I write same iteration for tesp_70_1 and tesp_100_1, because high iterations work for a long time
        
        List<City> cities = Utils.readFile("src/data/tsp/tsp_5_1");
        // Probabilistic Iterative Improvement Algorithm
        Solution bestSolution = probabilisticIterativeImp(cities, 50);  // 50 iteration

        // print solution and objective value
        System.out.println("Objective of Best Solution: " + bestSolution.getObj());
        bestSolution.printVisitingOrder();
        System.out.println("");
        
       
        List<City> cities2 = Utils.readFile("src/data/tsp/tsp_70_1");
        Solution bestSolution2 = probabilisticIterativeImp(cities2, 10000000);

        System.out.println("Objective of Best Solution: " + bestSolution2.getObj());
        bestSolution2.printVisitingOrder();
        System.out.println("");
        
        
        List<City> cities3 = Utils.readFile("src/data/tsp/tsp_100_1");
        Solution bestSolution3 = probabilisticIterativeImp(cities3, 10000000);

        System.out.println("Objective of Best Solution: " + bestSolution3.getObj());
        bestSolution3.printVisitingOrder();
        System.out.println("");

    }

}

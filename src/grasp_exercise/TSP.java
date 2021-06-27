/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSP {

    static int counter = 0;
    static int seed = 90; // YOU CAN ONLY CHANGE THIS VALUE

    public static void localSearch(Solution s) {
        boolean improved = true;
        while (improved) {
            improved = false;
            for (int i = 1; i < s.getVisitingOrder().size() - 1; i++) {
                for (int j = i + 1; j < s.getVisitingOrder().size(); j++) {

                    List<City> temp = new ArrayList<>(s.getVisitingOrder());
                    Collections.swap(temp, j, i);

                    Solution candidateSolution = new Solution(temp);
                    candidateSolution.calculateObjective();
                    counter++;

                    if (candidateSolution.getObj() < s.getObj()) {
                        s = candidateSolution;
                        improved = true;
                    }
                }
            }
        }

    }

    public static Solution graspAlgorithm(List<City> cities, int startPoint, int endPoint) {

        final int FIXED_OBJ = 2700;

        Solution bestSolution = new Solution(cities.size());
        bestSolution.setObj(Integer.MAX_VALUE);

        while (bestSolution.getObj() > FIXED_OBJ) {
            Solution s_prime = Utils.constructGreedyRandomizedSolution(cities, startPoint, endPoint);
            s_prime.calculateObjective();
            localSearch(s_prime);

            // Compare the objective values of s_best and s solutions
            if (s_prime.getObj() < bestSolution.getObj()) {
                bestSolution = s_prime;
            }

            counter++;

        }

        System.out.println("Number of iteration: " + counter);

        return bestSolution;
    }

    public static void main(String[] args) throws FileNotFoundException {

        List<City> cities = Utils.readFile("src/data/tsp/tsp_70_1");
        int startPoint = 0;
        int endPoint = cities.size() - 1;

        Solution bestSolution = graspAlgorithm(cities, startPoint, endPoint);

        // Print Best Solution Visiting Order and Objective Value
        System.out.println("Objective: " + bestSolution.getObj());
        bestSolution.printVisitingOrder();

    }

}

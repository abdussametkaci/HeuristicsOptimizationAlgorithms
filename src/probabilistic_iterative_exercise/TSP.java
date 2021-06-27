package probabilistic_iterative_exercise;
/*

    Implemented by Abdussamed KACI

*/

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TSP {
    
    static int seed = 0;
    static Random rnd = new Random(seed);
    
    public static Solution probabilisticIterativeImp(List<City> cities, int N) throws CloneNotSupportedException {
        final double CONSTANT_PROB = 0.5; // Constant Probability
        int startPoint = 0; // first cities
        int endPoint = cities.size() - 1;   // last cities
        final int FIXED_OBJ = 2700;

        Solution currentSolution = new Solution(cities.size());
        currentSolution.createRndVisitOrder(cities, startPoint, endPoint);
        currentSolution.calculateObjective(); // calculate objective value of solution

        Solution bestSolution = new Solution(cities.size()); // store best solution
        bestSolution.createRndVisitOrder(cities, startPoint, endPoint);
        bestSolution.calculateObjective();
        int counter = 0;
        // we will repeat these operations N times
        while (bestSolution.getObj() > FIXED_OBJ) {

            List<Solution> neighbors = Utils.getRndNeighbors(currentSolution); // get random neighbors of the solution
            Collections.shuffle(neighbors, rnd);
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
            } else if (rnd.nextDouble() > CONSTANT_PROB) {  // if new solution worse than current (s)
                currentSolution = s_prime; // accept with 0.5 probability
            }

            counter++;

        }
        
        System.out.println("Number of iteration:" + counter);
        return bestSolution; // return best solution
    }

    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        
        List<City> cities2 = Utils.readFile("src/data/tsp/tsp_70_1");
        Solution bestSolution2 = probabilisticIterativeImp(cities2, 10000000);

        System.out.println("Objective of Best Solution: " + bestSolution2.getObj());
        bestSolution2.printVisitingOrder();
        System.out.println("");
      

    }

}

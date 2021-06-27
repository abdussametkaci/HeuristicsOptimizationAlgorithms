
/*

    Implemented by Abdussamed KACI

 */
package tabu_search_lab_exercise;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class TSP {

    static int seed = 0;
    static Random rnd = new Random(seed);
    
    // for this methos, I used code of previous lab and pseudo code
    public static Solution tabuSearch(List<City> cities,  final int MAX_TABU_SIZE,  final int MAX_ITERATION) throws CloneNotSupportedException {
        int start = 0; // start poitn in cities
        int end = cities.size() - 1; // end point in cities
        
        // create best canditae solution and sBest solution
        Solution bestCandidate = new Solution(cities.size());
        bestCandidate.createRndVisitOrder(cities, start, end);
        bestCandidate.calculateObjective();

        Solution sBest = new Solution(cities.size());
        sBest.createRndVisitOrder(cities, start, end);
        sBest.calculateObjective();

        Queue<Solution> tabuList = new LinkedList<>(); // Add created first solution to tabuList
        tabuList.add(bestCandidate);

        int counter = 0;
        while (counter < MAX_ITERATION) {
            List<Solution> neighbors = Utils.getRndNeighbors(bestCandidate); // create the neighbors of the solution bestCandidate
            
            // get random neighbor and it is named as bestCandidate
            Collections.shuffle(neighbors);
            bestCandidate = neighbors.get(0);
            
            // iterate each solution (sCandidate) in the tabuList and compare the solutions
            for (Solution sCandidate : neighbors) {
                if (!Utils.isTabuListContain(tabuList, sCandidate.getRndSolution()) && (sCandidate.getObj() < bestCandidate.getObj())) { // minimize problem
                    bestCandidate = sCandidate;
                }
            }
            
            // if new solution (bestCandidate) better than best solution (sBest), change it to the best
            if (bestCandidate.getObj() < sBest.getObj()) { // minimize problem
                sBest = bestCandidate;
            }

            if (!Utils.isTabuListContain(tabuList, bestCandidate.getRndSolution())) {
                tabuList.add(bestCandidate); // add bestCandidate solution into the tabuList.
            }
            
            // check tabuList size
            // if the maximum tabu size, tabu tenure is exceeded, pop the leftmost element of the queue.
            if (tabuList.size() > MAX_TABU_SIZE) {
                tabuList.remove();
            }
            
            counter++; // increase counter
        }

        return sBest; // return best solution (sBest)
    }

    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        
        // for tsp_70_1 ->  MAX_TABU_SIZE = 200, MAX_ITERATION = 1000000, Result = 738.0486
        // for tsp_100_1 -> MAX_TABU_SIZE = 200, MAX_ITERATION = 2000000, Result = 24566.61
        
        List<City> cities = Utils.readFile("src/data/tsp/tsp_100_1");

        Solution bestSolution = tabuSearch(cities, 200, 2000000); // max tabu size 200, max iteration 2000000 
        
        // Print best solution and objective value of this solution
        System.out.println("Objective value of the best solution:" + bestSolution.getObj());
        bestSolution.printVisitingOrder();
        System.out.println("");
        
        /*
        List<City> cities = Utils.readFile("src/data/tsp/tsp_70_1");

        Solution bestSolution = tabuSearch(cities);
        
        // Print best solution and objective value of this solution
        System.out.println("Objective value of the best solution:" + bestSolution.getObj());
        bestSolution.printVisitingOrder();
        System.out.println("");
        */

    }

}

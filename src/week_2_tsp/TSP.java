/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week_2_tsp;


import java.io.FileNotFoundException;
import java.util.List;

public class TSP {

    public static void main(String[] args) throws FileNotFoundException {

        List<City> cities = Utils.readFile("src/data/tsp/tsp_5_1");
        int startPoint = 0;
        int endPoint = 2;

        Solution solution = new Solution(cities.size());
        solution.createRndVisitOrder(cities, startPoint, endPoint);
        solution.calculateObjective();

        // Print Solution Visiting Order and Objective Value
        System.out.println("Objective: " + solution.getObj());
        solution.printVisitingOrder();
    }

}

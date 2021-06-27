/*
Added getRndSolution and clone methods
*/

package hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    
    // Solution class store each traversal ordering, tsp cycle
    
    private float obj; // Objective Value of Solution
    private int numberCity; // Number of city
    private List<City> visitingOrder; // Store Visit Order (Each item must be City)
    
    // Default Constructor 
    public Solution(int numberCity) {
        this.numberCity = numberCity;
        visitingOrder = new ArrayList<>();
    }

    Solution() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Create Random Visit Order
    // Params:
        // Cities: Each location, city in tsp file
        // startPoint: Index of starting point
        // endPoint: Index of end point
    public void createRndVisitOrder(List<City> cities, int startPoint, int endPoint){
        // First city must be in first index and the last city must be in last index. 
        // That's why we didn't add them to the list.   
        for (int i = 0; i < cities.size(); i++) {
            if(i == startPoint || i == endPoint)
                continue;
            else
                // We add each city sequentially except starting and ending points.
                visitingOrder.add(cities.get(i)); 
        }
        
        Collections.shuffle(visitingOrder); // Randomly shuffle the location of the cities.
        visitingOrder.add(0, cities.get(startPoint)); // Add start city to visiting order
        visitingOrder.add(visitingOrder.size(), cities.get(endPoint)); // Add end city to visiting order
    }
    
    // Calculate objective value of solution
    public void calculateObjective(){
        for (int i = 0; i < visitingOrder.size() - 1; i++) {
            City cityOne = visitingOrder.get(i);
            City cityTwo = visitingOrder.get(i + 1);
            obj += Utils.euclideanDistance(cityOne, cityTwo);
        }
        
        // Calculate Distance Between Start and End point, and then add this value to objective
        obj += Utils.euclideanDistance(visitingOrder.get(0), visitingOrder.get(visitingOrder.size() - 1));
    }
    
    public void printVisitingOrder(){
        for (City city : visitingOrder) {
            System.out.print(city.getName() + " ");
        }
    }

    public float getObj() {
        return obj;
    }
    
    // -------------------------- MY CODES ---------------------------------
    public List<City> getRndSolution(){
        return visitingOrder;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Solution s = new Solution(numberCity); // create new solution object
        s.getRndSolution().clear();
        
        // copy each city from the original solution to the new solution.
        for (City city : this.visitingOrder) {
            City new_city = new City(city.getName(), city.getX_coord(), city.getY_coord()); // create city object
            s.getRndSolution().add(new_city);   // add city
        }
        return s; // return solution object
    }
    
}

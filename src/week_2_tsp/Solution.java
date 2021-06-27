package week_2_tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    
    private float obj; // Objective Value of Solution
    private int numberCity; // Number of city
    private List<City> visitingOrder; // Store Visit Order (Each item must be City)
    
    // Default Constructor 
    public Solution(int numberCity) {
        this.numberCity = numberCity;
        visitingOrder = new ArrayList<>();
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
                visitingOrder.add(cities.get(i));
        }
        
        Collections.shuffle(visitingOrder); // Randomly shuffle the location of the cities.
        visitingOrder.add(0, cities.get(startPoint)); // Add start city to visiting order
        visitingOrder.add(visitingOrder.size(), cities.get(endPoint)); // Add end city to visiting order
    }
    
    // Calculate objective value of solution
    public void calculateObjective(){
        // YOUR CODE HERE
        float sum = 0;
        int i;
        for(i = 0; i < visitingOrder.size() - 1; i++){
            sum += Utils.euclideanDistance(visitingOrder.get(i), visitingOrder.get(i + 1));
        }
        sum += Utils.euclideanDistance(visitingOrder.get(0), visitingOrder.get(i));
        obj = sum;
        // --------------
    }
    
    public void printVisitingOrder(){
        for (City city : visitingOrder) {
            System.out.print(city.getName() + " ");
        }
    }

    public float getObj() {
        return obj;
    }
    
}

package week_5_probabilistic_iterative_imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution implements Cloneable{
    
    private final int PENALTY_VALUE = 100; // Integer.MAX_VALUE
    private float obj; // Objective Value of Solution
    private List<Item> rndSolution; // Each index shows whether that item is selected or not

    public Solution() {
        rndSolution = new ArrayList<>(); // Create Empty Solution
    }

    // Create Random Solution
    // Params:
        // knapsackSize: Capacity of knapsack
        // itemList: Each item in knapsack file
    
    // This function creates the random solution and stores that solution in the list.
    public void createRndSolution(int knapsackSize, List<Item> itemList) {

        // Used Capacity
        int capacity = 0;

        // Copy each item from itemList to rndSolution
        rndSolution.addAll(itemList);

        // Shuffle List - Solution
        Collections.shuffle(rndSolution);

        // We'll randomly determine which items to choose.
        for (Item item : itemList) {
            // Is the knapsack size exceeded?
            // Will the capacity exceed when I add the item to the knapsack?
            if (item.getWeight() + capacity <= knapsackSize) { // Capacity not exceed
                // Set 0 or 1                
                // 1: Item is selected, 0: Item is not selected
                // We will determine if the item is selected or not.
                item.setIsSelected((int) Math.round(Math.random()));

                if (item.getIsSelected() == 1) { // Increase used capacity, if item is selected, 
                    capacity += item.getWeight();
                }
            } else {
                // Capacity exceed
                item.setIsSelected(0);
            }
        }
    }
    
    // Calculate Objective Value
    public void calculateObjective() {
        obj = 0;
        for (Item item : rndSolution) {
            obj += (item.getIsSelected() * item.getValue());
        }
        obj -= checkFeasibility();
    }
    
    // Check feasibility and return Penalty value
    // This function will be your homework
    // You will implement this function
    public int checkFeasibility(){
        int usedCapacity = 0;
        
        // Total Weights of Selected Items Exceed the Knapsack Capacity??
        for (Item item : rndSolution) {
            usedCapacity += (item.getWeight() * item.getIsSelected());
        }
        
        
        if(usedCapacity <= Utils.knapsackSize) // No, return 0 penalty
            return 0;
        else
            return PENALTY_VALUE; // Yes, return constant penalty
            // We want to penalize this solution
    }

    public void printSolution() {
        System.out.println("Item Value - isSelected");
        System.out.println("-----------------------");
        for (Item item : rndSolution) {
            System.out.println(item.getValue()+ "-" + item.getIsSelected() + " ");
        }
    }

    public float getObj() {
        return obj;
    }

    public List<Item> getRndSolution() {
        return rndSolution;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Solution s = new Solution(); // Create new solution object
        s.getRndSolution().clear();
        
        // Copy each item from the original solution to the new solution.
        for (Item item : this.rndSolution) {
            Item new_item = new Item(item.getName(), item.getValue(), item.getWeight());
            new_item.setIsSelected(item.getIsSelected());
            s.getRndSolution().add(new_item);
        }
        return s; // We have a copy of this solution object.
    }
    

}

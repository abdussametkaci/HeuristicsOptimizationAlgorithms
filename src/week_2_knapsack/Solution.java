package week_2_knapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    private float obj; // Objective Value of Solution
    private List<Item> rndSolution; // Each index shows whether that item is selected or not

    public Solution() {
        rndSolution = new ArrayList<>(); // Create Empty Solution
    }

    // Create Random Solution
    // Params:
    // knapsackSize: Capacity of knapsack
    // itemList: Each item in knapsack file
    public void createRndSolution(int knapsackSize, List<Item> itemList) {

        // Used Capacity
        int capacity = 0;

        // Copy each item from itemList to rndSolution
        rndSolution.addAll(itemList);

        // Shuffle List - Solution
        Collections.shuffle(rndSolution);

        for (Item item : itemList) {
            // Is the knapsack size exceeded?
            if (item.getWeight() + capacity <= knapsackSize) { // Capacity not exceed
                // Set 0 or 1                
                // 1: Item is selected, 0: Item is not selected
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

    public void calculateObjective() {
        // YOUR CODE HERE
        float sum = 0;
        
        for(Item item : rndSolution){
            sum += item.getValue() * item.getIsSelected();
        }
        
        obj = sum;
        // --------------
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

}

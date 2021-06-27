/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week_4_graph_coloring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Solution {

    // Stores the number of neighbor for each node
    private List<Entry<Integer, Integer>> degreeNodes; // Degree: Number of neighbor
    private int[] nodeColors; // Stores the color of each node.
    private int objective; // Store the objective value of solution
    private int number_of_color; // Used number of color

    // 
    public void calcNeighborCount(int[][] adjacencyMatrix) {
        // Calculate the degree of each node.
        // And then order the nodes in decreasing order of their degress
        // Return [(node, degree), ...] pairs
        Map<Integer, Integer> newMap = new TreeMap<>(Collections.reverseOrder());
        for (int node = 0; node < adjacencyMatrix.length; node++) {
            int degree = 0;
            for (int edge = 0; edge < adjacencyMatrix[node].length; edge++) {
                degree += adjacencyMatrix[node][edge];
            }
            newMap.put(node, degree);
        }

        degreeNodes = new ArrayList<>(newMap.entrySet());
        degreeNodes.sort(Entry.comparingByValue()); // Sort (node, degree) pairs by degree value
        Collections.reverse(degreeNodes); // Reverse the order.
    }

    public void createRndSolution(int[][] adjacencyMatrix) {

        // Calculate the degree of each node.
        calcNeighborCount(adjacencyMatrix);

        // Reset nodeColors array
        nodeColors = new int[adjacencyMatrix.length];
        for (int i = 0; i < nodeColors.length; i++) {
            nodeColors[i] = -1;
        }

        int index = 0; // Set index to zero
        number_of_color = 0; // Set number_of_color to zero

        // Iterate each (node, degree) pair in the degreeNodes Map
        for (Entry<Integer, Integer> record : getDegreeNodes()) {
            int node = record.getKey(); // Get node name
            int degree = record.getValue(); // Get degree of this node

            // Find assigned color to each nodes
            List<Integer> usedColors = new ArrayList<>();
            for (int nodeColor : nodeColors) {
                if (nodeColor != -1) {
                    usedColors.add(nodeColor);
                }
            }

            // Start from 1st node assign color    
            if (index == 0) {
                nodeColors[node] = number_of_color;
            } else {
                // Find the colors that this node's neighbors use.
                List<Integer> neighborColors = new ArrayList<>();
                for (int neighbor = 0; neighbor < adjacencyMatrix[node].length; neighbor++) {
                    // Node "node" and Node "neighbor" are neighbors?
                    // And node "neighbor" has any assigned color?
                    if (adjacencyMatrix[node][neighbor] == 1 && nodeColors[neighbor] != -1) {
                        neighborColors.add(nodeColors[neighbor]); // Add the color of this neighbor to the neighborColor list
                    }
                }

                // Remove neighborColors from the usedColors list, because we need non-used colors
                usedColors.removeAll(neighborColors); // Non-used Colors
                // Is there a color that can be used among the previously used colors?
                if (usedColors.size() > 0) {
                    // Yes, Select random color and assign this color to the node
                    Collections.shuffle(usedColors);
                    nodeColors[node] = usedColors.get(0);
                } else {
                    // # Otherwise, select new color and assign this color to the node
                    number_of_color++;
                    nodeColors[node] = number_of_color;
                }
            }

            index++;
        }
    }

    // Calculate objective of this solution
    // This function is your homework
    // You will implement this function
    public int calculateObjective(int[][] adjacencyMatrix) {
        // Steps:
        boolean isAllDifferent = true;
        // 1) Iterate each row in the adjacencyMatrix
        kill_loop:
        for (Entry<Integer, Integer> record : getDegreeNodes()) {
            // 2) Take the first node and check the colors of its neighbors.
            int node = record.getKey();
            for (int neighbor = 0; neighbor < adjacencyMatrix[node].length; neighbor++) { // 3) Apply step 2 for each nodes
                //  a) If two adjacent nodes have the same color, set objective value to the -1 and return
                if (adjacencyMatrix[node][neighbor] == 1 && nodeColors[node] == nodeColors[neighbor]) {
                    isAllDifferent = false;
                    objective = -1; // set objective value to the -1 and return
                    break kill_loop;
                }
            }
        }
        
        // 4) If all adjacent nodes do not have the same color, return the number_of_color + 1 and return
        if(isAllDifferent){
            objective = number_of_color + 1;
        }

        // YOUR CODE HERE
        // -------------
        return objective;
    }

    public void printSolution() {
        System.out.println("Objective of the solution: " + objective);
        for (int nodeColor : nodeColors) {
            System.out.print(nodeColor + " ");
        }
        System.out.println("");
    }

    public List<Entry<Integer, Integer>> getDegreeNodes() {
        return degreeNodes;
    }

    public void setNodeColors(int[] nodeColors) {
        this.nodeColors = nodeColors;
    }

}

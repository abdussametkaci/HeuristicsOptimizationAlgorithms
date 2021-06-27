/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week_14_ACO;

/**
 *
 * @author zekikus
 */
public class Ant {
    
    private int trailSize; // Store the trail size of trails array
    private int trail[]; // Store the intensity of trail or pheromone;
    private boolean visited[]; // Store the visit value of each city or each path

    public Ant(int tourSize) {
        this.trailSize = tourSize;
        this.trail = new int[tourSize];
        this.visited = new boolean[tourSize];
    }
    
    // Mark the given city as visited
    public void visitCity(int currentIndex, int city){
        trail[currentIndex + 1] = city;
        visited[city] = true;
    }
    
    // Return the visit value of the city given as parameter
    public boolean visited(int idx){
        return visited[idx];
    }
    
    // Compute total cost of this tour
    // Calculate the sum of the distances between the cities where pheromone left
    public double trailLength(double graph[][]){
        double length = graph[trail[trailSize - 1]][trail[0]];
        for (int i = 0; i < trailSize - 1; i++) {
            length += graph[trail[i]][trail[i + 1]];
        }
        
        return length;
    }
    
    // Mark all cities as non-visited
    public void clear(){
        for (int i = 0; i < trailSize; i++) {
            visited[i] = false;
        }
    }
    
    public int[] getTrail(){
        return trail;
    }
    
    
}

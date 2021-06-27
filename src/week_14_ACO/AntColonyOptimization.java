/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week_14_ACO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author zekikus
 */
public class AntColonyOptimization {
    
    private double c = 1.0; // Initial number of trail, pheromone intensity
    private double alpha = 1; // Controls the pheromone importance
    private double beta = 5; // Controls the distance priority
    private double evaporation = 0.5; // Shows the percent how much the pheromone is evaporating in every iteration, tour
    private double Q = 500; // Provides information about the total amount of pheromone left on the trail by each Ant
    private double antFactor = 0.8; // How many ants we will use per city
    private double randomFactor = 0.01; // For randomness in per simulations
    
    private int maxIterations = 1000;
    
    private int numberOfCities;
    private int numberOfAnts;
    private double graph[][]; // Store the adjacency matrix, store distance values between each city
    private double trails[][]; // Store the intensity value of trail, pheromone left each city
    private List<Ant> ants = new ArrayList<>();
    private Random rnd = new Random();
    private double probabilities[];
    
    private int currentIndex;
    private int [] bestTourOrder;
    private double bestTourLength;

    public AntColonyOptimization(int nbrCities) {
        graph = Utils.adjacencyMatrix;
        numberOfCities = graph.length;
        numberOfAnts = (int)(numberOfCities * antFactor);
        
        trails = new double[numberOfCities][numberOfCities]; // It keeps the pheromone number on the each path
        probabilities = new double[numberOfCities];
        
        // Create each ant
        for (int i = 0; i < numberOfAnts; i++) {
            ants.add(new Ant(numberOfCities));
        }
    }
    
    public void startACO(){
        for (int i = 0; i < 3; i++) {
            System.out.println("Run #" + i);
            solve();
        }
    }
    
    // Use this method to run the ACO logic
    public void solve(){
        // Initialize each ant, and place randomly to the each city
        setupAnts();
        
        // Clear pheromones, trails from each path
        clearTrails();
        
        for (int i = 0; i < maxIterations; i++) {
            moveAnts(); //  Select next city for each ant in the swarm
            updateTrails();
            updateBest();
        }
        
        System.out.println("Best tour length: " + bestTourLength);
        System.out.println("Best tour order:" + Arrays.toString(bestTourOrder));
    }
    
    // Prepare ants for the ant colony optimization simulation
    public void setupAnts(){
        for (Ant ant : ants) {
            ant.clear(); // Clear pheromone for each path, or city
            // At the beginning each ants are placed to the n cities randomly
            ant.visitCity(-1, rnd.nextInt(numberOfCities));
        }
        
        currentIndex = 0;
    }
    
    // At each iteration, move all ant
    public void moveAnts(){
        for (int i = currentIndex; i < numberOfCities - 1; i++) {
            for (Ant ant : ants) {
                // We need to choose the next city for all ants, remembering that each ant tries to follow other ant's trail, or pheromone intensity
                ant.visitCity(currentIndex, selectNextCity(ant));
            }
            currentIndex++;
        }
    }
    
    // Select next city for each ant given as parameter
    public int selectNextCity(Ant ant){
        
        // The most important part is to properly select next city to visit
        // We should select the next city based on the probability logic
        
        // First step; we can check if Ant should visit a random city
        if(rnd.nextDouble() < randomFactor){
            for (int i = 0; i < numberOfCities; i++) {
                if(!ant.visited(i))
                    return i; // Select random not visited city
            }
        }
        
        // If we did not select any random city, we need to calculate probabilities to select the next city,
        // We can do this by storing the probability of moving to each city in the probability array.
       calculateProbabilities(ant);
       
       // After we calculate probabilities, we can decide to which city to go
       double r = rnd.nextDouble();
       double sum = 0;
       
        for (int i = 0; i < numberOfCities; i++) {
            sum += probabilities[i];
            if(sum >= r)
                return i;
        }
        
        return -1;
    }
    
    // Calculate the next city picks probabilities
    public void calculateProbabilities(Ant ant){
        int i = ant.getTrail()[currentIndex];
        double pheromone = 0.0; // Store the total pheromone for city j
        
        for (int j = 0; j < numberOfCities; j++) {
            if(!ant.visited(j)){
                pheromone += Math.pow(trails[i][j], alpha) * Math.pow(1.0 / graph[i][j], beta);
            }
        }
        
        for(int j = 0; j < numberOfCities; j++){
            if(ant.visited(j)){
                probabilities[j] = 0.0;
            } else{
                double numerator = Math.pow(trails[i][j], alpha) * Math.pow((1 / graph[i][j]), beta);
                probabilities[j] = numerator / pheromone;
            }
        }
    }
    
    // Update trails that ants used
    public void updateTrails(){
        // After n iterations of this process, every ant completes tour
        
        // We should update trails or pheromone which is left
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                trails[i][j] = trails[i][j] * evaporation;
            }
            
        }
        
        for (Ant ant : ants) {
            double contribution = Q / ant.trailLength(graph);
            for (int i = 0; i < numberOfCities - 1; i++) {
                trails[ant.getTrail()[i]][ant.getTrail()[i + 1]] += contribution;
            }
            trails[ant.getTrail()[numberOfCities - 1]][ant.getTrail()[0]] += contribution;
        }
    }
    
     /**
     * Update the best solution
     */
    private void updateBest() {

        // We need to update the best solution in order to keep the reference to it
        if (bestTourOrder == null) {
            bestTourOrder = ants.get(0).getTrail();
            bestTourLength = ants.get(0)
                    .trailLength(graph);
        }
        for (Ant a : ants) {
            if (a.trailLength(graph) < bestTourLength) {
                bestTourLength = a.trailLength(graph);
                bestTourOrder = a.getTrail().clone();
            }
        }
    }

    /**
     * Clear trails after simulation
     */
    private void clearTrails() {
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                trails[i][j] = c;
            }
        }
    }
    
}

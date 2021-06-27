package week_11_GA;

import java.util.Arrays;

/**
 *
 * @author zekikus
 */
// Each individual has fitness value and chromosome array
public class Individual {

    private int fitness; // Store the fitness value of this individual, for this problem fitness value store the number of 1's count
    private int[] chromosome; // Store the each gene of the chromosome

    public Individual(int length, boolean init) {
        chromosome = new int[length];
        if (init) {
            // Fill chrosome array with random values between 0-1
            initializeChromosome();
            // Evaluate this individual
            evaluate();
        }
    }

    // Initialize the genes of the chrosome, individual
    public void initializeChromosome() {
        for (int i = 0; i < chromosome.length; i++) {
            if (Math.random() >= 0.5) {
                chromosome[i] = 1;
            } else {
                chromosome[i] = 0;
            }
        }
    }

    // Calculate the fitness value of this chromosome, individual
    // In this problem, we calculate the number of 1's 
    public void evaluate() {
        for (int i = 0; i < chromosome.length; i++) {
            fitness += (chromosome[i] * Utils.values[i]);
        }
        
        fitness -= checkFeasibility();
    }

    public int checkFeasibility() {
        int usedCapacity = 0;

        // Total Weights of Selected Items Exceed the Knapsack Capacity??
        for (int i = 0; i < chromosome.length; i++) {
            usedCapacity += (Utils.weights[i] * chromosome[i]);
        }

        if (usedCapacity <= Utils.knapsackSize) // No, return 0 penalty
            return 0;
        
        return Utils.totalValue; // Yes, return constant penalty
                // We want to penalize this solution
    }

    @Override
    public String toString() {
        return Arrays.toString(chromosome) + " - Fitness:" + fitness;
    }

    public int getFitness() {
        return fitness;
    }

    public int[] getChromosome() {
        return chromosome;
    }
    
    
}

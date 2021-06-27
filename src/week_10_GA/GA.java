package week_10_GA;

import java.util.Random;

/**
 *
 * @author Abdussamet KACI
 */
public class GA {

    static final int POP_SIZE = 10; // Store the population size
    static final int MAX_GENERATION = 50; // Store the maximum generation count
    static final int CHROMOSOME_LENGTH = 11; // Store the chromosome length or number of gene count
    static final double MUTATION_RATE = 0.5;

    public static void main(String[] args) {

        // Create current population
        Population currentPopulation = new Population(POP_SIZE, CHROMOSOME_LENGTH);
        currentPopulation.initializePopulation(); // Generate Initial Population and Evaluate individuals in the current population
        currentPopulation.printPopulation();

        int generation = 0;

        // Repeat until the end of generations
        while (generation < MAX_GENERATION) {
            Population newPop = new Population(POP_SIZE, CHROMOSOME_LENGTH); // Create new population
            int counter = 0;
            while (counter < POP_SIZE) {
                Individual[] individuals = Utils.createMatingPool(currentPopulation.getIndividuals(), POP_SIZE - 1); // Cretae max pooling for selection
                // Selection
                // K-way Tournament selection operation
                Individual parent1 = selection(individuals, 5);
                Individual parent2 = selection(individuals, 5);
                // We may select same object, thus, I write this code for selection different parents
                while (parent1.equals(parent2)) {
                    parent2 = selection(individuals, 5);
                }
                // Crossover operation to the selected pairs
                Individual child = crossover(parent1, parent2);
                // Mutate this child
                mutate(child);
                child.evaluate();   // Evaluate this chil for calculating fitness

                // Set generated child to the new population
                newPop.setIndividuals(counter, child);

                counter++;
            }

            // Curret population is new popyulation
            currentPopulation = newPop;
            generation++;

            System.out.println("\nGENERATION " + generation);
            currentPopulation.printPopulation();
        }

    }

    public static Individual selection(Individual[] population, int k) {
        Individual best = null;
        // Create ramdom indexes uniquely for selection randomly
        int[] randIndexes = new Random().ints(0, population.length - 1).distinct().limit(population.length - 1).toArray();
        // Select k element
        for (int i = 0; i < k; i++) {
            Individual individual = population[randIndexes[i]];
            if (best == null || individual.getFitness() > best.getFitness()) {
                best = individual;
            }
        }
        return best;
    }

    public static Individual crossover(Individual parent1, Individual parent2) {
        Individual child = new Individual(parent1.getChromosome().length, false); // Cretae a individual
        for (int i = 0; i < child.getChromosome().length; i++) {
            // If random is grower than 0.5, get parent1 chromose
            // else get parent2 chromose
            if (Math.random() > 0.5) {
                child.setChromosome(i, parent1.getChromosome()[i]);
            } else {
                child.setChromosome(i, parent2.getChromosome()[i]);
            }
        }
        return child;
    }

    public static void mutate(Individual child) {
        for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
            if (Math.random() > MUTATION_RATE) {
                child.setChromosome(i, child.getChromosome()[i] ^ 1); // bitwise mutation
            }
        }
    }

}

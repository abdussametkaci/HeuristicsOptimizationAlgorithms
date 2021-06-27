package week_11_GA;


/**
 *
 * @author zekikus
 */
public class Population {

    private int popSize; // Store the population size
    private int chromosomeLength; // Store the length of the chrosome array
    private Individual[] individuals; // Store the each individual, chromosome in this population

    public Population(int popSize, int chromosomeLength) {
        this.popSize = popSize;
        this.chromosomeLength = chromosomeLength;
        individuals = new Individual[popSize];
    }

    // Initialize the population. Create random individuals and put this individual to the individuals array
    public void initializePopulation() {
        for (int i = 0; i < popSize; i++) {
            individuals[i] = new Individual(chromosomeLength, true);
        }
    }
    
    // Print each individual's chromosomes
    public void printPopulation(){
        for (int i = 0; i < individuals.length; i++) {
            System.out.println("Chrosome #" + i + ": " + individuals[i]);
        }
    }

    public Individual[] getIndividuals() {
        return individuals;
    }
    
    

}

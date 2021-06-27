


// Class for common functions (Read file, calculate distance etc.)

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Utils {
    
    static Random rnd = new Random(TSP.seed);
    // We need to write a function that will read the file.
    // We will read the given file and store data in City List.
    public static List<City> readFile(String fileName) throws FileNotFoundException{
        Scanner reader = new Scanner(new File(fileName));
        
        int nbrLine = 0;
        int solutionNumber = 0;
        List<City> cityList = new ArrayList<>();
        
        // The loop will run until the lines in the file are finished.
        while (reader.hasNext()) {
            String nextLine = reader.nextLine(); // Get Next Line
            
            if(nbrLine == 0){ 
                nbrLine++;
                continue;
            } // Skip First Row
            
            // Split Line
            String[] coordinates = nextLine.replace("\n", "").split(" ");
            float xCoord = Float.parseFloat(coordinates[0]); // Get X Coordinate
            float yCoord = Float.parseFloat(coordinates[1]); // Get Y Coordinate
            
            // Create the city based on the information read from the file.
            cityList.add(new City("" + solutionNumber++, xCoord, yCoord)); // Add city
        }
        
        return cityList; // Return read cities from given tsp file
    }
    
    public static float euclideanDistance(City cityOne, City cityTwo){
        // Calculate Euclidean Distance
        float xDistance = (float) Math.pow(cityOne.getX_coord() - cityTwo.getX_coord(), 2);
        float yDistance = (float) Math.pow(cityOne.getY_coord() - cityTwo.getY_coord(), 2);
        return (float) Math.sqrt(xDistance + yDistance);
    }
    
    public static Solution constructGreedyRandomizedSolution(List<City> cities, int startPoint, int endPoint){
        
        int k = 3;
        
        List<City> solution = new ArrayList<>();
        solution.add(cities.get(startPoint)); // Greedy Randomized Solution
        
        List<City> nonVisitedCities = new ArrayList<>(cities); // Store Non-visited Cities
        // Remove start and end points from the list
        nonVisitedCities.remove(cities.get(endPoint));
        nonVisitedCities.remove(cities.get(startPoint));
        
        int numberOfCities = cities.size();
        while (solution.size() < numberOfCities - 1) {            
            Map<Double, City> distances = new TreeMap<>();
            for (City nonVisitedCity : nonVisitedCities) {
                City lastCity = solution.get(solution.size() - 1);
                // Calculate the distance between added last city in the solution and nonVisitedCity
                double distance = Utils.euclideanDistance(lastCity, nonVisitedCity);
                distances.put(distance, nonVisitedCity);
            }
            
            // Store Restricted Candidate List
            ArrayList<City> rcl = new ArrayList<>();
            for (Map.Entry<Double, City> entry : distances.entrySet()) {
                City value = entry.getValue();
                rcl.add(value);
                if(rcl.size() == k)
                    break;
            }
            
            // Shuffle list
            Collections.shuffle(rcl, rnd); 
            // Select random solution in rcl
            City selectedCity = rcl.get(0); 
            
            // Remove selected city from non_visited_cities list
            nonVisitedCities.remove(selectedCity);
            // Add Selected city to candidate solution
            solution.add(selectedCity);
        }
        
        solution.add(cities.get(endPoint));
        
        return new Solution(solution);
    }
    
}

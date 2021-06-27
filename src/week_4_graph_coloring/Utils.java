
package week_4_graph_coloring;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {
    
    public static int[][] readFile(String fileName) throws FileNotFoundException{
        
        Scanner reader = new Scanner(new File(fileName));
        
        // Create adjacencyMatrix
        int[][] adjacencyMatrix = null;
        
        int numberLine = 0;
        while (reader.hasNext()) {
            String nextLine = reader.nextLine(); // Read Next Line
            
            if(numberLine == 0){
                // Read Number of nodes from data file
                String[] line = nextLine.replace("\n","").split(" ");
                int numberNodes = Integer.parseInt(line[0]); // Get number of nodes
                adjacencyMatrix = new int[numberNodes][numberNodes]; // Fill AdjacencyMatrix with zeros
                numberLine++;
                continue;
            }
            
            // Split Line
            String[] line = nextLine.replace("\n", "").split(" ");
            int fromNode = Integer.parseInt(line[0]); // Get From Node
            int toNode = Integer.parseInt(line[1]); // Get To Node
            
            // Connect nodes with each other
            // Fill Adjacency Matrix with Neighborhoods
            adjacencyMatrix[fromNode][toNode] = 1;
            adjacencyMatrix[toNode][fromNode] = 1;
        }
        
        return adjacencyMatrix;
        
    }
    
}

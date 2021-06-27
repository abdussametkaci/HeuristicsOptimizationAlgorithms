/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week_14_ACO;

import java.io.FileNotFoundException;

/**
 *
 * @author zekikus
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Utils.readFile("src/data/tsp/gr17_d.txt"); // Min:2085
        AntColonyOptimization aco = new AntColonyOptimization(Utils.numberOfCities);
        aco.startACO();
    }
}

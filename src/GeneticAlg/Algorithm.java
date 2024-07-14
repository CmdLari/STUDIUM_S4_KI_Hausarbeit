package GeneticAlg;

import GraphMaker.LGraph;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    double selectionRate;
    LGraph graph;
    int populationSize;
    int generationCount;
    int generationNr;
    List<Ant> population;

    public Algorithm(double selectionRate, LGraph graph, int populationSize, int generationCount) {
        this.selectionRate = selectionRate;
        this.graph = graph;
        this.populationSize = populationSize;
        this.generationCount = generationCount;
        this.generationNr = 1;
        this.population = new ArrayList<Ant>();
        generateInitialPopulation();
    }

    /////// PUBLIC ////////


    ///////// PRIVATE ///////
    

    private void generateInitialPopulation() {
        for (int i = 0; i < populationSize; i++) {
            Ant newAnt = new Ant(graph, i);
            population.add(newAnt);
        }
    }

}
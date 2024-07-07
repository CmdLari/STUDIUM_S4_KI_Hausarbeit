package GeneticAlg;

import GraphMaker.LGraph;

import java.util.Arrays;
import java.util.Comparator;

public class Algorithm {
    double selectionRate;
    LGraph graph;
    int populationSize;
    int generationCount;
    Ant[] population;


    ////// NEEDS //////
    //  ------ Population of ants
    //  ------ Ability to select parents
    //  ------ Ability to produce children


    public Algorithm(double selectionRate, LGraph graph, int populationSize, int generationCount) {
        this.selectionRate = selectionRate;
        this.graph = graph;
        this.populationSize = populationSize;
        this.generationCount = generationCount;
        this.population = new Ant[populationSize];
    }

    ///////// PUBLIC ////////


    ///////// PRIVATE ///////

    private void generateInitialPopulation() {
        for (int i = 0; i < populationSize; i++) {
            Ant newAnt = new Ant(graph, i);
            population[i] = newAnt;
        }
    }

    private void generateNextGopulation() {
        int numberOfParents = (int) (populationSize * selectionRate);
        Ant[] parents = selectParents();
        Ant[] newGeneration = new Ant[populationSize];
        for (int i = 0; i < populationSize; i++) {

        }

    }

    private Ant[] selectParents() {
        Ant[] livingChildren = new Ant[populationSize];
        int counter;
        for (counter = 0; counter < populationSize; counter++) {
            if (population[counter].isAlive()) {
                livingChildren[counter] = population[counter];
            }
        }
        Ant[] sortedChildren = new Ant[counter];
        for (int i = 0; i < counter; i++) {
            sortedChildren[i] = livingChildren[i];
        }
        sortedChildren = Arrays.stream(sortedChildren)
                .sorted(Comparator.comparingInt(Ant::getAccCost))
                .toArray(Ant[]::new);
        return sortedChildren;
    }
}

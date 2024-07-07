package GeneticAlg;

import GraphMaker.LGraph;

import java.util.Arrays;
import java.util.Comparator;

public class Algorithm {
    double selectionRate;
    LGraph graph;
    int populationSize;
    int generationCount;
    int generationNr;
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
        this.generationNr = 0;
        this.population = new Ant[populationSize];
        generateInitialPopulation();
    }

    ///////// PUBLIC ////////

    public Ant procreate(){
        for (int i = 0; i < generationCount; i++) {
            System.out.println("Generation " + generationNr);
            thePopulationMarches();
            generateNextGopulation();
        }

        /// REFINE LATER
        for (int i = 0; i < populationSize; i++) {
            if(population[i].isQueen()){
                System.out.println("THE QUEEN RULES!");
                return population[i];
            }
        }
        return null;
    }


    ///////// PRIVATE ///////

    private void thePopulationMarches(){
        for(int i = 0; i < populationSize; i++){
            population[i].randomStep();
        }
    }

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
        int parentIndex = 0;
        for (int i = 0; i < populationSize; i++) {
            Ant child = new Ant(parents[parentIndex], generationNr);
            newGeneration[i] = parents[parentIndex];
            parentIndex++;
            if (parentIndex == numberOfParents) {
                parentIndex = 0;
            }
        }
        generationNr++;
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

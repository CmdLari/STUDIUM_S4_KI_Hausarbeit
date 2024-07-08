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
        this.generationNr = 1;
        this.population = new Ant[populationSize];
        generateInitialPopulation();
    }

    ///////// PUBLIC ////////

    public Ant procreate(){
        for (int i = 0; i < generationCount; i++) {
            System.out.println("\n          :::::GENERATION " + generationNr);
            thePopulationMarches();
            generateNextPopulation();
        }

        int queenCount = 0;
        Ant[] queensTemp = new Ant[populationSize];
        for (int i = 0; i < populationSize; i++) {
            if(population[i].isQueen()){
                queensTemp[i] = population[i];
                queenCount++;
            }
        }
        Ant[] queens = new Ant[queenCount];
        System.arraycopy(queensTemp, 0, queens, 0, queenCount);
        queens = Arrays.stream(queensTemp)
                .sorted(Comparator.comparingInt(Ant::getAccCost))
                .toArray(Ant[]::new);
        return queens[0];
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

    private void generateNextPopulation() {
        generationNr++;
        int numberOfParents = (int) (populationSize * selectionRate);
        Ant[] parents = selectParents();
        Ant[] newGeneration = new Ant[populationSize];
        int parentIndex = 0;
        for (int i = 0; i < populationSize; i++) {
            if (parents[parentIndex].getClass()== Ant.class) {
                Ant child = new Ant(parents[parentIndex], generationNr);
                newGeneration[i] = child;
                parentIndex++;
                if (parentIndex == numberOfParents) {
                    parentIndex = 0;
                }
            }
        }
        population = newGeneration;
    }

    private Ant[] selectParents() {
        Ant[] livingAnts = new Ant[populationSize];
        int counter;
        for (counter = 0; counter < populationSize; counter++) {
            if (population[counter].isAlive()) {
                livingAnts[counter] = population[counter];
            }
        }
        Ant[] sortedAnts = new Ant[counter];
        for (int i = 0; i < counter; i++) {
            sortedAnts[i] = livingAnts[i];
        }
        sortedAnts = Arrays.stream(sortedAnts)
                .sorted(Comparator.comparingInt(Ant::getAccCost))
                .toArray(Ant[]::new);
        return sortedAnts;
    }
}

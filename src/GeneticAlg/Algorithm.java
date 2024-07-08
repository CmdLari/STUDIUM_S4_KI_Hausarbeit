package GeneticAlg;

import GraphMaker.LGraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Algorithm {
    double selectionRate;
    LGraph graph;
    int populationSize;
    int generationCount;
    int generationNr;
    Ant[] population;
    List<Ant> queens;

    // INITIAL POPULATION: SET AT RANDOM
    // // POPULATION WALKS UNTIL ALL HAVE COMPLETED THE ROUTE OR ARE DEAD
    // FOLLOWING GENERATIONS: SELECT REPRODUCING UNITS FITNESS BASED (COST/DISTANCE IN THIS CASE)
    // // SELECT FROM COMPLETED ONES
    // FACTORS TO INHERIT:
    // //

    public Algorithm(double selectionRate, LGraph graph, int populationSize, int generationCount) {
        this.selectionRate = selectionRate;
        this.graph = graph;
        this.populationSize = populationSize;
        this.generationCount = generationCount;
        this.generationNr = 1;
        this.population = new Ant[populationSize];
        this.queens = new ArrayList<>();
        generateInitialPopulation();
        letThemMarch();
        if (generationNr <= generationCount){
            genAlg();
        }
    }

    /////// PUBLIC ////////

    public List<Ant> getQueens() {
        return queens;
    }


    ///////// PRIVATE ///////

    private void genAlg(){
            generateNextPopulation();
        for (Ant ant : population){
            if(ant.isQueen()){
                queens.add(ant);
            }
        }
        generationNr++;
    }

    private void generateInitialPopulation() {
        for (int i = 0; i < populationSize; i++) {
            Ant newAnt = new Ant(graph, i);
            population[i] = newAnt;
        }
    }

    private void letThemMarch() {
        for (Ant ant : population){
            for (int i = 0; i <= graph.nodes.length; i++) {
                ant.randomStep();
            }
        }
        for (Ant ant : population){
            if(ant.isQueen()){
                queens.add(ant);
            }
        }

    }

    private void generateNextPopulation() {
        Ant[] parents = selectParents();

        int numberOfParents = Math.min((int) (populationSize * selectionRate), populationSize);
        if (parents.length == 0) {
            System.out.println("          No parents were selected. The path is either complete, or all ants have died.");
            return;
        }

        Ant[] newPopulation = new Ant[populationSize];
        int parentCounter = 0;

        for (int i = 0; i < populationSize; i++) {
            newPopulation[i] = new Ant(parents[parentCounter], parents[parentCounter+1], generationNr);
            parentCounter++;
            if (parentCounter == parents.length-2) {
                parentCounter = 0;
            }
        }
        this.population = newPopulation;
    }


    private Ant[] selectParents() {
        queens = queens.stream().sorted(Comparator.comparingInt(Ant::getAccCost)).toList();
        Ant[] parents = new Ant[queens.size()];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = queens.get(i);
        }
        return parents;
    }

}
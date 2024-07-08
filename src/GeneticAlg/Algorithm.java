package GeneticAlg;

import GraphMaker.LGraph;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Algorithm(double selectionRate, LGraph graph, int populationSize, int generationCount) {
        this.selectionRate = selectionRate;
        this.graph = graph;
        this.populationSize = populationSize;
        this.generationCount = generationCount;
        this.generationNr = 1;
        this.population = new Ant[populationSize];
        this.queens = new ArrayList<>();
        generateInitialPopulation();
    }

    /////// PUBLIC ////////

    public Ant procreate() {
        for (int i = 0; i < generationCount; i++) {
            System.out.println("\n          :::::GENERATION " + generationNr);
            thePopulationMarches();
            for (int j = 0; j < populationSize; j++) {
                if (this.population[j].isQueen()) {
                    updateQueens(this.population[j]);
                }
            }
            generateNextPopulation();
        }

        queens = queens.stream().sorted(Comparator.comparingInt(Ant::getAccCost)).toList();

        if (!queens.isEmpty()) {
            System.out.println("          The competing queens: ");
            for (Ant queen : queens) {
                System.out.print("          Queen: "+queen.getid()+", Cost: "+queen.getAccCost());
            }
            return queens.get(0);
        } else {
            return null;
        }
    }

    public void updateQueens(Ant queen) {
        this.queens.add(queen);
    }

    ///////// PRIVATE ///////

    private void thePopulationMarches() {
        for (int i = 0; i < populationSize; i++) {
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
        int numberOfParents = Math.min((int) (populationSize * selectionRate), population.length);
        Ant[] parents = selectParents();
        if (parents.length == 0) {
            System.out.println("          No parents were selected. The path is either complete, or all ants have died.");
            return;
        }

        numberOfParents = Math.min(numberOfParents, parents.length);

        Ant[] newGeneration = new Ant[populationSize];

        for (int i = 0; i < populationSize; i++) {
            if (parents[i % numberOfParents] != null) {
                Ant child = new Ant(parents[i % numberOfParents], generationNr+i);
                newGeneration[i] = child;
            }
        }
        population = newGeneration;
    }

    private Ant[] selectParents() {
        Ant[] livingAnts = Arrays.stream(population)
                .filter(ant -> ant != null && ant.isAlive() && !ant.isQueen())
                .toArray(Ant[]::new);

        return Arrays.stream(livingAnts)
                .sorted(Comparator.comparingInt(Ant::getAccCost))
                .toArray(Ant[]::new);
    }
}
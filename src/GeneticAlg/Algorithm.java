package GeneticAlg;

import GraphMaker.LGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Algorithm {
    double selectionRate;
    LGraph graph;
    int populationSize;
    int generationCount;
    int generationNr;
    List<Ant> population;
    List<Ant> queens;
    Ant queen;

    public Algorithm(double selectionRate, LGraph graph, int populationSize, int generationCount) {
        this.selectionRate = selectionRate;
        this.graph = graph;
        this.populationSize = populationSize;
        this.generationCount = generationCount;
        this.generationNr = 1;
        this.population = new ArrayList<Ant>();
        this.queens = new ArrayList<>();
        System.out.println("          ______________________________________________");
        generateInitialPopulation();
        System.out.println("          ______________________________________________");
        runAlg();
        crownQueen();
    }

    /////// PUBLIC ////////

    public Ant getWinner(){
        return queen;
    }


    ///////// PRIVATE ///////

    private void runAlg(){
        if (generationCount>1) {
            for (generationNr = 2; generationNr <= generationCount; generationNr++) {
                generateNextPopulation(generateParents());
                processResults();
            }
        }
    }

    private void generateInitialPopulation() {
        for (int i = 0; i < populationSize; i++) {
            Ant newAnt = new Ant(graph, i);
            population.add(newAnt);
        }
        processResults();
    }

    private List<Ant> generateParents(){
        int parentNumber = (int) (selectionRate*populationSize);
        List<Ant> potentialParents = population.stream().sorted(Comparator.comparing(Ant::getAccCost)).toList();
        List<Ant> parents = new ArrayList<>();
        for (int i = 0; i < parentNumber; i++) {
            parents.add(potentialParents.get(i));
        }
        Collections.shuffle(parents);
        return parents;
    };

    private void generateNextPopulation(List<Ant> parents){
        List<Ant> newGeneration = new ArrayList<>();
        int parentCounter = 0;
        for (int i = 0; i < populationSize; i++) {
            if (parentCounter>=parents.size()-1){
                newGeneration.add(new Ant(graph, parents.get(parentCounter),parents.get(0), generationNr));
                parentCounter=0;

            }
            else {
                newGeneration.add(new Ant(graph, parents.get(parentCounter),parents.get(parentCounter+1), generationNr));
                parentCounter++;
            }
        }
        this.population = newGeneration;
    }

    private void processResults(){
        Ant cheapestAnt = population.stream().sorted(Comparator.comparing(Ant::getAccCost)).toList().get(0);
        queens.add(cheapestAnt);
        int cheapestPath = cheapestAnt.getAccCost();
        int accCost = 0;
        for (Ant ant : population){
            accCost += ant.getAccCost();
        }
        System.out.println("          ~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~");
        System.out.println("          GENERATION NR: "+generationNr);
//        System.out.println("          Population CheckUp: "+population.size());
        System.out.println("          Cheapest Path Found: "+cheapestPath+" ("+cheapestAnt.toString()+")");
        System.out.println("          Accumulated Costs of this Generation: "+accCost);

    }
    private void crownQueen(){
        this.queen = queens.stream().sorted(Comparator.comparing(Ant::getAccCost)).toList().get(0);
        System.out.println("          ~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~");
        System.out.println("          The Cheapest Route Found: "+queen.getAccCost()+" "+queen.toString());
        System.out.println("          ~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~");
    }
}
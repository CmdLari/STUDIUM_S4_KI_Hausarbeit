package GeneticAlg_noTerminal;

import GeneticAlg_withTerminal.Ant;
import GraphMaker.LGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// V1 -> parents are selection rate X populationsize from the sorted population (sorted by cost/distance)
// V2 -> We save the best individual of each generation to avoid losing potentially better routes along the way
// V3 -> former winners are included in the parents


public class Algorithm_v1 {
    double selectionRate;
    LGraph graph;
    int populationSize;
    int generationCount;
    int generationNr;
    List<Ant> population;
    public Ant queen;
    int accCost = 0;
    List<Integer> genCost = new ArrayList<>();
    List <Double> avgGenCostRatio = new ArrayList<>();
    public Double genCostRatio = 0.0;
    List<Integer> bestCost = new ArrayList<>();
    List<Double> avgbestCostRatio = new ArrayList<>();
    public Double bestCostRatio = 0.0;
    Ant bestAnt;
    public double bestAntRatio = 0.0;


    public Algorithm_v1(double selectionRate, LGraph graph, int populationSize, int generationCount) {
        this.selectionRate = selectionRate;
        this.graph = graph;
        this.populationSize = populationSize;
        this.generationCount = generationCount;
        this.generationNr = 1;
        this.population = new ArrayList<>();
//        System.out.println("          ______________________________________________");
        generateInitialPopulation();
//        System.out.println("          ______________________________________________");
        runAlg();
        crownQueen();
        genCostRatio = avgGenCostRatio.stream().mapToDouble(Double::doubleValue).sum()/generationCount;
        bestCostRatio = avgbestCostRatio.stream().mapToDouble(Double::doubleValue).sum()/generationCount;
        bestAntRatio = (double) population.stream().sorted(Comparator.comparing(Ant::getAccCost)).toList().get(0).getAccCost()/bestAnt.getAccCost();
    }

    /////// PUBLIC ////////


    ///////// PRIVATE ///////

    private void runAlg(){
        if (generationCount>1) {
            for (generationNr = 2; generationNr <= generationCount; generationNr++) {
                generateNextPopulation(generateParents());
                processResults();
                genCost.add(accCost);
                avgGenCostRatio.add(Double.valueOf(genCost.get(generationNr-1))/Double.valueOf(genCost.get(generationNr-2)));
                bestCost.add(population.stream().sorted(Comparator.comparing(Ant::getAccCost)).toList().get(0).getAccCost());
                avgbestCostRatio.add((double) (bestCost.get(generationNr - 1) / bestCost.get(generationNr - 2)));
            }
        }
    }

    private void generateInitialPopulation() {
        for (int i = 0; i < populationSize; i++) {
            Ant newAnt = new Ant(graph, i);
            population.add(newAnt);
        }
        processResults();
        genCost.add(accCost);
        bestCost.add(population.stream().sorted(Comparator.comparing(Ant::getAccCost)).toList().get(0).getAccCost());
        bestAnt = population.stream().sorted(Comparator.comparing(Ant::getAccCost)).toList().get(0);
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
        int cheapestPath = cheapestAnt.getAccCost();
        accCost = 0;
        for (Ant ant : population){
            accCost += ant.getAccCost();
        }
//        System.out.println("          ~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~");
//        System.out.println("          GENERATION NR: "+generationNr);
//        System.out.println("          Population CheckUp: "+population.size());
//        System.out.println("          Cheapest Path Found: "+cheapestPath+" ("+cheapestAnt.toString()+")");
//        System.out.println("          Accumulated Costs of this Generation: "+accCost);

    }
    private void crownQueen(){
        this.queen = population.stream().sorted(Comparator.comparing(Ant::getAccCost)).toList().get(0);
//        System.out.println("          ~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~");
//        System.out.println("          The Cheapest Route Found: "+queen.getAccCost()+" "+queen.toString());
//        System.out.println("          ~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~+*+~");
    }
}
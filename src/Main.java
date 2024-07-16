import GeneticAlg_withTerminal.Ant;
import GraphMaker.LGraph;
import Visualisation.GraphVisualizer;
import org.graphstream.graph.Graph;

/**
 * Client to run the algorithm.
 * NODENUMMBER: Number of nodes the graph has and that must be included in the route
 * MAXEDGECOST: Maximum cost an edge can have - relevant only to have different costs(distances)
 * POPULATIONSIZE: Number of individuals traversing the path. Same for each generation
 * GENERATIONCOUNT: Number of generations. Each generation (>1) consists of the last ones best individuals and a mutation thereof.
 */
public class Main {
    public static void main(String[] args) {


        int MAXEDGECOST = 10;
        double SELECTIONRATE = 0.5;


        /////////////////////Client for metrics////////////////

        // Metrics:
        // Runtime | AccuCost Changes in between generations | Best Route in between generations | Best Route compared between Versions for large numbers
        // INPUT FOR TRIALS: Small: NodeNr: 10, Population: 10, Generations: 10 || Medium: NodeNr: 100, Population: 50 || Generations: 100 ||  || Large: NodeNr: 20000, Population: 200 || Generations: 200
        // Nr of trials for each: 100


        int NODENUMBER_small = 10;
        int POPULATIONSIZE_small = 10;
        int GENERATIONCOUNT_small = 10;
        LGraph lGraphSmall = new LGraph(NODENUMBER_small, MAXEDGECOST);

        int NODENUMBER_medium = 20;
        int POPULATIONSIZE_medium = 50;
        int GENERATIONCOUNT_medium = 100;
        LGraph lGraphMedium = new LGraph(NODENUMBER_medium, MAXEDGECOST);

        int NODENUMBER_large = 50;
        int POPULATIONSIZE_large = 100;
        int GENERATIONCOUNT_large = 200;
        LGraph lGraphLarge = new LGraph(NODENUMBER_large, MAXEDGECOST);


        int trialNr = 100;

        ////// V1
        // Small
        System.out.println("          __________VERSION 1___________");
        System.out.println("          ____________SMALL_____________");

        long startTimev1_1 = System.currentTimeMillis();
        double costRatio_v1s = 0.0;
        double bestRatio_v1s = 0.0;

        for (int i = 0; i < trialNr; i++) {
            GeneticAlg_noTerminal.Algorithm_v1 alg1Small = new GeneticAlg_noTerminal.Algorithm_v1(SELECTIONRATE, lGraphSmall, POPULATIONSIZE_small, GENERATIONCOUNT_small);
            costRatio_v1s += alg1Small.genCostRatio;
            bestRatio_v1s += alg1Small.bestCostRatio;
        }
        long endTimev1_1 = System.currentTimeMillis();

        System.out.println("          V1 had an average runtime of " + (endTimev1_1 - startTimev1_1)/100 + " ms");
        System.out.println("          V1 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v1s/trialNr));
        System.out.println("          V1 had an average improvement of the shortest route of each generation of " + (bestRatio_v1s/trialNr));
        System.out.println("\n");

        System.out.println("          ____________MEDIUM____________");

        long startTimev1_2 = System.currentTimeMillis();
        double costRatio_v1m = 0.0;
        double bestRatio_v1m = 0.0;

        for (int i = 0; i < trialNr; i++) {
            GeneticAlg_noTerminal.Algorithm_v1 alg1Medium = new GeneticAlg_noTerminal.Algorithm_v1(SELECTIONRATE, lGraphMedium, POPULATIONSIZE_medium, GENERATIONCOUNT_medium);
            costRatio_v1m += alg1Medium.genCostRatio;
            bestRatio_v1m += alg1Medium.bestCostRatio;
        }
        long endTimev1_2 = System.currentTimeMillis();

        System.out.println("          V1 had an average runtime of " + (endTimev1_2 - startTimev1_2)/100 + " ms");
        System.out.println("          V1 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v1m/trialNr));
        System.out.println("          V1 had an average improvement of the shortest route of each generation of " + (bestRatio_v1m/trialNr));
        System.out.println("\n");

        System.out.println("          ____________LARGE____________");

        long startTimev1_3 = System.currentTimeMillis();
        double costRatio_v1l = 0.0;
        double bestRatio_v1l = 0.0;

        for (int i = 0; i < trialNr; i++) {
            GeneticAlg_noTerminal.Algorithm_v1 alg1Large = new GeneticAlg_noTerminal.Algorithm_v1(SELECTIONRATE, lGraphLarge, POPULATIONSIZE_large, GENERATIONCOUNT_large);
            costRatio_v1l += alg1Large.genCostRatio;
            bestRatio_v1l += alg1Large.bestCostRatio;
        }
        long endTimev1_3 = System.currentTimeMillis();

        System.out.println("          V1 had an average runtime of " + (endTimev1_3 - startTimev1_3)/100 + " ms");
        System.out.println("          V1 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v1l/trialNr));
        System.out.println("          V1 had an average improvement of the shortest route of each generation of " + (bestRatio_v1l/trialNr));
        System.out.println("\n");



        /////////////////////Client for visuals////////////////

//        int NODENUMBER = 10;
//        int POPULATIONSIZE = 20;
//        int GENERATIONCOUNT = 200;


        // Initialize Graph
//        LGraph lGraph = new LGraph(NODENUMBER, MAXEDGECOST);

        // Initialize Algorithm_v2
//        Algorithm_v3 algorithm = new Algorithm_v3(SELECTIONRATE, lGraph, POPULATIONSIZE, GENERATIONCOUNT);

        // Visualize the graph, tried routes and shortest route
//        visualizeGraph(lGraph, algorithm.getWinner());

    }

    /**
     * Method to hand the graph and result over to the GraphStream API using the GraphVisualizer class
     * @param lGraph the generated graph that was traversed
     * @param ant the individual resulting in the lowest cost/distance (Result of the algorithm)
     */
    private static void visualizeGraph(LGraph lGraph, Ant ant) {
        Graph graph = GraphVisualizer.visualize(lGraph, ant);
        GraphVisualizer.displayGraph(graph);
    }
}

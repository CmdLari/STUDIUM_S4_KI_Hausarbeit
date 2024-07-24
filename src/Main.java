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


        int MAXEDGECOST = 50;
        double SELECTIONRATE = 0.5;


        /////////////////////Client for metrics////////////////

        // Metrics:
        // Runtime | AccuCost Changes in between generations | Best Route in between generations | Best Route compared between Versions for large numbers
        // INPUT FOR TRIALS: Small: NodeNr: 10, Population: 10, Generations: 10 || Medium: NodeNr: 100, Population: 50 || Generations: 100 ||  || Large: NodeNr: 20000, Population: 200 || Generations: 200
        // Nr of trials for each: 100


//        int NODENUMBER_small = 10;
//        int POPULATIONSIZE_small = 10;
//        int GENERATIONCOUNT_small = 10;
//        LGraph lGraphSmall = new LGraph(NODENUMBER_small, MAXEDGECOST);
//
//        int NODENUMBER_medium = 20;
//        int POPULATIONSIZE_medium = 20;
//        int GENERATIONCOUNT_medium = 20;
//        LGraph lGraphMedium = new LGraph(NODENUMBER_medium, MAXEDGECOST);
//
//        int NODENUMBER_large = 40;
//        int POPULATIONSIZE_large = 40;
//        int GENERATIONCOUNT_large = 40;
//        LGraph lGraphLarge = new LGraph(NODENUMBER_large, MAXEDGECOST);
//
//
//        int TRIALNUMBER = 200;
//
//        System.out.println("          METRICS:");
//        System.out.println("          TRIAL NUMBER: "+TRIALNUMBER);
//        System.out.println("          SMALL:  Nodes: "+NODENUMBER_small+" Population: "+POPULATIONSIZE_small+" Generations: "+GENERATIONCOUNT_small);
//        System.out.println("          MEDIUM: Nodes: "+NODENUMBER_medium+" Population: "+POPULATIONSIZE_medium+" Generations: "+GENERATIONCOUNT_medium);
//        System.out.println("          LARGE:  Nodes: "+NODENUMBER_large+" Population: "+POPULATIONSIZE_large+" Generations: "+GENERATIONCOUNT_large);
//        System.out.println("          ______________________________");
//        System.out.println("\n");
//
//        ////// V1
//        // Small
//        System.out.println("          __________VERSION 1___________");
//        System.out.println("          ____________SMALL_____________");
//
//        long startTimev1_1 = System.currentTimeMillis();
//        double costRatio_v1s = 0.0;
//        double bestRatio_v1s = 0.0;
//        double bestAntRatio_v1s = 0.0;
//        double bestRoute_v1s = 0;
//
//        for (int i = 0; i < TRIALNUMBER; i++) {
//            GeneticAlg_noTerminal.Algorithm_v1 alg1Small = new GeneticAlg_noTerminal.Algorithm_v1(SELECTIONRATE, lGraphSmall, POPULATIONSIZE_small, GENERATIONCOUNT_small);
//            costRatio_v1s += alg1Small.genCostRatio;
//            bestRatio_v1s += alg1Small.bestCostRatio;
//            bestAntRatio_v1s+=alg1Small.bestAntRatio;
//            bestRoute_v1s+=alg1Small.queen.getAccCost();
//        }
//        long endTimev1_1 = System.currentTimeMillis();
//        bestRoute_v1s = bestRoute_v1s/TRIALNUMBER;
//
//        System.out.println("          V1 had an average runtime of " + (endTimev1_1 - startTimev1_1)/100 + " ms");
//        System.out.println("          V1 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v1s/TRIALNUMBER));
//        System.out.println("          V1 had an average improvement of the shortest route of each generation of " + (bestRatio_v1s/TRIALNUMBER));
//        System.out.println("          V1 had an average improvement of the best route of " + (bestAntRatio_v1s/TRIALNUMBER));
//        System.out.println("\n");
//
//        System.out.println("          ____________MEDIUM____________");
//
//        long startTimev1_2 = System.currentTimeMillis();
//        double costRatio_v1m = 0.0;
//        double bestRatio_v1m = 0.0;
//        double bestAntRatio_v1m = 0.0;
//        double bestRoute_v1m = 0;
//
//        for (int i = 0; i < TRIALNUMBER; i++) {
//            GeneticAlg_noTerminal.Algorithm_v1 alg1Medium = new GeneticAlg_noTerminal.Algorithm_v1(SELECTIONRATE, lGraphMedium, POPULATIONSIZE_medium, GENERATIONCOUNT_medium);
//            costRatio_v1m += alg1Medium.genCostRatio;
//            bestRatio_v1m += alg1Medium.bestCostRatio;
//            bestAntRatio_v1m+=alg1Medium.bestAntRatio;
//            bestRoute_v1m+=alg1Medium.queen.getAccCost();
//        }
//        long endTimev1_2 = System.currentTimeMillis();
//        bestRoute_v1m = bestRoute_v1m/TRIALNUMBER;
//
//        System.out.println("          V1 had an average runtime of " + (endTimev1_2 - startTimev1_2)/100 + " ms");
//        System.out.println("          V1 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v1m/TRIALNUMBER));
//        System.out.println("          V1 had an average improvement of the shortest route of each generation of " + (bestRatio_v1m/TRIALNUMBER));
//        System.out.println("          V1 had an average improvement of the best route of " + (bestAntRatio_v1m/TRIALNUMBER));
//        System.out.println("\n");
//
//        System.out.println("          ____________LARGE____________");
//
//        long startTimev1_3 = System.currentTimeMillis();
//        double costRatio_v1l = 0.0;
//        double bestRatio_v1l = 0.0;
//        double bestAntRatio_v1l = 0.0;
//        double bestRoute_v1l = 0;
//
//        for (int i = 0; i < TRIALNUMBER; i++) {
//            GeneticAlg_noTerminal.Algorithm_v1 alg1Large = new GeneticAlg_noTerminal.Algorithm_v1(SELECTIONRATE, lGraphLarge, POPULATIONSIZE_large, GENERATIONCOUNT_large);
//            costRatio_v1l += alg1Large.genCostRatio;
//            bestRatio_v1l += alg1Large.bestCostRatio;
//            bestAntRatio_v1l+=alg1Large.bestAntRatio;
//            bestRoute_v1l+=alg1Large.queen.getAccCost();
//        }
//        long endTimev1_3 = System.currentTimeMillis();
//        bestRoute_v1l = bestRoute_v1l/TRIALNUMBER;
//
//        System.out.println("          V1 had an average runtime of " + (endTimev1_3 - startTimev1_3)/100 + " ms");
//        System.out.println("          V1 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v1l/TRIALNUMBER));
//        System.out.println("          V1 had an average improvement of the shortest route of each generation of " + (bestRatio_v1l/TRIALNUMBER));
//        System.out.println("          V1 had an average improvement of the best route of " + (bestAntRatio_v1l/TRIALNUMBER));
//        System.out.println("\n");
//
//
//        ////// V2
//        // Small
//        System.out.println("          __________VERSION 2___________");
//        System.out.println("          ____________SMALL_____________");
//
//        long startTimev2_1 = System.currentTimeMillis();
//        double costRatio_v2s = 0.0;
//        double bestRatio_v2s = 0.0;
//        double bestAntRatio_v2s = 0.0;
//        double bestRoute_v2s = 0;
//
//        for (int i = 0; i < TRIALNUMBER; i++) {
//            GeneticAlg_noTerminal.Algorithm_v2 alg2Small = new GeneticAlg_noTerminal.Algorithm_v2(SELECTIONRATE, lGraphSmall, POPULATIONSIZE_small, GENERATIONCOUNT_small);
//            costRatio_v2s += alg2Small.genCostRatio;
//            bestRatio_v2s += alg2Small.bestCostRatio;
//            bestAntRatio_v2s+=alg2Small.bestAntRatio;
//            bestRoute_v2s+=alg2Small.queen.getAccCost();
//        }
//        long endTimev2_1 = System.currentTimeMillis();
//        bestRoute_v2s = bestRoute_v2s/TRIALNUMBER;
//
//        System.out.println("          V2 had an average runtime of " + (endTimev2_1 - startTimev2_1)/100 + " ms");
//        System.out.println("          V2 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v2s/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the shortest route of each generation of " + (bestRatio_v2s/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the best route of " + (bestAntRatio_v2s/TRIALNUMBER));
//        System.out.println("\n");
//
//        System.out.println("          ____________MEDIUM____________");
//
//        long startTimev2_2 = System.currentTimeMillis();
//        double costRatio_v2m = 0.0;
//        double bestRatio_v2m = 0.0;
//        double bestAntRatio_v2m = 0.0;
//        double bestRoute_v2m = 0;
//
//        for (int i = 0; i < TRIALNUMBER; i++) {
//            GeneticAlg_noTerminal.Algorithm_v2 alg2Medium = new GeneticAlg_noTerminal.Algorithm_v2(SELECTIONRATE, lGraphMedium, POPULATIONSIZE_medium, GENERATIONCOUNT_medium);
//            costRatio_v2m += alg2Medium.genCostRatio;
//            bestRatio_v2m += alg2Medium.bestCostRatio;
//            bestAntRatio_v2m+=alg2Medium.bestAntRatio;
//            bestRoute_v2m+=alg2Medium.queen.getAccCost();
//        }
//        long endTimev2_2 = System.currentTimeMillis();
//        bestRoute_v2m = bestRoute_v2m/TRIALNUMBER;
//
//        System.out.println("          V2 had an average runtime of " + (endTimev2_2 - startTimev2_2)/100 + " ms");
//        System.out.println("          V2 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v2m/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the shortest route of each generation of " + (bestRatio_v2m/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the best route of " + (bestAntRatio_v2m/TRIALNUMBER));
//        System.out.println("\n");
//
//        System.out.println("          ____________LARGE____________");
//
//        long startTimev2_3 = System.currentTimeMillis();
//        double costRatio_v2l = 0.0;
//        double bestRatio_v2l = 0.0;
//        double bestAntRatio_v2l = 0.0;
//        double bestRoute_v2l = 0;
//
//        for (int i = 0; i < TRIALNUMBER; i++) {
//            GeneticAlg_noTerminal.Algorithm_v2 alg2Large = new GeneticAlg_noTerminal.Algorithm_v2(SELECTIONRATE, lGraphLarge, POPULATIONSIZE_large, GENERATIONCOUNT_large);
//            costRatio_v2l += alg2Large.genCostRatio;
//            bestRatio_v2l += alg2Large.bestCostRatio;
//            bestAntRatio_v2l+=alg2Large.bestAntRatio;
//            bestRoute_v2l+=alg2Large.queen.getAccCost();
//        }
//        long endTimev2_3 = System.currentTimeMillis();
//        bestRoute_v2l = bestRoute_v2l/TRIALNUMBER;
//
//        System.out.println("          V2 had an average runtime of " + (endTimev2_3 - startTimev2_3)/100 + " ms");
//        System.out.println("          V2 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v2l/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the shortest route of each generation of " + (bestRatio_v2l/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the best route of " + (bestAntRatio_v2l/TRIALNUMBER));
//        System.out.println("\n");
//
//
//        ////// V3
//        // Small
//        System.out.println("          __________VERSION 3___________");
//        System.out.println("          ____________SMALL_____________");
//
//        long startTimev3_1 = System.currentTimeMillis();
//        double costRatio_v3s = 0.0;
//        double bestRatio_v3s = 0.0;
//        double bestAntRatio_v3s = 0.0;
//        double bestRoute_v3s = 0;
//
//        for (int i = 0; i < TRIALNUMBER; i++) {
//            GeneticAlg_noTerminal.Algorithm_v3 alg3Small = new GeneticAlg_noTerminal.Algorithm_v3(SELECTIONRATE, lGraphSmall, POPULATIONSIZE_small, GENERATIONCOUNT_small);
//            costRatio_v3s += alg3Small.genCostRatio;
//            bestRatio_v3s += alg3Small.bestCostRatio;
//            bestAntRatio_v3s+=alg3Small.bestAntRatio;
//            bestRoute_v3s+=alg3Small.queen.getAccCost();
//        }
//        long endTimev3_1 = System.currentTimeMillis();
//        bestRoute_v3s = bestRoute_v3s/TRIALNUMBER;
//
//        System.out.println("          V2 had an average runtime of " + (endTimev3_1 - startTimev3_1)/100 + " ms");
//        System.out.println("          V2 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v3s/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the shortest route of each generation of " + (bestRatio_v3s/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the best route of " + (bestAntRatio_v3s/TRIALNUMBER));
//        System.out.println("\n");
//
//        System.out.println("          ____________MEDIUM____________");
//
//        long startTimev3_2 = System.currentTimeMillis();
//        double costRatio_v3m = 0.0;
//        double bestRatio_v3m = 0.0;
//        double bestAntRatio_v3m = 0.0;
//        double bestRoute_v3m = 0;
//
//        for (int i = 0; i < TRIALNUMBER; i++) {
//            GeneticAlg_noTerminal.Algorithm_v3 alg3Medium = new GeneticAlg_noTerminal.Algorithm_v3(SELECTIONRATE, lGraphMedium, POPULATIONSIZE_medium, GENERATIONCOUNT_medium);
//            costRatio_v3m += alg3Medium.genCostRatio;
//            bestRatio_v3m += alg3Medium.bestCostRatio;
//            bestAntRatio_v3m+=alg3Medium.bestAntRatio;
//            bestRoute_v3m+=alg3Medium.queen.getAccCost();
//        }
//        long endTimev3_2 = System.currentTimeMillis();
//        bestRoute_v3m = bestRoute_v3m/TRIALNUMBER;
//
//        System.out.println("          V2 had an average runtime of " + (endTimev3_2 - startTimev3_2)/100 + " ms");
//        System.out.println("          V2 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v3m/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the shortest route of each generation of " + (bestRatio_v3m/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the best route of " + (bestAntRatio_v3m/TRIALNUMBER));
//        System.out.println("\n");
//
//        System.out.println("          ____________LARGE____________");
//
//        long startTimev3_3 = System.currentTimeMillis();
//        double costRatio_v3l = 0.0;
//        double bestRatio_v3l = 0.0;
//        double bestAntRatio_v3l = 0.0;
//        double bestRoute_v3l = 0;
//
//        for (int i = 0; i < TRIALNUMBER; i++) {
//            GeneticAlg_noTerminal.Algorithm_v3 alg3Large = new GeneticAlg_noTerminal.Algorithm_v3(SELECTIONRATE, lGraphLarge, POPULATIONSIZE_large, GENERATIONCOUNT_large);
//            costRatio_v3l += alg3Large.genCostRatio;
//            bestRatio_v3l += alg3Large.bestCostRatio;
//            bestAntRatio_v3l+=alg3Large.bestAntRatio;
//            bestRoute_v3l+=alg3Large.queen.getAccCost();
//        }
//        long endTimev3_3 = System.currentTimeMillis();
//        bestRoute_v3l = bestRoute_v3l/TRIALNUMBER;
//
//        System.out.println("          V2 had an average runtime of " + (endTimev3_3 - startTimev3_3)/100 + " ms");
//        System.out.println("          V2 had an average improvement of the overall cost of the generation's routes of " + (costRatio_v3l/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the shortest route of each generation of " + (bestRatio_v3l/TRIALNUMBER));
//        System.out.println("          V2 had an average improvement of the best route of " + (bestAntRatio_v3l/TRIALNUMBER));
//        System.out.println("\n");
//
//
//        System.out.println("          SMALL  :: V1 : V2: " + (bestRoute_v1s/bestRoute_v2s)+" | V1 : V3: " + (bestRoute_v1s/bestRoute_v3s)+" | V2 : V3: " + (bestRoute_v2s/bestRoute_v3s));
//        System.out.println("          MEDIUM :: V1 : V2: " + (bestRoute_v1m/bestRoute_v2m)+" | V1 : V3: " + (bestRoute_v1m/bestRoute_v3m)+" | V2 : V3: " + (bestRoute_v2m/bestRoute_v3m));
//        System.out.println("          LARGE  :: V1 : V2: " + (bestRoute_v1l/bestRoute_v2l)+" | V1 : V3: " + (bestRoute_v1l/bestRoute_v3l)+" | V2 : V3: " + (bestRoute_v2l/bestRoute_v3l));

        /////////////////////Client for visuals////////////////

        int NODENUMBER = 5;
        int POPULATIONSIZE = 20;
        int GENERATIONCOUNT = 1;


//      Initialize Graph
        LGraph lGraph = new LGraph(NODENUMBER, MAXEDGECOST);

//      Initialize Algorithm_v2
        GeneticAlg_withTerminal.Algorithm_v3 algorithm = new GeneticAlg_withTerminal.Algorithm_v3(SELECTIONRATE, lGraph, POPULATIONSIZE, GENERATIONCOUNT);

//      Visualize the graph, tried routes and shortest route
        visualizeGraph(lGraph, null);

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

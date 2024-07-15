import GeneticAlg.Algorithm;
import GeneticAlg.Ant;
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

        int NODENUMBER = 20;
        int MAXEDGECOST = 10;
        double SELECTIONRATE = 0.4;
        int POPULATIONSIZE = 200;
        int GENERATIONCOUNT = 200;

        // Initialize Graph
        LGraph lGraph = new LGraph(NODENUMBER, MAXEDGECOST);

        // Initialize Algorithm
        Algorithm algorithm = new Algorithm(SELECTIONRATE, lGraph, POPULATIONSIZE, GENERATIONCOUNT);

        // Visualize the graph, tried routes and shortest route
        visualizeGraph(lGraph, algorithm.getWinner());

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

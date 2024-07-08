import GeneticAlg.Algorithm;
import GeneticAlg.Ant;
import GraphMaker.LGraph;
import Visualisation.GraphVisualizer;
import org.graphstream.graph.Graph;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int MAXNODES = 10;
        int MAXEDGES = 40;
        int MAXEDGECOST = 10;

        LGraph lGraph = new LGraph(MAXNODES, MAXEDGES, MAXEDGECOST);

        Algorithm algorithm = new Algorithm(0.5, lGraph, 10, 20);

        Ant potentialQueen = algorithm.procreate();

        if (potentialQueen == null) {
            System.out.println("          No path was found!");
            visualizeGraph(lGraph, null);
        }
        else {
            potentialQueen.trimEdgesArray();
            System.out.println("\n          ~+*+~+*+~+*+~+*+~*~+*+~*~+*+~*~+*+~*+~~+*+~+*+~+*+~+*+~*~+*+~*~+*+~*~+*+~*+~");
            System.out.println("          The colony found a route! Cost: "+potentialQueen.getAccCost()+", "+ Arrays.toString(potentialQueen.getVisitedEdges()));
            visualizeGraph(lGraph, potentialQueen);
        }
    }

    private static void visualizeGraph(LGraph lGraph, Ant ant) {
        Graph graph = GraphVisualizer.visualize(lGraph, ant);
        GraphVisualizer.displayGraph(graph);
    }
}

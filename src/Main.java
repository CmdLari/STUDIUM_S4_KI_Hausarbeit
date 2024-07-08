import GeneticAlg.Algorithm;
import GeneticAlg.Ant;
import GraphMaker.LGraph;
import Visualisation.GraphVisualizer;
import org.graphstream.graph.Graph;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int MAXNODES = 10;
        int MAXEDGES = 20;
        int MAXEDGECOST = 10;

        LGraph lGraph = new LGraph(MAXNODES, MAXEDGES, MAXEDGECOST);

        Algorithm algorithm = new Algorithm(0.5, lGraph, 20, 1);

        if(!(algorithm.getQueens().isEmpty())) {
            Ant ant = algorithm.getQueens().getFirst();
            ant.trimEdgesArray();
            System.out.println("\n          ~+*+~+*+~+*+~+*+~*~+*+~*~+*+~*~+*+~*+~~+*+~+*+~+*+~+*+~*~+*+~*~+*+~*~+*+~*+~");
            System.out.println("          The colony found a route! Cost: "+ant.getAccCost()+", "+ Arrays.toString(ant.getVisitedEdges()));
            visualizeGraph(lGraph, ant);
        }
        else{
            System.out.println("          No path was found!");
            visualizeGraph(lGraph, null);
        }


    }

    private static void visualizeGraph(LGraph lGraph, Ant ant) {
        Graph graph = GraphVisualizer.visualize(lGraph, ant);
        GraphVisualizer.displayGraph(graph);
    }
}

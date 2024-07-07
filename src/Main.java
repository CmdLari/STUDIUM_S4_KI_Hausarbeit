import GeneticAlg.Ant;
import GraphMaker.LGraph;
import Visualisation.GraphVisualizer;
import org.graphstream.graph.Graph;

public class Main {
    public static void main(String[] args) {

        int MAXNODES = 4;
        int MAXEDGES = 50;
        int MAXEDGECOST = 5;

        LGraph lGraph = new LGraph(MAXNODES, MAXEDGES, MAXEDGECOST);

        Ant bob = new Ant(lGraph);

        for (int i = 0; i < 100; i++) {
            bob.cheapestStep();
        }
        System.out.println("hi");
        bob.cleanWinningRoute();
        visualizeGraph(lGraph, bob);
        System.out.println("bye");

    }

    private static void visualizeGraph(LGraph lGraph, Ant ant) {
        Graph graph = GraphVisualizer.visualize(lGraph, ant);
        GraphVisualizer.displayGraph(graph);
    }
}

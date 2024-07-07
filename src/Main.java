import GeneticAlg.Ant;
import GraphMaker.LGraph;
import Visualisation.GraphVisualizer;
import org.graphstream.graph.Graph;

public class Main {
    public static void main(String[] args) {

        int MAXNODES = 30;
        int MAXEDGES = 50;
        int MAXEDGECOST = 5;

        LGraph lGraph = new LGraph(MAXNODES, MAXEDGES, MAXEDGECOST);

        Ant bob = new Ant(lGraph, 1);

        for (int i = 0; i < 200; i++) {
            bob.cheapestStep();
        }

        bob.trimEdgesArray();
        visualizeGraph(lGraph, bob);

    }

    private static void visualizeGraph(LGraph lGraph, Ant ant) {
        Graph graph = GraphVisualizer.visualize(lGraph, ant);
        GraphVisualizer.displayGraph(graph);
    }
}

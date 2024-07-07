import GeneticAlg.Algorithm;
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

        Algorithm algorithm = new Algorithm(0.5, lGraph, 100, 10);

        Ant potentialQueen = algorithm.procreate();

        if (potentialQueen == null) {
            System.out.println("Sucks!");
            visualizeGraph(lGraph, potentialQueen);
        }
        else {
            potentialQueen.trimEdgesArray();
            visualizeGraph(lGraph, potentialQueen);
        }
    }

    private static void visualizeGraph(LGraph lGraph, Ant ant) {
        Graph graph = GraphVisualizer.visualize(lGraph, ant);
        GraphVisualizer.displayGraph(graph);
    }
}

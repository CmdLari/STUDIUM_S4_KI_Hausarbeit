import GeneticAlg.Algorithm;
import GeneticAlg.Ant;
import GraphMaker.LGraph;
import Visualisation.GraphVisualizer;
import org.graphstream.graph.Graph;


public class Main {
    public static void main(String[] args) {

        int NODENUMBER = 10;
        int MAXEDGECOST = 10;

        LGraph lGraph = new LGraph(NODENUMBER, MAXEDGECOST);

        Algorithm algorithm = new Algorithm(0.7, lGraph, 20, 20);
        visualizeGraph(lGraph, algorithm.getWinner());

    }

    private static void visualizeGraph(LGraph lGraph, Ant ant) {
        Graph graph = GraphVisualizer.visualize(lGraph, ant);
        GraphVisualizer.displayGraph(graph);
    }
}

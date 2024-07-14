package GeneticAlg;

import GraphMaker.LGraph;
import GraphMaker.LEdge;
import GraphMaker.LNode;

import java.util.*;

public class Ant {

    private LNode currentNode; // Location
    private int accCost; // Price of this ants route
    private final List<LEdge> visitedEdges; // Path this ant knows
    private final String id;

    public Ant(LGraph graph, int id) {
        Random rand = new Random();
        this.visitedEdges = new ArrayList<LEdge>();
        this.accCost = 0;
        this.id = "Ant " + id;

        // random path this ant takes
        List<LNode> visitedNodes = new ArrayList<LNode>(graph.nodes);
        Collections.shuffle(visitedNodes);
        for (int i = 1; i < visitedNodes.size(); i++) {
            LEdge edge = graph.findEdge(visitedNodes.get(i - 1), visitedNodes.get(i));
            edge.markAsTried();
            this.visitedEdges.add(edge);
            this.accCost += edge.getCost();
        }
        LEdge edge = graph.findEdge(visitedNodes.getFirst(), visitedNodes.getLast());
        edge.markAsTried();
        this.visitedEdges.add(edge);
        this.accCost += edge.getCost();
    }

    ///////// PUBLIC ////////

    public List<LEdge> getVisitedEdges() {
        return visitedEdges;
    }

    public int getAccCost(){
        return accCost;
    }

    public String getAntId(){
        return this.id;
    }

    ///////// PRIVATE //////

}

package GeneticAlg;
import GraphMaker.LGraph;
import GraphMaker.LEdge;
import GraphMaker.LNode;
public class Unit {

//    Needs to:
//    Be able to step in a direction
//
//    Has:
//    Cost Sum
//    Current Node / Location
//    List of past Edges
//
//    Check, if it has reached the destination

    LNode currentNode;
    LEdge[] passedEdges;
    int accCost = 0;
    LGraph graph;

    public Unit(LGraph graph, int accCost, LNode currentNode, LEdge[] passedEdges) {
        int initialLength = graph.edges.length;
        this.accCost = 0;
        this.currentNode = currentNode;
        this.passedEdges = new LEdge[initialLength];
    }
}

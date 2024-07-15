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

    List<LNode> visitedNodes;

    public Ant(LGraph graph, int id) {
        this.visitedEdges = new ArrayList<LEdge>();
        this.accCost = 0;
        this.id = String.valueOf(id);

        this.visitedNodes = new ArrayList<LNode>(graph.nodes);
        Collections.shuffle(visitedNodes);
        createPath(graph);
    }

    public Ant(LGraph lGraph, Ant parent1, Ant parent2, int id){
        int newId = Integer.parseInt(parent1.getId())+Integer.parseInt(parent2.getId())+id;
        this.id = String.valueOf(newId);
        this.visitedNodes = new ArrayList<>();
        this.visitedEdges = new ArrayList<LEdge>();
        for (int i = 0; i < lGraph.nodes.size()/2; i++) {
            this.visitedNodes.add(parent1.getVisitedNodes().get(i));
        }
        for (LNode lNode : parent2.visitedNodes) {
            if(!this.visitedNodes.contains(lNode)){
                this.visitedNodes.add(lNode);
            }
        }

        createPath(lGraph);

    }

    ///////// PUBLIC ////////

    public List<LEdge> getVisitedEdges() {
        return visitedEdges;
    }

    public int getAccCost(){
        return accCost;
    }

    public String getId(){
        return this.id;
    }

    public List<LNode> getVisitedNodes(){
        return visitedNodes;
    }

    @Override
    public String toString(){
        return "Ant "+id+" ("+visitedNodes+")";
    }

    ///////// PRIVATE //////

    private void createPath(LGraph lGraph){
        // random path this ant takes
        for (int i = 1; i < visitedNodes.size(); i++) {
            LEdge edge = lGraph.findEdge(visitedNodes.get(i - 1), visitedNodes.get(i));
            edge.markAsTried();
            this.visitedEdges.add(edge);
            this.accCost += edge.getCost();
        }
        LEdge edge = lGraph.findEdge(visitedNodes.get(0), visitedNodes.get(visitedNodes.size()-1));
        edge.markAsTried();
        this.visitedEdges.add(edge);
        this.accCost += edge.getCost();
//        System.out.println("          Ant Created: "+this.toString()+" Cost: "+this.getAccCost());
    }

}

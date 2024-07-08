package GeneticAlg;

import GraphMaker.LGraph;
import GraphMaker.LEdge;
import GraphMaker.LNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ant {

    private LNode currentNode; // Location
    private final LNode startingNode; // To complete the circle
    private int accCost; // Price of the path so far
    private int visitedNodesIndex; // Pointer to next space in node array
    private LNode[] visitedNodes; // Path so far
    private int visitedEdgesIndex; // Pointer to next space in edges array
    private LEdge[] visitedEdges; // Path so far
    private boolean alive = true; // Is the ant stuck?
    private final LGraph graph;
    private final String id;
    private boolean isQueen = false;

    public Ant(LGraph graph, int id) {
        Random rand = new Random();
        this.graph = graph;
        this.currentNode = graph.nodes[rand.nextInt(graph.nodes.length)];
        this.startingNode = currentNode;
        this.visitedNodes = new LNode[graph.nodes.length + 1];
        this.visitedNodes[0] = currentNode;
        this.visitedNodesIndex = 1;
        this.visitedEdges = new LEdge[graph.edges.length];
        this.visitedEdgesIndex = 0;
        this.accCost = 0;
        this.id = "Ant " + id;
    }

    public Ant(Ant parentAnt, int generation) {
        this.graph = parentAnt.graph;
        this.currentNode = parentAnt.currentNode;
        this.startingNode = parentAnt.startingNode;
        this.visitedNodes = Arrays.copyOf(parentAnt.visitedNodes, parentAnt.visitedNodes.length);
        this.visitedNodesIndex = parentAnt.visitedNodesIndex;
        this.visitedEdges = Arrays.copyOf(parentAnt.visitedEdges, parentAnt.visitedEdges.length);
        this.visitedEdgesIndex = parentAnt.visitedEdgesIndex;
        this.accCost = parentAnt.accCost;
        this.id = String.valueOf(generation);
    }

    ///////// PUBLIC ////////

    public void randomStep() {
        Random rand = new Random();
        alive = false;
        LEdge[] edgesToChoseFrom = this.currentNode.getEdges();
        LEdge chosenEdge = null;
        LNode chosenNode = null;
        LEdge tempEdge;
        boolean foundOne = false;
        int attempts = 0;
        int maxAttempts = edgesToChoseFrom.length * 2; // Prevent infinite loops

        while (!foundOne && attempts < maxAttempts) {
            attempts++;
            int picker = rand.nextInt(edgesToChoseFrom.length);
            tempEdge = edgesToChoseFrom[picker];
            if (tempEdge != null && checkIfVisitedEdge(tempEdge)) {
                tempEdge.markAsTried(); // Mark edge as tried
                for (LNode lNode : tempEdge.getNodes()) {
                    if (!(lNode.equals(this.currentNode)) && !(checkIfVisitedNode(lNode))) {
                        chosenEdge = tempEdge;
                        chosenNode = lNode;
                        alive = true;
                        foundOne = true;
                        break;
                    }
                    if (checkIfAllowedHome() && lNode.equals(startingNode)) {
                        chosenEdge = tempEdge;
                        chosenNode = lNode;
                        alive = true;
                        isQueen = true;
                        foundOne = true;
                        break;
                    }
                }
            }
        }
        if (!foundOne) {
            System.out.println("          ANT: " + id + " DIED! ");
        }
        updateAnt(chosenEdge, chosenNode);
        if (isQueen) {
            System.out.println("\n          A Queen was found!");
            System.out.println("          Ant: " + this.getid() + ", Code: " + this.getAccCost() + "\n");
        }
    }

    public void trimEdgesArray() {
        List<LEdge> newEdgesList = new ArrayList<>();
        for (LEdge lEdge : this.visitedEdges) {
            if (lEdge != null) {
                newEdgesList.add(lEdge);
            }
        }
        visitedEdges = newEdgesList.toArray(new LEdge[0]);
    }

    public int getAccCost() {
        return accCost;
    }

    public LEdge[] getVisitedEdges() {
        return visitedEdges;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public String getid() {
        return id;
    }

    ///////// PRIVATE //////

    private void updateAnt(LEdge chosenEdge, LNode chosenNode) {
        if (alive && chosenEdge != null && chosenNode != null) {
            System.out.println("          :::::" + id + " walked " + chosenEdge.toString());
            this.visitedEdges[visitedEdgesIndex] = chosenEdge;
            visitedEdgesIndex++;
            this.currentNode = chosenNode;
            this.visitedNodes[visitedNodesIndex] = this.currentNode;
            visitedNodesIndex++;
            this.accCost += chosenEdge.getCost();
        }
    }

    private boolean checkIfVisitedEdge(LEdge edge) {
        for (LEdge visitedEdge : visitedEdges) {
            if (visitedEdge == null) {
                return true;
            }
            if (visitedEdge.equals(edge)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfVisitedNode(LNode node) {
        for (LNode visitedNode : visitedNodes) {
            if (visitedNode == null) {
                return false;
            }
            if (visitedNode.equals(node)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfAllowedHome() {
        int counter = 0;
        for (LNode lNode : graph.nodes) {
            for (LNode lNodethis : visitedNodes) {
                if (lNode.equals(lNodethis)) {
                    counter++;
                }
            }
        }
        return counter == graph.nodes.length;
    }
}

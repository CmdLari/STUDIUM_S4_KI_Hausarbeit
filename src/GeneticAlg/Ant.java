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
    private boolean alive; // Is the ant stuck?
    private final LGraph graph;
    private final String id;
    private boolean isQueen;

    public Ant(LGraph graph, int id) {
        this.graph = graph;
        this.currentNode = graph.nodes[0];
        this.startingNode = currentNode;
        this.visitedNodes = new LNode[graph.nodes.length + 1];
        this.visitedNodes[0] = currentNode;
        this.visitedNodesIndex = 1;
        this.visitedEdges = new LEdge[graph.edges.length];
        this.visitedEdgesIndex = 0;
        this.accCost = 0;
        this.id = "Ant " + id;
        this.alive = true;
        this.isQueen = false;

    }

    public Ant(Ant parent1, Ant parent2, int generation) {
        this.graph = parent1.graph;
        this.id = "Ant "+generation;
        this.startingNode = graph.nodes[0];
        currentNode = startingNode;
        this.accCost = 0;
        mixParents(parent1, parent2);
        this.alive = checkWalkable(startingNode, 0);
        this.isQueen = alive;
    }

    private boolean checkWalkable(LNode steppingStone, int nodeCounter) {
        boolean canContinue = false;
        if (nodeCounter == graph.nodes.length) {
            return true;
        }

        for (LEdge edge : visitedEdges){
            for (LNode node : edge.getNodes()){
                if (node.equals(steppingStone)){
                    canContinue = true;
                }
            }
            if (canContinue){
                for (LNode node : edge.getNodes()){
                    if (!node.equals(steppingStone)) {
                        steppingStone = node;
                    }
                }
                nodeCounter++;
                checkWalkable(steppingStone, nodeCounter);
            }
        }

        return false;
    }

    private void mixParents(Ant parent1, Ant parent2) {
        List<LNode> newNodesList = new ArrayList<>();
        List<LEdge> newEdgesList = new ArrayList<>();
        List<LEdge> allEdges = new ArrayList<>();
        allEdges.addAll(Arrays.stream(parent1.visitedEdges).toList());
        allEdges.addAll(Arrays.stream(parent2.visitedEdges).toList());

        allEdges = shuffle(allEdges);

        for (LEdge edge : allEdges) {
            newEdgesList.add(edge);
            for (LNode node : edge.getNodes()){
                if(!newNodesList.contains(node)){
                    newNodesList.add(node);
                    if(!newEdgesList.contains(edge)){
                        newEdgesList.add(edge);
                        this.accCost+=edge.getCost();
                    }
                }
            }
        }
        LNode[] newNodes = new LNode[newNodesList.size()];
        for (int i = 0; i < newNodes.length; i++) {
            newNodes[i] = newNodesList.get(i);
        }
        LEdge[] newEdges = new LEdge[newEdgesList.size()];
        for (int i = 0; i < newEdgesList.size(); i++) {
            newEdges[i] = newEdgesList.get(i);
        }
        this.visitedEdges = newEdges;
        this.visitedNodes = newNodes;
    }

    private List<LEdge> shuffle(List<LEdge> allEdges) {
        Random random = new Random();
        List<LEdge> newNodesList = new ArrayList<>();
        int start = random.nextInt(allEdges.size()-1);
        for(int i = 0; i < allEdges.size(); i++){
            newNodesList.add(allEdges.get(start));
            start++;
            if(start == allEdges.size()){
                start = 0;
            }
        }
        return newNodesList;
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
                        System.out.println("\n          A Queen was found!");
                        System.out.println("          Ant: " + this.getid() + ", Code: " + this.getAccCost() + "\n");
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
        updateAnt(chosenEdge, chosenNode);
        if (!alive&&!isQueen) {
            System.out.println("          ANT: " + id + " DIED! ");
        }

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

    public LNode[] getVisitedNodes() {
        return visitedNodes;
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

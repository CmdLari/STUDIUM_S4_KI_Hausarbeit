package GeneticAlg;
import GraphMaker.LGraph;
import GraphMaker.LEdge;
import GraphMaker.LNode;

import java.util.Arrays;
import java.util.Random;

public class Ant {

    private LNode currentNode; //Locatio
    private final LNode startingNode; //To complete the circle
    private int accCost; //Price of the path so far
    private int visitedNodesIndex; //Pointer to next space in node array
    private LNode[] visitedNodes; //Path so far
    private int visitedEdgesIndex; //Pointer to next space in edges array
    private LEdge[] visitedEdges; //Path so far
    private boolean alive = true; //Is the ant stuck?
    private LGraph graph;
    private String id;
    private boolean isQueen = false;

    /**
     * Initial unit to start traversing the graph
     * initial costs: 0, initial starting node: Random
     * @param graph The graph to be traversed
     */
    public Ant(LGraph graph, int id) {
        Random rand = new Random();
        this.graph = graph;
        this.currentNode = graph.nodes[rand.nextInt(0, graph.nodes.length-1)];
        this.startingNode = currentNode;
        this.visitedNodes = new LNode[graph.nodes.length+1];
        this.visitedNodes[0] = currentNode;
        this.visitedNodesIndex = 1;
        this.visitedEdges = new LEdge[graph.edges.length];
        this.visitedEdgesIndex = 0;
        this.accCost = 0;
        this.id = "Ant "+id;
    }

    /**
     * Ant that isn't 1st gen
     * @param parentAnt The parent ant
     */
    public Ant(Ant parentAnt, int generation) {
            this.graph = parentAnt.graph;
            this.currentNode = parentAnt.currentNode;
            this.startingNode = parentAnt.startingNode;
            this.visitedNodes = parentAnt.visitedNodes;
            this.visitedNodesIndex = parentAnt.visitedNodesIndex;
            this.visitedEdges = parentAnt.visitedEdges;
            this.visitedEdgesIndex = parentAnt.visitedEdgesIndex;
            this.accCost = parentAnt.accCost;
            this.id = parentAnt.id + "_" + generation;
    }

    ///////// PUBLIC ////////

    public void cheapestStep() {
        alive = false;
        LEdge[] edgesToChoseFrom = this.currentNode.getEdges();
        int cheapest =-5;
        LEdge chosenEdge = null;
        LNode chosenNode = null;
        LEdge tempEdge;

        for (LEdge lEdge : edgesToChoseFrom) {
            if (!(lEdge==null)){
                if (checkIfVisitedEdge(lEdge) && (lEdge.getCost() < cheapest || (cheapest==-5))) {
                    tempEdge = lEdge;
                    for (LNode lNode : tempEdge.getNodes()) {
                        if (!(lNode.Lequals(this.currentNode))&&!(checkifVisitedNode(lNode))) {
                            chosenEdge = lEdge;
                            cheapest = lEdge.getCost();
                            chosenNode = lNode;
                            alive = true;
                        }
                        if (checkIfAllowedHome()) {
                            if(lNode.equals(startingNode)){
                                chosenEdge = lEdge;
                                cheapest = lEdge.getCost();
                                chosenNode = lNode;
                                isQueen = true;
                                alive = true;
                            }
                        }
                    }
                }
            }
        }
        updateAnt(chosenEdge, chosenNode);
    }

    public void randomStep(){
        Random rand = new Random();
        alive = false;
        LEdge[] edgesToChoseFrom = this.currentNode.getEdges();
        LEdge chosenEdge = null;
        LNode chosenNode = null;
        LEdge tempEdge;
        boolean foundOne = false;
        while (!foundOne){
            int picker = rand.nextInt(edgesToChoseFrom.length-1);
            if (checkIfVisitedEdge(edgesToChoseFrom[picker]) &&(edgesToChoseFrom[picker]!=null)) {
                tempEdge = edgesToChoseFrom[picker];
                for (LNode lNode : tempEdge.getNodes()) {
                    if (!(lNode.Lequals(this.currentNode))&&!(checkifVisitedNode(lNode))) {
                        chosenEdge = edgesToChoseFrom[picker];
                        chosenNode = lNode;
                        alive = true;
                        foundOne = true;
                    }
                    if (checkIfAllowedHome()) {
                        if(lNode.equals(startingNode)){
                            chosenEdge = edgesToChoseFrom[picker];
                            alive = true;
                            foundOne = true;
                            isQueen = true;
                        }
                    }
                }
            }
        }
        updateAnt(chosenEdge, chosenNode);
        if(isQueen){
            System.out.println("\n          A Queen was found!");
            System.out.println("          Cost: "+this.getAccCost() + ", " + Arrays.toString(this.getVisitedEdges())+"\n");
        }
    }

    public void trimEdgesArray(){
        int newEdgeArrayLength = 0;
        for (LEdge lEdge : this.visitedEdges) {
            if(!(lEdge==null)){
                newEdgeArrayLength++;
            }
        }
        LEdge[] newEdges = new LEdge[newEdgeArrayLength];
        for (int i = 0; i < newEdgeArrayLength; i++) {
            newEdges[i] = this.visitedEdges[i];
        }
        visitedEdges = newEdges;
    }

    public int getAccCost(){
        return accCost;
    }

    public LEdge[] getVisitedEdges() {
        return visitedEdges;
    }

    public boolean isAlive(){
        return alive;
    }

    public boolean isQueen(){
        return isQueen;
    }

    ///////// PRIVATE //////

    private void updateAnt(LEdge chosenEdge, LNode chosenNode) {
        if (alive) {
            System.out.println("          :::::"+id+" walked "+chosenEdge.toString());
            this.visitedEdges[visitedEdgesIndex] = chosenEdge;
            visitedEdgesIndex++;
            this.currentNode = chosenNode;
            this.visitedNodes[visitedNodesIndex] = this.currentNode;
            visitedNodesIndex++;
            this.accCost += chosenEdge.getCost();
        }
        if(!alive) {
            System.out.println("!!!BOB DIED!!!");
        }
    }

    private boolean checkIfVisitedEdge(LEdge edge) {
        for (LEdge visitedEdge : visitedEdges) {
            if (visitedEdge==null){
                return true;
            }
            if(visitedEdge.equals(edge)){
                return false;
            }
        }
        return true;
    }

    private boolean checkifVisitedNode(LNode node) {
        for (LNode visitedNode : visitedNodes) {
            if(visitedNode==null){
                return false;
            }
            if(visitedNode.equals(node)){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfAllowedHome() {
        int counter = 0;
        for (LNode lNode: graph.nodes){
            for(LNode lNodethis : visitedNodes){
                if(lNode.equals(lNodethis)){
                    counter++;
                }
            }
        }
        if(counter==graph.nodes.length){
            return true;
        }
        return false;
    }
}

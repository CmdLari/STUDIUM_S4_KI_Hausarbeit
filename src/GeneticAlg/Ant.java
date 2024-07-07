package GeneticAlg;
import GraphMaker.LGraph;
import GraphMaker.LEdge;
import GraphMaker.LNode;

import java.util.Random;

public class Ant {

    public LNode currentNode; //Locatio
    public int accCost; //Price of the path so far
    public int visitedNodesIndex; //Pointer to next space in node array
    public LNode[] visitedNodes; //Path so far
    int visitedEdgesIndex; //Pointer to next space in edges array
    public LEdge[] visitedEdges; //Path so far
    public boolean alive = true; //Is the ant stuck?
    LGraph graph;

    /**
     * Initial unit to start traversing the graph
     * initial costs: 0, initial starting node: Random
     * @param graph The graph to be traversed
     */
    public Ant(LGraph graph) {
        Random rand = new Random();
        this.graph = graph;
        this.currentNode = graph.nodes[rand.nextInt(0, graph.nodes.length-1)];
        this.visitedNodes = new LNode[graph.nodes.length];
        this.visitedNodes[0] = currentNode;
        this.visitedNodesIndex = 1;
        this.visitedEdges = new LEdge[graph.edges.length];
        this.visitedEdgesIndex = 0;
        this.accCost = 0;
    }

    /**
     * Ant that isn't 1st gen
     * @param parentAnt The parent ant
     */
    public Ant(Ant parentAnt) {
            this.graph = parentAnt.graph;
            this.currentNode = parentAnt.currentNode;
            this.visitedNodes = parentAnt.visitedNodes;
            this.visitedNodesIndex = parentAnt.visitedNodesIndex;
            this.visitedEdges = parentAnt.visitedEdges;
            this.visitedEdgesIndex = parentAnt.visitedEdgesIndex;
            this.accCost = parentAnt.accCost;
    }

    ///////// PUBLIC ////////

    public void cheapestStep() {
        alive = false;
        LEdge[] edgesToChoseFrom = this.currentNode.getEdges();
        int cheapest = edgesToChoseFrom[0].getCost();
        LEdge chosenEdge = null;
        for (LEdge lEdge : edgesToChoseFrom) {
            if (!checkIfVisited(lEdge) && lEdge.getCost() < cheapest) {
                cheapest = lEdge.getCost();
                chosenEdge = lEdge;
                alive = true;
            }
        }
        if (!(chosenEdge == null)) {
            for (LNode lNode : chosenEdge.getNodes()) {
                if (!(lNode.Lequals(this.currentNode))) {
                    this.currentNode = lNode;
                }
            }
            this.visitedEdges[visitedEdgesIndex] = chosenEdge;
            visitedEdgesIndex++;
            this.visitedNodes[visitedNodesIndex] = this.currentNode;
            visitedEdgesIndex++;
            this.accCost += chosenEdge.getCost();
        }
    }

    public void cleanWinningRoute(){
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

    ///////// PRIVATE //////

    private boolean checkIfVisited(LEdge edge) {
        for (LEdge visitedEdge : visitedEdges) {
            return visitedEdge.Leguals(edge);
        }
        return false;
    }

//    public void cheapestStep(){
//        alive = false;
//        LEdge[] isLeftNodeEdges = graph.isLeftNodeEdges(this.currentNode);
//        int minCost = isLeftNodeEdges[0].getCost();
//
//        if(isLeftNodeEdges.length==1){
//            if(notVisited(isLeftNodeEdges[0])) {
//                this.currentNode = isLeftNodeEdges[0].getRightNode();
//                this.visitedNodes[visitedNodesIndex] = currentNode;
//                this.visitedNodesIndex++;
//                alive = true;
//                visitedEdges[visitedEdgesIndex]=isLeftNodeEdges[0];
//                visitedEdgesIndex++;
//            }
//        }
//        else{
//            int takenEdge = 0;
//            for (int i = 1; i < isLeftNodeEdges.length; i++) {
//                    if (isLeftNodeEdges[i].getCost() < minCost && notVisited(isLeftNodeEdges[i])) {
//                        minCost = isLeftNodeEdges[i].getCost();
//                        this.currentNode = isLeftNodeEdges[i].getRightNode();
//                        this.visitedNodes[visitedNodesIndex]=currentNode;
//                        this.visitedNodesIndex++;
//                        alive = true;
//                        takenEdge = i;
//                    }
//            }
//            if(alive){
//                this.visitedEdges[visitedEdgesIndex]=isLeftNodeEdges[takenEdge];
//                this.visitedEdgesIndex++;
//            }
//        }
//        this.accCost+=minCost;
//        if(!alive){
//            System.out.println("Ant is dead");
//        }
//    }
//
//    public void randomStep() {
//        // This tries up to 5 times to take a step
//        alive = false;
//        Random rand = new Random();
//        LEdge[] isLeftNodeEdges = graph.isLeftNodeEdges(this.currentNode);
//        boolean stillLooping = true;
//        for(int i = 0; i<5 && i<isLeftNodeEdges.length-1; i++){
//            LEdge chosenEdge = isLeftNodeEdges[rand.nextInt(isLeftNodeEdges.length - 1)];
//            if(notVisited(isLeftNodeEdges[i])&& stillLooping){
//                this.currentNode = chosenEdge.getRightNode();
//                this.visitedNodes[visitedNodesIndex]=currentNode;
//                this.visitedNodesIndex++;
//                this.accCost+=chosenEdge.getCost();
//                stillLooping = false;
//                visitedEdges[visitedEdgesIndex]=chosenEdge;
//                visitedEdgesIndex++;
//                alive = true;
//            }
//        }
//        if(!alive){
//            System.out.println("Ant is dead");
//        }
//    }
//
//    private boolean notVisited(LEdge isLeftNodeEdge){
//        for (LNode lNode : visitedNodes) {
//            if (!(lNode==null)&&lNode.equals(isLeftNodeEdge.getRightNode())){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void cleanWinningRoute(){
//
//        if (!(visitedEdges==null)){
//            int winningRoutelength=0;
//            for (LEdge visitedEdge : visitedEdges) {
//                if (!(visitedEdge == null)) {
//                    winningRoutelength++;
//                }
//            }
//            LEdge[] shortenedWinningRoute = new LEdge[winningRoutelength];
//            for(int i = 0; i<winningRoutelength; i++){
//                shortenedWinningRoute[i]=visitedEdges[i];
//            }
//            this.visitedEdges=shortenedWinningRoute;
//        }
//    }
}

package GeneticAlg;
import GraphMaker.LGraph;
import GraphMaker.LEdge;
import GraphMaker.LNode;

import java.util.Random;

public class Ant {

    public LNode currentNode; //Locatio
    public LNode startingNode; //To complete the circle
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
        this.startingNode = currentNode;
        this.visitedNodes = new LNode[graph.nodes.length+1];
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
            this.startingNode = parentAnt.startingNode;
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
        int cheapest =-5;
        LEdge chosenEdge = null;
        LNode chosenNode = null;
        LEdge tempEdge = null;

        for (LEdge lEdge : edgesToChoseFrom) {
            if (!(lEdge==null)){
                if (!checkIfVisitedEdge(lEdge) && (lEdge.getCost() < cheapest || (cheapest==-5))) {
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
                                alive = true;
                            }
                        }
                    }
                }
            }
        }
        if (alive) {
            System.out.println("Bob walked "+chosenEdge.toString());
            this.visitedEdges[visitedEdgesIndex] = chosenEdge;
            visitedEdgesIndex++;
            this.currentNode = chosenNode;
            this.visitedNodes[visitedNodesIndex] = this.currentNode;
            visitedNodesIndex++;
            this.accCost += chosenEdge.getCost();
        }
        else {
            System.out.println("Bob died!");
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

    ///////// PRIVATE //////

    private boolean checkIfVisitedEdge(LEdge edge) {
        for (LEdge visitedEdge : visitedEdges) {
            if (visitedEdge==null){
                return false;
            }
            if(visitedEdge.equals(edge)){
                return true;
            }
        }
        return false;
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
            System.out.println("Found a Queen!");
            return true;
        }
        return false;
    }


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

package GeneticAlg;
import GraphMaker.LGraph;
import GraphMaker.LEdge;
import GraphMaker.LNode;

import java.util.Random;

public class Ant {

//    Needs to:
//    Be able to step in a direction
//
//    Has:
//    Cost Sum
//    Current Node / Location
//    List of visited nodes
//
//    Check, if it has reached the destination
//    Ants need to be alive

    public LNode currentNode;
    public int accCost;
    public int visitedNodesIndex;
    public LNode[] visitedNodes;
    int visitedEdgesIndex;
    public LEdge[] visitedEdges;
    public boolean alive = true;
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
            this.accCost = parentAnt.accCost;
            this.visitedEdgesIndex = parentAnt.visitedEdgesIndex;
    }



    public void cheapestStep(){
        alive = false;
        LEdge[] isLeftNodeEdges = graph.isLeftNodeEdges(this.currentNode);
        int minCost = isLeftNodeEdges[0].getCost();

        if(isLeftNodeEdges.length==1){
            if(notVisited(isLeftNodeEdges[0])) {
                this.currentNode = isLeftNodeEdges[0].getRightNode();
                this.visitedNodes[visitedNodesIndex] = currentNode;
                this.visitedNodesIndex++;
                alive = true;
                visitedEdges[visitedEdgesIndex]=isLeftNodeEdges[0];
            }
        }
        else{
            int takenEdge = 0;
            for (int i = 1; i < isLeftNodeEdges.length; i++) {
                    if (isLeftNodeEdges[i].getCost() < minCost && notVisited(isLeftNodeEdges[i])) {
                        minCost = isLeftNodeEdges[i].getCost();
                        this.currentNode = isLeftNodeEdges[i].getRightNode();
                        this.visitedNodes[visitedNodesIndex]=currentNode;
                        this.visitedNodesIndex++;
                        alive = true;
                        takenEdge = i;
                    }
            }
            if(alive){
                this.visitedEdges[visitedEdgesIndex]=isLeftNodeEdges[takenEdge];
            }
        }
        this.accCost+=minCost;
        if(!alive){
            System.out.println("Ant is dead");
        }
    }

    public void randomStep() {
        // This tries up to 5 times to take a step
        alive = false;
        Random rand = new Random();
        LEdge[] isLeftNodeEdges = graph.isLeftNodeEdges(this.currentNode);
        boolean stillLooping = true;
        for(int i = 0; i<5 && i<isLeftNodeEdges.length-1; i++){
            LEdge chosenEdge = isLeftNodeEdges[rand.nextInt(isLeftNodeEdges.length - 1)];
            if(notVisited(isLeftNodeEdges[i])&& stillLooping){
                this.currentNode = chosenEdge.getRightNode();
                this.visitedNodes[visitedNodesIndex]=currentNode;
                this.visitedNodesIndex++;
                this.accCost+=chosenEdge.getCost();
                stillLooping = false;
                visitedEdges[visitedEdgesIndex]=chosenEdge;
                alive = true;
            }
        }
        if(!alive){
            System.out.println("Ant is dead");
        }
    }

    private boolean notVisited(LEdge isLeftNodeEdge){
        for (LNode lNode : visitedNodes) {
            if (lNode.equals(isLeftNodeEdge.getRightNode())){
                return false;
            }
        }
        return true;
    }

    public void cleanWinningRoute(){
        int winningRoutelength=0;
        for(int i = 0; i<visitedEdges.length; i++){
            if (!(visitedEdges[i] == null)){
                winningRoutelength++;
            }
        }
        LEdge[] shortenedWinningRoute = new LEdge[winningRoutelength];
        for(int i = 0; i<winningRoutelength; i++){
            shortenedWinningRoute[i]=visitedEdges[i];
        }
        this.visitedEdges=shortenedWinningRoute;
    }
}

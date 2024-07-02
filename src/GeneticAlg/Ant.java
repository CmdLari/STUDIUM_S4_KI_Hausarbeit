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

    public LNode currentNode;
    public int accCost;
    public int visitedNodesIndex;
    public LNode[] visitedNodes;
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
    }


    /////////////////////////////////////////////////////
    //////////WARNING////////////////////////////////////
    /////////Not checking if Node is visited yet/////////
    /////////////////////////////////////////////////////

    public void cheapestStep(){
        LEdge[] isLeftNodeEdges = graph.isLeftNodeEdges(this.currentNode);
        int minCost = isLeftNodeEdges[0].getCost();

        if(isLeftNodeEdges.length==1){
            this.currentNode=isLeftNodeEdges[0].getRightNode();
            this.visitedNodes[visitedNodesIndex]=currentNode;
            this.visitedNodesIndex++;
        }
        else{
            for (int i = 1; i < isLeftNodeEdges.length; i++) {
                if (isLeftNodeEdges[i].getCost() < minCost) {
                    minCost = isLeftNodeEdges[i].getCost();
                    this.currentNode = isLeftNodeEdges[i].getRightNode();
                    this.visitedNodes[visitedNodesIndex]=currentNode;
                    this.visitedNodesIndex++;
                }
            }
        }
        this.accCost+=minCost;
    }

    public void randomStep() {
        Random rand = new Random();
        LEdge[] isLeftNodeEdges = graph.isLeftNodeEdges(this.currentNode);
        LEdge chosenEdge = isLeftNodeEdges[rand.nextInt(isLeftNodeEdges.length - 1)];
        this.currentNode = chosenEdge.getRightNode();
        this.visitedNodes[visitedNodesIndex]=currentNode;
        this.visitedNodesIndex++;
        this.accCost+=chosenEdge.getCost();
    }
}

package GraphMaker;

import GeneticAlg.Ant;
import Visualisation.GraphVisualizer;
import org.graphstream.graph.Graph;

import java.util.Random;

// UNDIRECTED
// WEIGHTED
// Nodes have min 2 Edges
// No Loops LNode i to LNode i

public class LGraph {

    public LNode[] nodes;
    public LEdge[] edges;

    public LGraph(int MAXNODES, int MAXEDGES, int MAXEDGECOST) {
        generateGraph(MAXNODES, MAXEDGES, MAXEDGECOST);
    }

    /// PUBLIC METHODS ///

    // get left array of edges where node is left node
    public LEdge[] isLeftNodeEdges(LNode leftNode){
        LEdge[] edges = leftNode.edges;
        LEdge[] isLeftNodeEdges = new LEdge[edges.length];
        int countEdges = 0;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].getLeftNode()==leftNode){
                isLeftNodeEdges[countEdges] = edges[i];
                countEdges++;
            }
        }
        LEdge[] noNullisLeftNodeEdges = new LEdge[countEdges];
        for (int i = 0; i < countEdges; i++) {
            noNullisLeftNodeEdges[i] = isLeftNodeEdges[i];
        }
        return noNullisLeftNodeEdges;
    }

    // Private Methods

    private void generateGraph(int MAXNODES, int MAXEDGES, int MAXEDGECOST) {
        Random rand = new Random();

        // How many nodes?
        int nodeNumber = rand.nextInt(2, MAXNODES);
        if (nodeNumber == 2) {
            nodeNumber = 3;
        }
        nodes = new LNode[nodeNumber];
        System.out.println("\nNodes number: " + nodeNumber);

        // create nodes
        for (int i = 0; i < nodeNumber; i++) {
            LNode newNode = new LNode();
            nodes[i] = newNode;
        }

        // How many edges?
        int edgeNumber = rand.nextInt(nodeNumber, MAXEDGES);
        edges = new LEdge[edgeNumber];
        System.out.println("\nEdges number: " + edgeNumber);

        // create edges
        for (int i = 0; i < edgeNumber; i++) {
            LEdge newEdge = new LEdge(MAXEDGECOST);
            edges[i] = newEdge;
        }

        // connect edges and nodes
        int pointer = 0;

        // connect all nodes in an initial circle
        while (pointer < nodeNumber-1) {
            nodes[pointer].setEdge(edges[pointer]);
            edges[pointer].setLeftNode(nodes[pointer]);
            edges[pointer].setRightNode(nodes[pointer + 1]);
            nodes[pointer + 1].setEdge(edges[pointer]);
            System.out.println("\nConnected: "+pointer+" with "+(pointer+1)+" | Cost: "+edges[pointer].getCost());
            pointer++;
        }
        // close the circle
        if (pointer == nodeNumber-1) {
            nodes[pointer].setEdge(edges[pointer]);
            edges[pointer].setLeftNode(nodes[pointer]);
            edges[pointer].setRightNode(nodes[0]);
            nodes[0].setEdge(edges[pointer]);
            System.out.println("\nConnected: "+pointer+" with "+0+" | Cost: "+edges[pointer].getCost());
            pointer++;
        }

        // set random edges
        Random randEdge = new Random();
        while (pointer < edgeNumber) {
            int i = randEdge.nextInt(0, nodeNumber);
            edges[pointer].setLeftNode(nodes[i]);
            nodes[i].setEdge(edges[pointer]);
            int j = randEdge.nextInt(0, nodeNumber);
            if (i==j && i<nodeNumber-1){
                j = j+1;
            } else if (i==j && i==nodeNumber-1){
                j = 0;
            }
            edges[pointer].setRightNode(nodes[j]);
            nodes[j].setEdge(edges[pointer]);
            System.out.println("\nConnected: "+i+" with "+j+" | Cost: "+edges[pointer].getCost());
            pointer++;
        }

        // clear up edge lists of nodes
        for (LNode node : nodes) {
//            System.out.print("\n" + node.edges.length);
            node.clearEdges();
//            System.out.print(" to " + node.edges.length + "\n");
        }

    }

}

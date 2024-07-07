package GraphMaker;

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

    ///////// PUBLIC ////////


    ///////// PRIVATE ///////

    private void generateGraph(int MAXNODES, int MAXEDGES, int MAXEDGECOST) {
        Random rand = new Random();

        System.out.println("INITIALIZING GRAPH");

        // How many nodes?
        int nodeNumber = rand.nextInt(3, MAXNODES);
        nodes = new LNode[nodeNumber];
        System.out.println("Number of Nodes: " + nodeNumber);

        // create nodes
        for (int i = 0; i < nodeNumber; i++) {
            LNode newNode = new LNode(i);
            nodes[i] = newNode;
        }

        // How many edges?
        int edgeNumber = rand.nextInt(nodeNumber, MAXEDGES);
        edges = new LEdge[edgeNumber];
        System.out.println("Number of Edges: " + edgeNumber);

        // create edges
        for (int i = 0; i < edgeNumber; i++) {
            LEdge newEdge = new LEdge(i, MAXEDGECOST);
            edges[i] = newEdge;
        }

        // connect edges and nodes

        int edgePointer = 0;
        System.out.print("\n");

        // // Initialize chain
        edges[edgePointer].addNode(nodes[edgePointer]);
        nodes[edgePointer].setEdge(edges[edgePointer]);
        System.out.println("Connected: "+edges[edgePointer].toString()+" with "+nodes[edgePointer].toString());

        // // Ensure there is at least one functional route
        for (edgePointer=1; edgePointer < nodeNumber; edgePointer++) {
            edges[edgePointer-1].addNode(nodes[edgePointer]);
            nodes[edgePointer].setEdge(edges[edgePointer-1]);
            System.out.println("Connected: "+edges[edgePointer-1].toString()+" with "+nodes[edgePointer].toString());

            edges[edgePointer].addNode(nodes[edgePointer]);
            nodes[edgePointer].setEdge(edges[edgePointer]);
            System.out.println("Connected: "+edges[edgePointer].toString()+" with "+nodes[edgePointer].toString());

            if (edgePointer==nodeNumber-1) {
                edges[edgePointer].addNode(nodes[0]);
                nodes[0].setEdge(edges[edgePointer]);
                System.out.println("Connected: "+edges[edgePointer].toString()+" with "+nodes[0].toString());
            }
        }

        // // Insert the remaining edges
        while (edgePointer < edgeNumber) {
            int nodeOne = rand.nextInt(0, nodeNumber-1);
            int nodeTwo = rand.nextInt(0, nodeNumber-1);
            edges[edgePointer].addNode(nodes[nodeOne]);
            nodes[nodeOne].setEdge(edges[edgePointer]);

            edges[edgePointer].addNode(nodes[nodeTwo]);
            nodes[nodeTwo].setEdge(edges[edgePointer]);
            edgePointer++;
        }
    }
}

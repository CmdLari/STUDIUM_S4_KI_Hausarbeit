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

        // How many nodes?
        int nodeNumber = rand.nextInt(3, MAXNODES);
        nodes = new LNode[nodeNumber];
        System.out.println("\nNumber of Nodes: " + nodeNumber);

        // create nodes
        for (int i = 0; i < nodeNumber; i++) {
            LNode newNode = new LNode(i);
            nodes[i] = newNode;
        }

        // How many edges?
        int edgeNumber = rand.nextInt(nodeNumber, MAXEDGES);
        edges = new LEdge[edgeNumber];
        System.out.println("\nNumber of Edges: " + edgeNumber);

        // create edges
        for (int i = 0; i < edgeNumber; i++) {
            LEdge newEdge = new LEdge(i, MAXEDGECOST);
            edges[i] = newEdge;
        }
    }

//        // connect edges and nodes
//        int pointer = 0;
//
//        // connect all nodes in an initial circle
//        while (pointer < nodeNumber-1) {
//            LNode[] visitedNodes = new LNode[nodeNumber];
//
//            pointer++;
//        }
//        // close the circle
//        if (pointer == nodeNumber-1) {
//            nodes[pointer].setEdge(edges[pointer]);
//            edges[pointer].setLeftNode(nodes[pointer]);
//            edges[pointer].setRightNode(nodes[0]);
//            nodes[0].setEdge(edges[pointer]);
//            System.out.println("\nConnected: "+pointer+" with "+0+" | Cost: "+edges[pointer].getCost());
//            pointer++;
//        }
//
//        // set random edges
//        Random randEdge = new Random();
//        while (pointer < edgeNumber) {
//            int i = randEdge.nextInt(0, nodeNumber);
//            edges[pointer].setLeftNode(nodes[i]);
//            nodes[i].setEdge(edges[pointer]);
//            int j = randEdge.nextInt(0, nodeNumber);
//            if (i==j && i<nodeNumber-1){
//                j = j+1;
//            } else if (i==j && i==nodeNumber-1){
//                j = 0;
//            }
//            edges[pointer].setRightNode(nodes[j]);
//            nodes[j].setEdge(edges[pointer]);
//            System.out.println("\nConnected: "+i+" with "+j+" | Cost: "+edges[pointer].getCost());
//            pointer++;
//        }
//
//        // clear up edge lists of nodes
//        for (LNode node : nodes) {
////            System.out.print("\n" + node.edges.length);
//            node.clearEdges();
////            System.out.print(" to " + node.edges.length + "\n");
//        }
//
//    }

}

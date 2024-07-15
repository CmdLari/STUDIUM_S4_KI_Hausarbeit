package GraphMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// UNDIRECTED
// WEIGHTED
// Nodes have min 2 Edges
// No Loops LNode i to LNode i

public class LGraph {

    public List<LNode> nodes;
    public List<LEdge> edges;

    public LGraph(int MAXNODES, int MAXEDGECOST) {
        this.edges = new ArrayList<LEdge>();
        this.nodes = new ArrayList<LNode>();
        generateGraph(MAXNODES, MAXEDGECOST);
    }

    ///////// PUBLIC ////////


    ///////// PRIVATE ///////

    private void generateGraph(int NODENUMBER, int MAXEDGECOST) {
        Random rand = new Random();

        System.out.println("          INITIALIZING GRAPH");

        // How many nodes?
        System.out.println("          Number of Nodes: " + NODENUMBER);

        // create nodes
        for (int i = 0; i < NODENUMBER; i++) {
            LNode newNode = new LNode(i);
            nodes.add(newNode);
        }

        int idCounter = 0;

        // create edges and connect all nodes with each other
        for (LNode node : nodes) {
            for (LNode nodeOther : nodes) {
                if (!nodeOther.equals(node)) {
                    if (nodeOther.getEdges().stream().noneMatch(e -> e.getNodes().contains(node))) {
                        LEdge newEdge = new LEdge(idCounter, rand.nextInt(1, MAXEDGECOST), node, nodeOther);
                        node.setEdge(newEdge);
                        nodeOther.setEdge(newEdge);
                        this.edges.add(newEdge);
                        idCounter++;
//                        System.out.println("          :::::Connected: "+node.toString()+" with "+nodeOther.toString());
                    }
                }
            }
        }
        System.out.println("          Number of Edges: " + edges.size());


        System.out.print("\n");
    }

    public LEdge findEdge(LNode lNode, LNode lNode1) {
        for (LEdge edge : edges) {
            if (edge.getNodes().contains(lNode) && edge.getNodes().contains(lNode1)) {
                return edge;
            }
        }
        return null;
    }
}

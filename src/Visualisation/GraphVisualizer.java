package Visualisation;

import GeneticAlg.Ant;
import GraphMaker.LGraph;
import GraphMaker.LEdge;
import GraphMaker.LNode;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.HashMap;
import java.util.Map;

public class GraphVisualizer {

    public static Graph visualize(LGraph lGraph, Ant ant) {
        Graph graph = new MultiGraph("LGraph");

        colorWinningRoute(lGraph, ant.visitedEdges, graph);

        System.setProperty("org.graphstream.ui.renderer",
                "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        // Add nodes to the GraphStream graph
        for (int i = 0; i < lGraph.nodes.length; i++) {
            graph.addNode("Node" + i);
        }

        // Map to keep track of the number of edges between each pair of nodes
        Map<String, Integer> edgeCountMap = new HashMap<>();

        // Add edges to the GraphStream graph
        for (int i = 0; i < lGraph.edges.length; i++) {
            LEdge edge = lGraph.edges[i];
            LNode leftNode = edge.getLeftNode();
            LNode rightNode = edge.getRightNode();
            int leftNodeIndex = findNodeIndex(lGraph, leftNode);
            int rightNodeIndex = findNodeIndex(lGraph, rightNode);

            if (leftNodeIndex != -1 && rightNodeIndex != -1) {
                String node1Id = "Node" + leftNodeIndex;
                String node2Id = "Node" + rightNodeIndex;
                String edgeKey = node1Id + "-" + node2Id;

                // Get the current count of edges between these nodes and increment
                int edgeCount = edgeCountMap.getOrDefault(edgeKey, 0);
                edgeCountMap.put(edgeKey, edgeCount + 1);

                // Create a unique edge ID by appending the edge count
                String edgeId = "Edge" + i + "_" + edgeCount;

                // Add edge, allowing multi-edges
                try {
                    org.graphstream.graph.Edge graphEdge = graph.addEdge(edgeId, node1Id, node2Id);
                    graphEdge.setAttribute("ui.label", edge.getCost());

                    // Check for specific edge to color differently
                    if (edge.hasBeenWalked) {
                        graphEdge.setAttribute("ui.class", "walked");
                    }
                } catch (org.graphstream.graph.EdgeRejectedException e) {
                    System.err.println("Edge rejected: " + edgeId + " [" + node1Id + " -> " + node2Id + "]");
                }
            }
        }

        // Set visual styles
        for (org.graphstream.graph.Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }

        graph.setAttribute("ui.stylesheet", styleSheet);

        return graph;
    }

    private static int findNodeIndex(LGraph lGraph, LNode node) {
        for (int i = 0; i < lGraph.nodes.length; i++) {
            if (lGraph.nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }

    private static String styleSheet =
            "node {" +
                    "   fill-color: darkgray;" +
                    "   size: 20px, 20px;" +
                    "   text-mode: normal;" +
                    "}" +
                    "edge {" +
                    "   text-mode: normal;" +
                    "   fill-color: gray;" +
                    "   size: 1.5px;" +
                    "}" +
                    "edge.walked {" +
                    "   fill-color: green;" +
                    "   size: 2px;" +
                    "}";

    public static void displayGraph(Graph graph) {
        graph.display();
    }

    public static void colorWinningRoute(LGraph lGraph, LEdge[] winningRoute, Graph graph) {
        // Set hasBeenWalked attribute to true for winning route edges
        for (LEdge lEdge : winningRoute) {
            lEdge.hasBeenWalked = true;
        }

        // Update the GraphStream graph to reflect the changes
        for (LEdge edge : winningRoute) {
            int leftNodeIndex = findNodeIndex(lGraph, edge.getLeftNode());
            int rightNodeIndex = findNodeIndex(lGraph, edge.getRightNode());

            if (leftNodeIndex != -1 && rightNodeIndex != -1) {
                String node1Id = "Node" + leftNodeIndex;
                String node2Id = "Node" + rightNodeIndex;
                String edgeKey = node1Id + "-" + node2Id;

                for (org.graphstream.graph.Edge graphEdge : graph.getEachEdge()) {
                    if (!(graphEdge ==null)){
                        if ((graphEdge.getNode0().getId().equals(node1Id) && graphEdge.getNode1().getId().equals(node2Id)) ||
                                (graphEdge.getNode0().getId().equals(node2Id) && graphEdge.getNode1().getId().equals(node1Id))) {
                            graphEdge.setAttribute("ui.class", "walked");
                        }
                    }
                }
            }
        }
    }
}

package Visualisation;

import GraphMaker.LGraph;
import GraphMaker.LEdge;
import GraphMaker.LNode;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.HashMap;
import java.util.Map;

public class GraphVisualizer {

    public static Graph visualize(LGraph lGraph) {
        Graph graph = new MultiGraph("LGraph");

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
                    graph.addEdge(edgeId, node1Id, node2Id).setAttribute("ui.label", edge.getCost());
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
                    "}";

    public static void displayGraph(Graph graph) {
        graph.display();
    }
}
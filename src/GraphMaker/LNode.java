package GraphMaker;

public class LNode {
    LEdge[] edges = new LEdge[2];

    public LNode() {
    }

    public LEdge[] getEdges() {
        return edges;
    }

    public void setEdge(LEdge newEdge) {
        int placement = 0;
        boolean notPlaced = true;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] == null && notPlaced) {
                placement = i;
                notPlaced = false;
            }
        }
        edges[placement] = newEdge;
        adjustEdges();
    }

    private void adjustEdges() {
        int useCounter = 0;

        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                useCounter++;
            }
        }
        if (useCounter >= edges.length/2) {
            LEdge[] newEdges = new LEdge[edges.length*2];
            for (int i = 0; i < edges.length; i++) {
                newEdges[i] = edges[i];
            }
            this.edges = newEdges;
        }
    }

    // Reduce node's edge arrays to non null values

    public void clearEdges() {
        int nullCounter = 0;

        for (int i = 0; i < edges.length; i++) {
            if (edges[i] == null) {
                nullCounter++;
            }
        }
        if (nullCounter > 0) {
            LEdge[] newEdges = new LEdge[edges.length-nullCounter];
            for (int i = 0; i < newEdges.length; i++) {
                newEdges[i] = edges[i];
            }
            this.edges = newEdges;
        }
    }
}
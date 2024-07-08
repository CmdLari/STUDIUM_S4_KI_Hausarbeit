package GraphMaker;

public class LNode {

    LEdge[] edges = new LEdge[2];
    String id;

    public LNode(int id) {
        this.id = "Node: "+id;
    }

    ///////// PUBLIC ////////

    public String getId() {
        return id;
    }

    public LEdge[] getEdges(){
        return edges;
    }

    public void setEdge(LEdge lEdge){
        checkEdgeArray();
        int placement = checkAvailability();
        edges[placement] = lEdge;
    }

    public boolean equals(LNode lNode){
        if (lNode ==null) return false;
        return this.id.equals(lNode.id);
    }

    public String toString(){
        return id;
    }


    ///////// PRIVATE ///////

    private void checkEdgeArray(){
        int counter = 0;
        for (LEdge edge : edges) {
            if (!(edge == null)) {
                counter++;
            }
        }
        if (counter>=edges.length/2){
            LEdge[] newEdges = new LEdge[edges.length*2];
            for (int i = 0; i < edges.length; i++) {
                newEdges[i]=edges[i];
            }
            this.edges = newEdges;
        }
    }

    private int checkAvailability(){
        int counter;
        for (counter=0; counter < edges.length; counter++) {
            if (edges[counter] == null) {
                return counter;
            }
        }
        return counter;
    }

}

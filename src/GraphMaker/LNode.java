package GraphMaker;

import java.util.ArrayList;
import java.util.List;

public class LNode {

    List<LEdge> edges = new ArrayList<LEdge>();
    String id;

    public LNode(int id) {
        this.id = "Node: "+id;
    }

    ///////// PUBLIC ////////

    public List<LEdge> getEdges(){
        return edges;
    }

    public void setEdge(LEdge lEdge){
        checkEdgeArray();
        int placement = checkAvailability();
        edges.add(lEdge);
    }

    public boolean equals(LNode lNode){
        if (lNode ==null) return false;
        return this.id.equals(lNode.id);
    }

    @Override
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
        if (counter>=edges.size()/2){
            this.edges = new ArrayList<LEdge>(edges);
        }
    }

    private int checkAvailability(){
        int counter;
        for (counter=0; counter < edges.size(); counter++) {
            if (edges.get(counter) == null) {
                return counter;
            }
        }
        return counter;
    }

}

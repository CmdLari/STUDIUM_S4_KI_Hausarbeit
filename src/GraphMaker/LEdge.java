package GraphMaker;

import java.util.Random;

public class LEdge {
    LNode[] nodes;
    int cost;
    String id;
    boolean isSet;
    boolean inWinningRoute;


    public LEdge(int id, int MAXCOST) {
        this.nodes = new LNode[2];
        this.cost = 0;
        this.id = "Edge: "+id;
        Random rand = new Random();
        this.cost = rand.nextInt(1, MAXCOST);
        this.isSet = false;
        this.inWinningRoute = false;
    }

    ///////// PUBLIC ////////

    public String getID() {
        return id;
    }

    public LNode[] getNodes() {
        return nodes;
    }

    public void addNode(LNode node) {
        int placement = checkAvailability();
        if (!(placement == 99)) {
            nodes[placement] = node;
        }
    }

    public boolean Leguals(LEdge lEdge) {
        if (lEdge==null){
            return false;
        }
        return this.id.equals(lEdge.id);
    }

    public boolean isSet() {
        return isSet;
    }

    public String toString(){
        return id+"(cost: "+cost+")";
    }

    public void isWinningRoute(Boolean bool) {
        this.inWinningRoute = bool;
    }

    public boolean getisInWinningRoute(){
        return inWinningRoute;
    }

    public int getCost(){
        return cost;
    }

    ///////// PRIVATE ///////

    private int checkAvailability() {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                return i;
            }
        }
        return 99;
    }
}

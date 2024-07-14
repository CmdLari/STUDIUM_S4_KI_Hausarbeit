package GraphMaker;

import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class LEdge {
    List<LNode> nodes;
    int cost;
    String id;
    boolean isSet;
    boolean inWinningRoute;
    boolean hasBeenTried; // New attribute

    public LEdge(int id, int MAXCOST, LNode zero, LNode one) {
        this.nodes = new ArrayList<LNode>();
        this.nodes.add(zero);
        this.nodes.add(one);
        this.cost = 0;
        this.id = "Edge: " + id;
        Random rand = new Random();
        this.cost = rand.nextInt(0, MAXCOST);
        this.isSet = false;
        this.inWinningRoute = false;
        this.hasBeenTried = false; // For visualisation only
    }

    ///////// PUBLIC ////////

    public String getID() {
        return id;
    }

    public List<LNode> getNodes() {
        return nodes;
    }

    public void addNode(LNode node) {
        nodes.add(node);
    }


    public boolean Leguals(LEdge lEdge) {
        if (lEdge == null) {
            return false;
        }
        return this.id.equals(lEdge.id);
    }

    @Override
    public String toString() {
        return id + "(cost: " + cost + ")";
    }

    public void isWinningRoute(Boolean bool) {
        this.inWinningRoute = bool;
    }

    public boolean getisInWinningRoute() {
        return inWinningRoute;
    }

    public int getCost() {
        return cost;
    }

    public void markAsTried() {
        this.hasBeenTried = true;
    }

    public boolean hasBeenTried() {
        return hasBeenTried;
    }

    ///////// PRIVATE ///////
}

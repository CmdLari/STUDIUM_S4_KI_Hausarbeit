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
        this.id = "Edge: " + id;
        Random rand = new Random();
        this.cost = rand.nextInt(1, MAXCOST+1);
        this.isSet = false;
        this.inWinningRoute = false;
        this.hasBeenTried = false; // For visualisation only
    }

    ///////// PUBLIC ////////

    public List<LNode> getNodes() {
        return nodes;
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

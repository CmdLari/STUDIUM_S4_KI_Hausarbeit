package GraphMaker;

import java.util.Random;

public class LEdge {
    LNode leftNode;
    LNode rightNode;
    LNode[] nodes = new LNode[2];
    int cost = 0;
    public boolean hasBeenWalked = false;


    public LEdge(int MAXEDGECOST){
        Random rand = new Random();
        this.cost = rand.nextInt(1, MAXEDGECOST);
    };

    public LNode getLeftNode(){
        return leftNode;
    };

    public LNode getRightNode(){
        return rightNode;
    };

    public int getCost(){
        return cost;
    }

    public LNode[] getNodes(){
        return nodes;
    };

    public void  setLeftNode(LNode leftNode) {
        this.leftNode = leftNode;
        nodes[0] = leftNode;
    }
    public void setRightNode(LNode rightNode) {
        this.rightNode = rightNode;
        nodes[1] = rightNode;
    }
}

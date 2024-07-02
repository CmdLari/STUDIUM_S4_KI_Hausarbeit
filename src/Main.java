import GraphMaker.LGraph;

public class Main {
    public static void main(String[] args) {

        int MAXNODES = 15;
        int MAXEDGES = 50;
        int MAXEDGECOST = 5;

        LGraph lGraph = new LGraph(MAXNODES, MAXEDGES, MAXEDGECOST);
        lGraph.visualizeGraph();    }
}

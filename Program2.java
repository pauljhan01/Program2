/*
 * Name: Paul J. Han
 * EID: pjh2235
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;

public class Program2 implements ProgramTwoInterface {

    public void relaxDistance(HeapInterface heap, City min, City neighbor, int i){
        int dist = min.getMinDist() + min.getWeights().get(i);
        if(dist < neighbor.getMinDist()){
            heap.changeKey(neighbor, dist);
        }
    }

    public void relaxLength(HeapInterface heap, City min, City neighbor, int index){
        int weight_of_edge = min.getWeights().get(index);
        if(heap.toArrayList().contains(neighbor) && weight_of_edge < neighbor.getMinDist()){
            heap.changeKey(neighbor,min.getWeights().get(index));
        }
    }
    
    
    // additional constructor fields may be added, but don't delete or modify anything already here


    /**
     * findMinimumRouteDistance(Problem problem)
     *
     *  @param problem - the problem to solve.
     * 
     *  @param problem  - contains the cities, start, dest, and heap.
     * 
     * @return the minimum distance possible to get from start to dest.
     * Assume the given graph is always connected.
     */
    public int findMinimumRouteDistance(Problem problem) {

        //various variable declarations for readability
        int neighbor_name = 0;
        int start_node_name = 0;

        // Some code to get you started

        City start = problem.getStart();
        City min = null;
        City dest = problem.getDest();
        start.setMinDist(0);

        start_node_name = start.getName();

        HeapInterface heap = problem.getHeap();     // get the heap
        heap.buildHeap(problem.getCities());        // build the heap



        //we will be extracting the min from the heap so the size of the ArrayList will keep
        //decreasing
        while(heap.toArrayList().size() > 0){
            min = heap.extractMin();
            for(int i = 0; i < min.getNeighbors().size(); i++){
                relaxDistance(heap, min, min.getNeighbors().get(i), i);
            }
        }

        return dest.getMinDist();
    }

    /**
     * findMinimumLength()
     *
     * @return The minimum total optical line length required to connect (span) each city on the given graph.
     * Assume the given graph is always connected.
     */
    public int findMinimumLength(Problem problem) {
        // TODO: implement this function

        int totalDist = 0;

        City min = null;

        HeapInterface heap = problem.getHeap();     // get the heap
        heap.buildHeap(problem.getCities());        // build the heap

        heap.toArrayList().get(0).setMinDist(0); //set the key of the first node to 0

        while(heap.toArrayList().size() > 0){
            min = heap.extractMin();

            totalDist += min.getMinDist();

            for(int i = 0; i < min.getNeighbors().size(); i++){
                relaxLength(heap, min, min.getNeighbors().get(i), i);
            }
        }
        return totalDist;
    }

    //returns edges and weights in a string.
    public String toString(Problem problem){ 
        String o = "";
        for (City v : problem.getCities()) {
            boolean first = true;
            o += "City ";
            o += v.getName();
            o += " has neighbors ";
            ArrayList<City> ngbr = v.getNeighbors();
            for (City n : ngbr) {
                o += first ? n.getName() : ", " + n.getName();
                first = false;
            }
            first = true;
            o += " with distances ";
            ArrayList<Integer> wght = v.getWeights();
            for (Integer i : wght) {
                o += first ? i : ", " + i;
                first = false;
            }
            o += System.getProperty("line.separator");

        }

        return o;
    }

}

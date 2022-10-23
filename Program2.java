/*
 * Name: Paul J. Han
 * EID: pjh2235
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;

public class Program2 implements ProgramTwoInterface {
    
    
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

        // Some code to get you started
        City start = problem.getStart();
        City dest = problem.getDest();
        start.setMinDist(0);

        HeapInterface heap = problem.getHeap();     // get the heap
        heap.buildHeap(problem.getCities());        // build the heap

        // TODO: implement this function



        return -1;
    }

    /**
     * findMinimumLength()
     *
     * @return The minimum total optical line length required to connect (span) each city on the given graph.
     * Assume the given graph is always connected.
     */
    public int findMinimumLength(Problem problem) {
        // TODO: implement this function
        return -1;
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

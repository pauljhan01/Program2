/*
 * Name: <Paul J. Han>
 * EID: <pjh2235>
 */

// Implement your heap here
// Private methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;
import java.util.Iterator;

public class Heap implements HeapInterface {
    private ArrayList<City> minHeap;

    public Heap() {
        minHeap = new ArrayList<City>();
    }
    
    public void HeapifyUp(ArrayList<City> minHeap, int current_index){

        //if the current index is 0, we are at the root so we need not heapify up further
        if(current_index == 0){
            return;
        }

        //various variable declarations for the sake of readability
        int parent_index = (current_index-1)/2;
        
        int parent_name = minHeap.get(parent_index).getName();
        int current_name = minHeap.get(current_index).getName();

        City parent_city = minHeap.get(parent_index);
        City current_city = minHeap.get(current_index);

        int parent_key = minHeap.get(parent_index).getMinDist();
        int current_key = minHeap.get(current_index).getMinDist();

        //first we compare the key of the parent with the current key
        //if the parent is greater than the child, we do not have a heap so we need to switch
        //otherwise, we do nothing
        if(parent_key > current_key){
            minHeap.set(parent_index, current_city);
            minHeap.set(current_index, parent_city);

            //we also need to set the index field in the City class

            parent_city.setIndex(current_index);
            current_city.setIndex(parent_index);
            HeapifyUp(minHeap, parent_index);
        }
        //documentation states that ties in the key are broken by the name which is an integer

        else if(parent_key == current_key){
            //the lowest name value becomes the parent
            if(parent_name > current_name){
                minHeap.set(parent_index, current_city);
                minHeap.set(current_index, parent_city);

                //we must also change the index field in the City class

                parent_city.setIndex(current_index);
                current_city.setIndex(parent_index);
                HeapifyUp(minHeap, parent_index);
            }
        }
    }
    
    public void HeapifyDown(ArrayList<City> minHeap, int current_index){
        //index variable declarations
        int left_child_index = 0;
        int right_child_index = 0;

        City min_child = null;
        int min_child_key = 0;
        int min_child_name = 0;
        int min_child_index = 0;

        //for readability
        City current_city = null;
        City left_child = null;
        City right_child = null;

        int left_child_key = 0;
        int right_child_key = 0;
        int current_key = 0;

        int right_child_name = 0;
        int left_child_name = 0;
        int current_name = 0;

        left_child_index = 2*current_index+1; 
        right_child_index = 2*current_index+2; 
        
        if(left_child_index > minHeap.size()-1){
            return;
        }
        if(left_child_index == minHeap.size()-1){
            min_child_index = left_child_index;

            //to improve readability

            min_child = minHeap.get(min_child_index);
            min_child_key = minHeap.get(min_child_index).getMinDist();
            min_child_name = minHeap.get(min_child_index).getName();

            current_city = minHeap.get(current_index);
            current_key = minHeap.get(current_index).getMinDist();
            current_name = minHeap.get(current_index).getName();

            /*if the key of the child is smaller than the parent, we do not have a heap so we need to
            * switch the parent and the child. 
            */
            if(min_child_key < current_key){
                minHeap.set(min_child_index, current_city);
                minHeap.set(current_index, min_child);

                //we also want to change the index inside the City objects when we switch

                current_city.setIndex(min_child_index);
                min_child.setIndex(current_index);
                HeapifyDown(minHeap, min_child_index);
            }
            //however, if the smallest child has the same key as the parent then we break the tie
            //using the integer name
            else if(min_child_key == current_key){
                //documentation dicates that all ties are broken using the name. the smallest name
                //is made the parent
                if(min_child_name < current_name){
                    minHeap.set(min_child_index, current_city);
                    minHeap.set(current_index, min_child);

                    //we also want to change the index inside the City objects when we switch

                    current_city.setIndex(min_child_index);
                    min_child.setIndex(current_index);
                    HeapifyDown(minHeap, min_child_index);
                }
            }
        }

        //if the left child index is less than the size of the heap -1, i.e we have two children
        //of the current node then we must compare their values to see which one is smaller
        //and if necessary also compare their names to find the smallest one

        else if(left_child_index < minHeap.size()-1){

            //for readability

            current_city = minHeap.get(current_index);
            left_child = minHeap.get(left_child_index);
            right_child = minHeap.get(right_child_index);

            left_child_key = minHeap.get(left_child_index).getMinDist();
            right_child_key = minHeap.get(right_child_index).getMinDist();
            current_key = minHeap.get(current_index).getMinDist();

            right_child_name = minHeap.get(right_child_index).getName();
            left_child_name = minHeap.get(left_child_index).getName();
            current_name = minHeap.get(current_index).getName();

            //we want to find the smallest child of the current node
            if(left_child_key < right_child_key){
                min_child_index = left_child_index;
                min_child = left_child;
            }
            else if(right_child_key < left_child_key){
                min_child_index = right_child_index;
                min_child = right_child;
            }
            //documentation dictates that all ties are broken by comparing the "name" of the node
            //the smaller "name" will be the minimum child
            else if(right_child_key == left_child_key){
                if(right_child_name < left_child_name){
                    min_child_index = right_child_index;
                    min_child = right_child;
                }
                else{
                    min_child_index = left_child_index;
                    min_child = left_child;
                }
            }

            //to improve readability
            min_child = minHeap.get(min_child_index);
            min_child_key = minHeap.get(min_child_index).getMinDist();
            min_child_name = minHeap.get(min_child_index).getName();

            /*if the key of the child is smaller than the parent, we do not have a heap so we need to
            * switch the parent and the child. 
            */
            if(min_child_key < current_key){
                minHeap.set(min_child_index, current_city);
                minHeap.set(current_index, min_child);

                //we also want to change the index inside the City objects when we switch

                current_city.setIndex(min_child_index);
                min_child.setIndex(current_index);
                HeapifyDown(minHeap, min_child_index);
            }
            //however, if the smallest child has the same key as the parent then we break the tie
            //using the integer name
            else if(min_child_key == current_key){
                //documentation dicates that all ties are broken using the name. the smallest name
                //is made the parent
                if(min_child_name < current_name){
                    minHeap.set(min_child_index, current_city);
                    minHeap.set(current_index, min_child);

                    //we also want to change the index inside the City objects when we switch

                    current_city.setIndex(min_child_index);
                    min_child.setIndex(current_index);
                    HeapifyDown(minHeap, min_child_index);
                }
            }
        }
        

        
    }

    /**
     * buildHeap(ArrayList<City> cities)
     * Given an ArrayList of Cities, build a min-heap keyed on each City's minDist
     * Time Complexity - O(nlog(n)) or O(n)
     * 
     * Should assign cities to the minHeap instance variable
     *
     *
     * @param cities
     */
    public void buildHeap(ArrayList<City> cities) {
        minHeap = cities;
        int start_index;
        //first, we have an unsorted array of cities

        /* we will be creating a heap in O(n) time
         * this algorithm assumes that the lowest level of the heap, the level with the most
         * children already creates a heap. we will then go to the level above and heapify down
         * as needed. this repeats until the root node is processed. a heapify down function will
         * be created.
         */

        //we begin at the node last non-leaf node
        //this is because the first non-leaf node would be the root
        //we then go in reverse order until we hit the node.

        //there is an edge case where if there is one element on the heap, start_index is -1
        //this crashes the program so we use a ternary operator because i hate if else statements
        start_index = (minHeap.size() == 1 ? 0 : ((minHeap.size()/2)-1));
        HeapifyDown(minHeap, start_index);
    }

    /**
     * insertNode(City in)
     * Insert a City into the heap.
     * Time Complexity - O(log(n))
     *
     * @param in - the City to insert.
     */
    public void insertNode(City in) {
        //we add the city to the end of the heap and heapify up
        minHeap.add(in);
        int last_element_index = minHeap.size()-1;
        HeapifyUp(minHeap, last_element_index);
    }

    /**
     * findMin()
     * Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */
    public City findMin() {
        //return the first element since the smallest element is the first
        return minHeap.get(0); 
    }

    /**
     * extractMin()
     * Time Complexity - O(log(n))
     *
     * @return the minimum element of the heap, AND removes the element from said heap.
     */
    public City extractMin() {

        //first element is the minimum element in a minHeap, we store this value in a variable
        //so that we can save a reference to the minimum element before it gets overridden
        
        City minimum_element = minHeap.get(0);

        //call the delete function to remove the node
        delete(0);

        return minimum_element;
    }

    /**
     * delete(int index)
     * Deletes an element in the min-heap given an index to delete at.
     * Time Complexity - O(log(n))
     *
     * @param index - the index of the item to be deleted in the min-heap.
     */
    public void delete(int index) {
        // first, we switch the node we want to delete with the last node in the heap

        //for the sake of readability, various variable declarations
        int last_element_index = minHeap.size()-1; 

        City last_city = minHeap.get(last_element_index);
        City current_city = minHeap.get(index);

        minHeap.set(last_element_index, current_city);
        minHeap.set(index, last_city);

        //next we need to change the index field in each of the switched Cities

        current_city.setIndex(last_element_index);

        last_city.setIndex(index);

        //we then delete the last index which will contain the element that we want to delete

        minHeap.remove(last_element_index);

        //since the size has changed, we must adjust our last element index by 1;

        last_element_index -= 1;

        //we then heapify down starting at the index of the deleted node 

        HeapifyDown(minHeap, index);

    }

    /**
     * changeKey(City r, int newDist)
     * Changes minDist of City s to newDist and updates the heap.
     * Time Complexity - O(log(n))
     *
     * @param r       - the City in the heap that needs to be updated.
     * @param newDist - the new cost of City r in the heap (note that the heap is keyed on the values of minDist)
     */
    public void changeKey(City r, int newDist) {
        /* 
         * The City class contains a field called index which is the location in the heap
         * where the city lies. Using this index, we can update the key and sort the heap again
         */

        //various variable declarations
        int current_index = r.getIndex();

        //set the city reference's minimum distance in the heap to the value specified by the parameter
        minHeap.get(current_index).setMinDist(newDist);

        //how to sort the heap again?
        //various variable declarations, for sake of readability
        int parent_index = (current_index-1)/2;
        if(current_index == 1){
            parent_index = 0;
        }
        int left_child_index = 2*current_index+1;
        int right_child_index = 2*current_index+2;
        int current_key = newDist;

        /*
         * if the left child index of the current node is greater than the size of the heap
         * the current node is a leaf node and we heapify up however many times necessary
         * to create a heap.
         * 
         * since we can assume that minHeap is an almost heap then all we need to do is find
         * out if the parent is larger than the current node or if the children are smaller
         * than the current node. This determines if we heapify up or down.
         */
        
        if(left_child_index > minHeap.size()){
            HeapifyUp(minHeap, current_index);
        }else{
            int parent_key = minHeap.get(parent_index).getMinDist();

            //what should I do if the left child key is equal to the current node key
            //and the right child key is less than the current node key. This algorithm
            //relies on future knowledge. Need to do tomorrow, I am too tired to finish this
            //right now

            //if the parent key is greater than the current key, we do not have a min heap
            //so we call HeapifyUp
            if(parent_key > current_key){
                HeapifyUp(minHeap, current_index);
            }
            else{
                //HeapifyDown already covers all the cases involving keys so we call heapify down
                HeapifyDown(minHeap, current_index);
            }
        }
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < minHeap.size(); i++) {
            output += minHeap.get(i).getName() + " ";
        }
        return output;
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public ArrayList<City> toArrayList() {
        return minHeap;
    }
}

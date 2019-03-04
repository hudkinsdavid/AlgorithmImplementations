package threadsort;

import java.util.*;

//Create a thread and perform a Merge Sort on the specified half of the array
public class NewThread extends Thread{
    //Declare Variables
    private int[] myArray;
    private int[] tempArray;
    private int[] helpArray;
    
    //Constructor
    NewThread(int[] arr, int location){
       tempArray = arr;
       myArray = new int[arr.length/2];
       helpArray = new int[arr.length/2];
       
       if(location == 0){
           System.arraycopy(tempArray, 0, myArray, 0, arr.length/2);
           System.arraycopy(tempArray, 0, helpArray, 0, arr.length/2);             
       }else{
           System.arraycopy(tempArray, arr.length/2, myArray, 0, arr.length/2);
           System.arraycopy(tempArray, arr.length/2, helpArray, 0, arr.length/2);
       }
    }
    
    //METHOD - Return the sorted array 
    public int[] returnArr(){
        return myArray;
    }
    
    //METHOD - Perform the split for merge sort
    public void mergeSplit(int lowIndex, int highIndex){
        //Continuously split until down to one element
        if(lowIndex < highIndex){
            //Identify the middle indice of the array
            int middle = lowIndex + (highIndex - lowIndex)/2;
            
            //Split the array into left and right portions
            mergeSplit(lowIndex, middle);
            mergeSplit(middle + 1, highIndex);
            
            //Merge the arrays and perform comparisons
            mergeSort(lowIndex, middle, highIndex);
        }
    }
    
    //METHOD - Perform the sort for merge sort
    public void mergeSort(int lowIndex, int middleIndex, int highIndex){
        //copy the primary array into the helper array
        for(int i = lowIndex; i<= highIndex; i++){
            helpArray[i] = myArray[i];
        }
        
        //Initialize the indices
        int mergeIndex = lowIndex;
        int leftIndex  = lowIndex;
        int rightIndex = middleIndex + 1;
        
        //Perform the merge sort on the entirety of the two splits
        while(leftIndex <= middleIndex && rightIndex <= highIndex){
            if(helpArray[leftIndex] <= helpArray[rightIndex]){
                myArray[mergeIndex] = helpArray[leftIndex];
                leftIndex++;
            }else{
                myArray[mergeIndex] = helpArray[rightIndex];
                rightIndex++;
            }
            mergeIndex++;
        }
        while(leftIndex <= middleIndex){
            myArray[mergeIndex] = helpArray[leftIndex];
            leftIndex++;
            mergeIndex++;
        }
    }
    
    //METHOD - Start thread
    @Override
    public void run(){
        mergeSplit(0, myArray.length-1);
    }
}
package threadsort;

public class MergeThread extends Thread{
    int[] myArray;
    int[] leftArray;
    int[] rightArray;
    
    MergeThread(int[] left, int[] right){
        leftArray  = left;
        rightArray = right;
    }
    
    public void mergeArrays(){
        int arraySize = leftArray.length + rightArray.length;
        myArray = new int[arraySize];
        
        //Initialize the indices
        int middle     = (leftArray.length + rightArray.length)/2;
        int mergeIndex = 0;
        int leftIndex  = 0;
        int rightIndex = 0;
        
        //Perform the merge sort on the entirety of the two splits
        while(leftIndex < leftArray.length  && rightIndex < rightArray.length){
            if(leftArray[leftIndex] <= rightArray[rightIndex]){
                myArray[mergeIndex] = leftArray[leftIndex];
                leftIndex++;
            }else{
                myArray[mergeIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            mergeIndex++;
        }
        
        if(leftIndex == leftArray.length-1){
            while(rightIndex < rightArray.length){
                myArray[mergeIndex] = rightArray[rightIndex];
                rightIndex++;
                mergeIndex++;
            }
        }else{
            while(leftIndex < leftArray.length){
                myArray[mergeIndex] = leftArray[leftIndex];
                leftIndex++;
                mergeIndex++;
            }
        }
    }
    
    public int[] returnArr(){
        return myArray;      
    }
    
    @Override
    public void run(){
        mergeArrays();
    }
}

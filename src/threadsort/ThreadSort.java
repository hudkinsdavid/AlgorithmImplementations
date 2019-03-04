package threadsort;

public class ThreadSort {
    //Create flags for left and right parts of the array 
    private static final int LEFT  = 0;
    private static final int RIGHT = 1;
    private static int sort = 0;
    
    public static void printArr(int[] arr){
        switch (sort) {
            case 0:
                System.out.println("The original array:");
                for(int i=0;i<arr.length;i++){
                    System.out.print(arr[i] + " ");
                }   break;
            case 1:
                System.out.println("\nArray sorted on a thread:");
                for(int i=0;i<arr.length;i++){
                    System.out.print(arr[i] + " ");
                }   break;
            default:
                System.out.println("\nThreads merged:");
                for(int i=0;i<arr.length;i++){
                    System.out.print(arr[i] + " ");
                }       break;
        }
    }
        
    public static void main(String[] args) {
        //Create and initialize the array of numbers to perform thread sorting on 
        int[] number = {7,12,19,3,18,4,2,6,15,8};
        printArr(number);
        //Create and start the threads that sort the array halves
        NewThread leftThread  = new NewThread(number,LEFT);
        NewThread rightThread = new NewThread(number,RIGHT);
        
        try{
            leftThread.start();
            leftThread.join();
            rightThread.start();
            rightThread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        //Merge the two other threads into one array on the new thread
        MergeThread mergeIt   = new MergeThread(leftThread.returnArr(), 
                rightThread.returnArr());
        
        try{
            mergeIt.start();
            mergeIt.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        sort = 1;
        printArr(leftThread.returnArr());
        printArr(rightThread.returnArr());
        sort = 2;
        printArr(mergeIt.returnArr());
    }
}

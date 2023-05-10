// You can (and should) add "implements Partitioner" below once you have the implementation ready
// Code used from https://www.geeksforgeeks.org/quicksort-using-random-pivoting/
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class WebPartitioner implements Partitioner{

    // This Function helps in calculating
    // random numbers between low(inclusive)
    // and high(inclusive)
    static void random(String[] str,int low,int high)
    {
        high -= 1; //Account for our exclusive high input
        Random rand= new Random();
        int pivot = rand.nextInt(high-low)+low;
         
        String temp1=str[pivot]; 
        str[pivot]=str[high];
        str[high]=temp1;
    }

    /* This function takes last element as pivot,
    places the pivot element at its correct
    position in sorted stray, and places all
    smaller (smaller than pivot) to left of
    pivot and all greater elements to right
    of pivot */
    public int partition(String[] str, int low, int high)
    {
        high -= 1;
        // pivot is chosen randomly
        random(str,low,high);
        String pivot = str[high];
     
 
        int i = (low-1); // index of smaller element
        for (int j = low; j < high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (str[j].compareToIgnoreCase(pivot) <= 0)
            {
                i++;
 
                // swap str[i] and str[j]
                String temp = str[i];
                str[i] = str[j];
                str[j] = temp;
            }
        }
 
        // swap str[i+1] and str[high] (or pivot)
        String temp = str[i+1];
        str[i+1] = str[high];
        str[high] = temp;
 
        return i+1;
    }
}

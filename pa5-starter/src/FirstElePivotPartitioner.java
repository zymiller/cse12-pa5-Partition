// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class FirstElePivotPartitioner implements Partitioner{
    public int partition(String[] str, int low, int high) {
        String pivotData = str[low];
        int partitionEnd = findPartitionEnd(str, pivotData, low, high);
        int i = partitionEnd - 1;
        while (i > low) {
            if (str[i].compareToIgnoreCase(pivotData) > 0) {
                String newHigh = str[i];            
                str[i] = str[partitionEnd];
                str[partitionEnd] = newHigh;
                System.out.println("pEnd: " + partitionEnd);
                partitionEnd--;
                System.out.println("i: " + i);
            }
            if (partitionEnd == 0) {
                break;
            }
            i--;
        }
        str[0] = str[partitionEnd];
        str[partitionEnd] = pivotData;
        
        return partitionEnd;
    }

    public int findPartitionEnd(String[] str, String pivotData, int low, int high) {
        for (int i = high - 1; i >= low; i--) {
            if (str[i].compareToIgnoreCase(pivotData) <= 0) {
                return i;
            }
        }
        return 0;
    }
}

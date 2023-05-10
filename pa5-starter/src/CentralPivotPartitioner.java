// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class CentralPivotPartitioner implements Partitioner{

    public int partition(String[] str, int low, int high) {
        int pivotPos;
        if ((high - low)% 2 == 0) {
            pivotPos = (high - low)/2;
        } else {
            pivotPos = (high - low - 1)/2;
        }
        String pivotData = str[pivotPos];        
        int partitionEnd = findPartitionEnd(str, pivotPos, low, high);
        System.out.println(partitionEnd);
        for (int i = partitionEnd - 1; i >= low; i--) {
            System.out.println("Looking at: " + str[i] + i);
            if (i == pivotPos) {
                continue;
            }else if (partitionEnd == pivotPos) {
                partitionEnd--;
            }
            if (str[i].compareToIgnoreCase(pivotData) > 0) {
                String newHigh = str[i];
                System.out.println("Partition: " + str[partitionEnd] + partitionEnd);
                str[i] = str[partitionEnd];
                str[partitionEnd] = newHigh;
                System.out.println("Swapping: " + partitionEnd);
                partitionEnd--;
            }
        }

        System.out.println(partitionEnd);
        System.out.println(str[pivotPos]);
        if (str[partitionEnd].compareToIgnoreCase(pivotData) <= 0 && partitionEnd < pivotPos) {
                partitionEnd += 1;
        } else if (str[partitionEnd].compareToIgnoreCase(pivotData) > 0 && partitionEnd > pivotPos) {
            partitionEnd -= 1;
        }
        System.out.println(str[partitionEnd]);
        str[pivotPos] = str[partitionEnd];
        str[partitionEnd] = pivotData;
        return partitionEnd;
    }

    public int findPartitionEnd(String[] str, int pivotPos, int low, int high) {
        for (int i = high - 1; i >= low; i--) {
            if (str[i].compareToIgnoreCase(str[pivotPos]) <= 0 && i != pivotPos) {
                return i;
            }
        }
        return 0;
    }

}


// These are some imports that the course staff found useful, feel free to use them
// along with other imports from java.util.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PartitionOracle {

    /**
     * Feel free to use this method to call partition. It catches any exceptions or
     * errors and returns a definitely-invalid pivot (-1) to turn errors into
     * potential failures. For example, in testPartition, you may use
     * 
     * runPartition(p, someArray, someLow, someHigh)
     * 
     * instead of
     * 
     * p.partition(someArray, someLow, someHigh)
     * 
     * @param p
     * @param strs
     * @param low
     * @param high
     * @return
     */
    public static int runPartition(Partitioner p, String[] strs, int low, int high) {
        try {
            return p.partition(strs, low, high);
        } catch (Throwable t) {
            return -1;
        }
    }

    // The three methods below are for you to fill in according to the PA writeup.
    // Feel free to make other helper methods as needed.

    public static String isValidPartitionResult(String[] before, int low, int high, int pivot, String[] after) {
        if (before.length != after.length) {
            if (before.length > after.length) {
                return "Before array is larger than after";
            } else {
                return "After array is larger than before";
            }

        } 
        List<String> data = new ArrayList<String>(Arrays.asList(before));
        for (int i = low; i < high; i++) {
            int dataPos = data.indexOf(after[i]);
            if (dataPos == -1) {
                return "Elements from before array are not present in after";
            } else {
                data.remove(dataPos);
            }
        }

        for (int i = low; i < pivot; i++) {
            if (after[i].compareToIgnoreCase(after[pivot]) >= 0) {
                return "Not all elements before pivot sorted correctly";
            }
        }

        for (int i = pivot + 1; i < high; i++) {
            if (after[i].compareToIgnoreCase(after[pivot]) < 0) {
                return "Not all elements after pivot sorted correctly";
            }
        }

        if (low > 0) {
            for (int i = 0; i < low; i++) {
                if (before[i] != after[i]) {
                    return "Elements before partition array were sorted";
                }
            }
        }

        if (high < after.length) {
            for (int i = high; i < after.length; i++) {
                if (before[i] != after[i]) {
                    return "Elements after partition array were sorted";
                }
            }
        }

        return null;
    }

    public static String[] generateInput(int n) {
        Random rn = new Random();
        String[] generatedArray = new String[n];
        for (int i = 0; i < n; i++) {
            int letterNum = rn.nextInt(26) + 97;
            String letter = String.valueOf((char)letterNum);
            generatedArray[i] = letter;
        }
        return generatedArray;
    }

    public static CounterExample findCounterExample(Partitioner p) {
        Random rn = new Random();
        int test1Length = rn.nextInt(5) + 8;
        //int test1Length = 10;
        String[]testBefore = generateInput(test1Length);
        //String[]testBefore = {"n", "o", "t", "a", "b", "b", "f", "z", "r", "k"};
        System.out.println(Arrays.toString(testBefore));
        String[]testAfter = testBefore.clone();
        int testPivot = p.partition(testAfter, 0, test1Length);
        System.out.println(Arrays.toString(testAfter));
        String testValidity = isValidPartitionResult(testBefore, 0, test1Length, testPivot, testAfter);
        if (testValidity == null) {
            return null;
        } else {
            CounterExample output = new CounterExample(testBefore, 0, test1Length, testPivot, testAfter, testValidity);
            return output;
        }
    }

}

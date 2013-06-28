import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * The class <code>Solver</code> is an implementation of a greedy algorithm to solve the knapsack problem.
 */
public class Solver {

    /**
     * The main class
     */
    public static void main(String[] args) {
        try {
            solve(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the instance, solve it, and print the solution in the standard output
     */
    public static void solve(String[] args) throws IOException {
        String fileName = resolveFileName(args);
        if (fileName == null) return;

        // read the lines out of the file
        Parser parser = new Parser(fileName);
        int numberOfItems = parser.getNumberOfItems();
        int capacity = parser.getCapacity();

        Item items[] = parser.getItems();
        printItems(items);

        // a trivial greedy algorithm for filling the knapsack
        // it takes items in-order until the knapsack is full
        boolean selectedItems[] = greedySelection(items, capacity);


        // prepare the solution in the specified output format
//        System.out.println(value + " 0");
        printSelectedItems(selectedItems, items);
    }

    private static void printItems(Item[] items) {
        for (Item item : items) {
            System.out.println(item);
        }
    }

    private static void printSelectedItems(boolean[] taken, Item items[]) {
        int value = 0;
        for (int i = 0; i < taken.length; i++) {
            System.out.println("[" + i + "] = " + taken[i] + " ");
            if (taken[i])
                value += items[i].getValue();
        }
        System.out.println("value = " + value);
    }

    private static boolean[] densityBasedGreedySelection(Item items[], int capacity) {
        int value = 0;
        int weight = 0;
        boolean[] itemsTaken = new boolean[items.length];
        Arrays.sort(items, new Comparator<Item>() {
            /**
             * Dense item first
             * @param item
             * @param item2
             * @return
             */
            @Override
            public int compare(Item item, Item item2) {
                return ComparisonChain.start().compare(item.getDensity(), item2.getDensity(), Ordering.natural().reverse()).result();
            }
        });

        for (int i = 0; i < items.length; i++) {
            if (weight + items[i].getWeight() < capacity) {
                itemsTaken[i] = true;
            }
        }

        return itemsTaken;
    }

    private static boolean[] greedySelection(Item items[], int capacity) {
        int value = 0;
        int weight = 0;
        boolean[] itemsTaken = new boolean[items.length];
        for (int i = 0; i < items.length; i++) {
            if (weight + items[i].getWeight() <= capacity) {
                itemsTaken[i] = true;
                value += items[i].getValue();
                weight += items[i].getWeight();
            } else {
                itemsTaken[i] = false;
            }
        }

        return itemsTaken;
    }

    private static String resolveFileName(String[] args) {
        String fileName = null;

        // get the temp file name
        for (String arg : args) {
            if (arg.startsWith("-file=")) {
                fileName = arg.substring(6);
            }
        }
        if (fileName == null)
            return null;
        return fileName;
    }


}



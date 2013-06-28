import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
        int capacity = parser.getCapacity();
        System.out.println("capacity = " + capacity);

        Item items[] = parser.getItems();
        printItems(items);

        // a trivial greedy algorithm for filling the knapsack
        // it takes items in-order until the knapsack is full
        List<Item> baggedItems = greedySelection(items, capacity);
        System.out.println("greedy....");
        // prepare the solution in the specified output format
        printSelectedItems(baggedItems);
        System.out.println("density based....");
        baggedItems = densityBasedGreedySelection(Arrays.copyOf(items, items.length), capacity);

        printSelectedItems(baggedItems);
    }

    private static void printItems(Item[] items) {
        System.out.println();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    private static void printSelectedItems(List<Item> items) {
        int totalValue = 0;
        int totalWeight= 0;
        System.out.println();
        for (Item item : items) {
            totalValue += item.getValue();
            totalWeight += item.getWeight();
            System.out.println("Item in the Bag " + item);
        }
        System.out.println("Total value = " + totalValue);
        System.out.println("Total Weight= " + totalWeight);
    }

    private static List<Item> densityBasedGreedySelection(Item items[], int capacity) {
        int weight = 0;
        List<Item> itemsTaken = new ArrayList<Item>();
        Arrays.sort(items, new Comparator<Item>() {
            /**
             * Dense item first
             * @param item
             * @param item2
             * @return
             */
            @Override
            public int compare(Item item, Item item2) {
                return ComparisonChain.start().compare(item.getDensity(), item2.getDensity(), Ordering.natural().reverse()).compare(item.getValue(),item2.getValue(),Ordering.natural().reverse())
                        .compare(item.getWeight(),item2.getWeight()).result();
            }
        });

//        printItems(items);

        for (Item item : items) {
            if (weight + item.getWeight() <= capacity) {
                itemsTaken.add(item);
                weight += item.getWeight();
            }
        }

        return itemsTaken;
    }

    private static List<Item> greedySelection(Item items[], int capacity) {

        int weight = 0;
        List<Item> itemsTaken = new ArrayList<Item>();
        for (Item item : items) {
            if (weight + item.getWeight() <= capacity) {
                itemsTaken.add(item);
                weight += item.getWeight();
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



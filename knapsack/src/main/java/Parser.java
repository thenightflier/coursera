import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: usman
 * Date: 28/06/13
 * Time: 10:43
 * To change this template use File | Settings | File Templates.
 */
public class Parser {

    private final String fileName;
    private int numberOfItems;
    private int capacity;
    private Item[] items;

    public Parser(String fileName) throws IOException {
        this.fileName = fileName;
        parse();
    }

    String getFileName() {
        return fileName;
    }

    int getNumberOfItems() {
        return numberOfItems;
    }

    int getCapacity() {
        return capacity;
    }

    Item[] getItems() {
        return items;
    }

    // parse the data in the file
    private void parse() throws IOException {
        List<String> lines = getLines(fileName);
        String[] firstLine = lines.get(0).split("\\s+");
        numberOfItems = Integer.parseInt(firstLine[0]);
        capacity = Integer.parseInt(firstLine[1]);
        items = new Item[numberOfItems];

        for (int i = 0; i < numberOfItems; i++) {
            String line = lines.get(i);
            String[] parts = line.split("\\s+");
            int value = Integer.parseInt(parts[0]);
            int weight = Integer.parseInt(parts[1]);
            Item temp = new Item(value, weight);
            items[i] = temp;
        }

    }

    private List<String> getLines(String fileName) throws IOException {
        List<String> lines = new ArrayList<String>();

        BufferedReader input = new BufferedReader(new FileReader(fileName));
        try {
            String line = null;
            while ((line = input.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            input.close();
        }
        return lines;
    }


}
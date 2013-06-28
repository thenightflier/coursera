import com.google.common.base.Objects;

/**
 * Class representing an Item to put in the knapsack
 */
class Item {
    private final int value;
    private final int weight;
    private final int density;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.density = value / weight;
    }

    int getDensity() {
        return density;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("value", value).add("weight", weight).add("density", density).toString();
    }


}
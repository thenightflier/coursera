import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class representing an Item to put in the knapsack
 */
class Item {
    private final int value;
    private final int weight;
    private final double density;
    private final int id;

    public Item(int id, int value, int weight) {
        this.id = id;
        this.value = value;
        this.weight = weight;
        this.density = BigDecimal.valueOf(value).divide(BigDecimal.valueOf(weight),4,RoundingMode.HALF_EVEN).doubleValue();
    }

    public int getId() {
        return id;
    }

    public double getDensity() {
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
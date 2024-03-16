package org.watermelon;

public enum FruitType {
    CHERRY, STRAWBERRY, GRAPE, LEMON, ORANGE, APPLE, PEAR,PEACH, PINEAPPLE, MELON, WATERMELON;
    private static final FruitType[] values = values();

    public FruitType next() {
        return values[(this.ordinal() + 1) % values.length];
    }
}


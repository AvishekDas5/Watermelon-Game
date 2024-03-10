package org.watermelon.fruits;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import org.watermelon.FruitType;

public class Apple extends Fruit {

    public Apple(Point2D position) {
        super("apple_view.png", 47.5, FruitType.APPLE);
        this.position = position;
    }

    @Override
    public Entity buildFruit() {
        return super.buildFruit();
    }
}

package org.watermelon.fruits;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import org.watermelon.FruitType;

public class Cherry extends Fruit {

    public Cherry(Point2D position) {
        super("cherry_view.png", 21.015, FruitType.CHERRY);
        this.position = position;
    }

    @Override
    public Entity buildFruit() {
        return super.buildFruit();
    }
}

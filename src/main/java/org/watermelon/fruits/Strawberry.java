package org.watermelon.fruits;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import org.watermelon.FruitType;

public class Strawberry extends Fruit {

    public Strawberry(Point2D position) {
        super("strawberry_view.png", 26, FruitType.STRAWBERRY);
        this.position = position;
    }

    @Override
    public Entity buildFruit() {
        return super.buildFruit();
    }
}

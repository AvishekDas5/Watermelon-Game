package org.watermelon.fruits;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import org.watermelon.FruitType;

public class Orange extends Fruit {

    public Orange(Point2D position) {
        super("orange_view.png", 42, FruitType.ORANGE);
        this.position = position;
    }

    @Override
    public Entity buildFruit() {
        return super.buildFruit();
    }
}

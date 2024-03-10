package org.watermelon.fruits;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import org.watermelon.FruitType;

public class Pineapple extends Fruit {

    public Pineapple(Point2D position) {
        super("pineapple_view.png", 75, FruitType.PINEAPPLE);
        this.position = position;
    }

    @Override
    public Entity buildFruit() {
        return super.buildFruit();
    }
}

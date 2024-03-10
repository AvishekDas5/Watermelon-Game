package org.watermelon.fruits;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import org.watermelon.FruitType;

public class Watermelon extends Fruit {

    public Watermelon(Point2D position) {
        super("watermelon_view.png", 105, FruitType.WATERMELON);
        this.position = position;
    }

    @Override
    public Entity buildFruit() {
        return super.buildFruit();
    }
}

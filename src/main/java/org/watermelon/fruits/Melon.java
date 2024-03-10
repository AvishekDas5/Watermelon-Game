package org.watermelon.fruits;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import org.watermelon.FruitType;

public class Melon extends Fruit {

    public Melon(Point2D position) {
        super("melon_view.png", 92.5, FruitType.MELON);
        this.position = position;
    }

    @Override
    public Entity buildFruit() {
        return super.buildFruit();
    }
}

package org.watermelon;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Player {
    private Entity rectangle;
    private Entity line;
    private long lastClickTime;
    private final long coolDownDuration = 1000;
    private final double lineHeight = 540;
    private double MIN_X;
    private double MAX_X;
    private final FruitFactory fruitFactory;

    public Player(FruitFactory fruitFactory) {
        this.fruitFactory = fruitFactory;
        initializeEntities();
        initializeInput();
    }

    private void initializeEntities() {
        rectangle = FXGL.entityBuilder()
                .at(400, 60)
                .viewWithBBox(new Rectangle(100, 50, Color.TRANSPARENT))
                .with(new CollidableComponent(true))
                .buildAndAttach();

        line = FXGL.entityBuilder()
                .at(rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight())
                .view(new Line(0, 0, 0, lineHeight + 15) {{
                    setStroke(Color.WHITE);
                }})
                .buildAndAttach();
    }

    private void initializeInput() {
        FXGL.getInput().addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            double mouseX = event.getSceneX();
            double newRectangleX = mouseX - (rectangle.getWidth() / 2);
            newRectangleX = Math.max(MIN_X, Math.min(newRectangleX, MAX_X));
            rectangle.setX(newRectangleX);
            line.setX(rectangle.getX() + rectangle.getWidth() / 2);
        });

        FXGL.getInput().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime >= coolDownDuration) {
                lastClickTime = currentTime;
                Entity newFruit = fruitFactory.spawnFruitAt(rectangle.getPosition().add((rectangle.getWidth() / 2 - fruitFactory.currentFruitImageView.getImage().getWidth() / 2), 0));
                FXGL.getGameWorld().addEntity(newFruit);
                FXGL.play("fruit_drop.wav");
                double imageWidth = fruitFactory.currentFruitImageView.getImage().getWidth();
                double imageHeight = fruitFactory.currentFruitImageView.getImage().getHeight();
                fruitFactory.currentFruitImageView.setX(rectangle.getX() + rectangle.getWidth() / 2 - imageWidth / 2);
                fruitFactory.currentFruitImageView.setY(60 + (rectangle.getHeight()) - imageHeight);
                MIN_X = (340 + fruitFactory.currentFruitImageView.getImage().getWidth() / 2);
                MAX_X = (FXGL.getAppWidth() - 440 - fruitFactory.currentFruitImageView.getImage().getWidth() / 2);
            }
        });

        FXGL.getInput().addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double mouseX = event.getSceneX();
            double newRectangleX = mouseX - (rectangle.getWidth() / 2);
            newRectangleX = Math.max(MIN_X, Math.min(newRectangleX, MAX_X));
            rectangle.setX(newRectangleX);
            line.setX(rectangle.getX() + rectangle.getWidth() / 2);
        });

        rectangle.xProperty().addListener((obs, oldX, newX) -> {
            double imageWidth = fruitFactory.currentFruitImageView.getImage().getWidth();
            double imageHeight = fruitFactory.currentFruitImageView.getImage().getHeight();
            fruitFactory.currentFruitImageView.setX(newX.doubleValue() + rectangle.getWidth() / 2 - imageWidth / 2);
            fruitFactory.currentFruitImageView.setY(60 + (rectangle.getHeight()) - imageHeight);
            MIN_X = (340 + fruitFactory.currentFruitImageView.getImage().getWidth() / 2);
            MAX_X = (FXGL.getAppWidth() - 440 - fruitFactory.currentFruitImageView.getImage().getWidth() / 2);
        });
    }
}

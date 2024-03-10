package org.watermelon;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import org.watermelon.fruits.*;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
public class FruitFactory {

    private final Queue<Fruit> fruitQueue = new LinkedList<>();
    public ImageView nextFruitImageView;
    public ImageView currentFruitImageView;


    public Entity spawnFruitAt(Point2D currentPosition) {
        if (fruitQueue.isEmpty()) {
            fillFruitQueue(currentPosition);
        }

        Fruit fruitToSpawn = fruitQueue.poll();

        enqueueRandomFruit(currentPosition);

        updateCurrentFruitImageView();
        updateNextFruitImageView();

        assert fruitToSpawn != null;
        Entity newFruit = fruitToSpawn.buildFruit();
        newFruit.setPosition(currentPosition);

        return newFruit;
    }

    private void fillFruitQueue(Point2D position) {
        fruitQueue.addAll(Arrays.asList(
                new Cherry(position),
                new Grape(position),
                new Lemon(position),
                new Strawberry(position),
                new Orange(position),
                new Apple(position)
        ));
        Collections.shuffle((List<?>) fruitQueue);
    }

    private void enqueueRandomFruit(Point2D position) {
        Fruit[] fruits = {
                new Cherry(position),
                new Grape(position),
                new Lemon(position),
                new Strawberry(position),
                new Orange(position),
                new Apple(position)
        };
        int randomIndex = new Random().nextInt(fruits.length);
        fruitQueue.offer(fruits[randomIndex]);
    }

    private void updateCurrentFruitImageView() {
        if (!fruitQueue.isEmpty()) {
            Image currentFruitTexture = fruitQueue.peek().getTexture();
            currentFruitImageView.setImage(currentFruitTexture);
        }
    }

    void initFruits() {
        Entity newFruit = spawnFruitAt(new Point2D(10, 1000));
        FXGL.getGameWorld().addEntity(newFruit);
    }

    private void updateNextFruitImageView() {
        if (fruitQueue.size() > 1) {
            Fruit secondFruit = fruitQueue.toArray(new Fruit[0])[1];
            Image nextFruitTexture = secondFruit.getTexture();
            nextFruitImageView.setImage(nextFruitTexture);
        }
    }
}

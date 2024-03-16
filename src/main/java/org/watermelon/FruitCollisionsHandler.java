package org.watermelon;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import org.watermelon.fruits.*;
import javafx.geometry.Point2D;

public class FruitCollisionsHandler {

    public static void initCollisionHandlers() {
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.CHERRY, FruitType.CHERRY, 1));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.STRAWBERRY, FruitType.STRAWBERRY, 4));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.GRAPE, FruitType.GRAPE, 9));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.LEMON, FruitType.LEMON, 13));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.ORANGE, FruitType.ORANGE, 20));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.APPLE, FruitType.APPLE, 27));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.PEAR, FruitType.PEAR, 35));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.PEACH, FruitType.PEACH, 44));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.PINEAPPLE, FruitType.PINEAPPLE, 54));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.MELON, FruitType.MELON, 65));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.WATERMELON, FruitType.WATERMELON, 77));
    }

    public static CollisionHandler getCollisionHandler(FruitType type1, FruitType type2, int score) {
        return new CollisionHandler(type1, type2) {
            @Override
            protected void onCollisionBegin(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity newFruit = createFruitFromType(type1.next(), spawnPoint);
                assert newFruit != null;
                FXGL.getGameWorld().addEntity(newFruit);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(score);
                FXGL.play("fruit_merge.wav");
            }
        };
    }
    public static Entity createFruitFromType(FruitType type, Point2D position) {
        switch (type) {
            case STRAWBERRY:
                return new Strawberry(position).buildFruit();
            case GRAPE:
                return new Grape(position).buildFruit();
            case LEMON:
                return new Lemon(position).buildFruit();
            case ORANGE:
                return new Orange(position).buildFruit();
            case APPLE:
                return new Apple(position).buildFruit();
            case PEAR:
                return new Pear(position).buildFruit();
            case PEACH:
                return new Peach(position).buildFruit();
            case PINEAPPLE:
                return new Pineapple(position).buildFruit();
            case MELON:
                return new Melon(position).buildFruit();
            case WATERMELON:
                return new Watermelon(position).buildFruit();
            default:
                return null;
        }
    }

}

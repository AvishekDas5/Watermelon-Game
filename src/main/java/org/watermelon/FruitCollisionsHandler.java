package org.watermelon;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import org.watermelon.fruits.*;
import javafx.geometry.Point2D;

public class FruitCollisionsHandler {

    public static void initCollisionHandlers() {
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.CHERRY, FruitType.CHERRY));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.STRAWBERRY, FruitType.STRAWBERRY));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.GRAPE, FruitType.GRAPE));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.LEMON, FruitType.LEMON));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.ORANGE, FruitType.ORANGE));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.APPLE, FruitType.APPLE));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.PEAR, FruitType.PEAR));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.PEACH, FruitType.PEACH));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.PINEAPPLE, FruitType.PINEAPPLE));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.MELON, FruitType.MELON));
        FXGL.getPhysicsWorld().addCollisionHandler(getCollisionHandler(FruitType.WATERMELON, FruitType.WATERMELON));
    }

    public static CollisionHandler getCollisionHandler(FruitType type1, FruitType type2) {
        return new CollisionHandler(type1, type2) {
            @Override
            protected void onCollisionBegin(Entity e1, Entity e2) {
                // Handle collisions based on the fruit types
                switch (type1) {
                    case CHERRY:
                        handleCherryCollision(e1, e2);
                        break;
                    case STRAWBERRY:
                        handleStrawberryCollision(e1, e2);
                        break;
                    case GRAPE:
                        handleGrapeCollision(e1, e2);
                        break;
                    case LEMON:
                        handleLemonCollision(e1, e2);
                        break;
                    case ORANGE:
                        handleOrangeCollision(e1, e2);
                        break;
                    case APPLE:
                        handleAppleCollision(e1, e2);
                        break;
                    case PEAR:
                        handlePearCollision(e1, e2);
                        break;
                    case PEACH:
                        handlePeachCollision(e1, e2);
                        break;
                    case PINEAPPLE:
                        handlePineappleCollision(e1, e2);
                        break;
                    case MELON:
                        handleMelonCollision(e1, e2);
                        break;
                    case WATERMELON:
                        handleWatermelonCollision(e1, e2);
                        break;
                    default:
                        break;
                }

            }


            private void handleCherryCollision(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity strawberry = new Strawberry(spawnPoint).buildFruit();
                FXGL.getGameWorld().addEntity(strawberry);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(1);
                FXGL.play("fruit_merge.wav");
            }

            private void handleStrawberryCollision(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity grape = new Grape(spawnPoint).buildFruit();
                FXGL.getGameWorld().addEntity(grape);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(4);
                FXGL.play("fruit_merge.wav");
            }

            private void handleGrapeCollision(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity lemon = new Lemon(spawnPoint).buildFruit();
                FXGL.getGameWorld().addEntity(lemon);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(9);
                FXGL.play("fruit_merge.wav");
            }

            private void handleLemonCollision(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity orange = new Orange(spawnPoint).buildFruit();
                FXGL.getGameWorld().addEntity(orange);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(13);
                FXGL.play("fruit_merge.wav");
            }

            private void handleOrangeCollision(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity apple = new Apple(spawnPoint).buildFruit();
                FXGL.getGameWorld().addEntity(apple);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(20);
                FXGL.play("fruit_merge.wav");
            }

            private void handleAppleCollision(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity pear = new Pear(spawnPoint).buildFruit();
                FXGL.getGameWorld().addEntity(pear);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(27);
                FXGL.play("fruit_merge.wav");
            }

            private void handlePearCollision(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity peach = new Peach(spawnPoint).buildFruit();
                FXGL.getGameWorld().addEntity(peach);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(35);
                FXGL.play("fruit_merge.wav");
            }

            private void handlePeachCollision(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity pineapple = new Pineapple(spawnPoint).buildFruit();
                FXGL.getGameWorld().addEntity(pineapple);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(44);
                FXGL.play("fruit_merge.wav");
            }

            private void handlePineappleCollision(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity melon = new Melon(spawnPoint).buildFruit();
                FXGL.getGameWorld().addEntity(melon);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(54);
                FXGL.play("fruit_merge.wav");
            }

            private void handleMelonCollision(Entity e1, Entity e2) {
                Point2D position1 = e1.getPosition();
                Point2D position2 = e2.getPosition();
                Point2D spawnPoint = position1.add(position2).multiply(0.5);
                Entity watermelon = new Watermelon(spawnPoint).buildFruit();
                FXGL.getGameWorld().addEntity(watermelon);
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(65);
                FXGL.play("fruit_merge.wav");
            }

            private void handleWatermelonCollision(Entity e1, Entity e2) {
                e1.removeFromWorld();
                e2.removeFromWorld();
                ScoreManager.addToGameScore(77);
                FXGL.play("fruit_merge.wav");
            }
        };

    }
}


package org.watermelon;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class WatermelonGame extends GameApplication {
    int APP_WIDTH = 1280;
    int APP_HEIGHT = 720;
    private final Point2D rectanglePosition = new Point2D(0, 0);
    FruitType[] fruitTypes = {
            FruitType.CHERRY, FruitType.STRAWBERRY, FruitType.GRAPE, FruitType.LEMON, FruitType.ORANGE, FruitType.APPLE, FruitType.PEAR, FruitType.PEACH, FruitType.PINEAPPLE, FruitType.MELON, FruitType.WATERMELON
    };

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setAppIcon("watermelon_view.png");
        settings.setDeveloperMenuEnabled(true);
        settings.setWidth(APP_WIDTH);
        settings.setHeight(APP_HEIGHT);
        settings.setTitle("WaterMelon Game");
        settings.setVersion("0.1");
        settings.setManualResizeEnabled(true);
        settings.setFullScreenAllowed(true);
    }
    protected void initGame() {

        double wallThickness = 1;
        double floorHeight = getAppHeight() - getAppHeight()*0.0833;
        double wallHeight = getAppHeight()*0.752;
        double floorWidth = getAppWidth() ;

        Container container = new Container(wallThickness, floorHeight, wallHeight, floorWidth);
        ContainerEntity containerEntity = container.createContainer();
        Player player = new Player(fruitFactory);
    }
    FruitFactory fruitFactory = new FruitFactory();

    protected void initPhysics() {

        FruitCollisionsHandler.initCollisionHandlers();

        for (FruitType fruitType : fruitTypes) {
            FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(fruitType, ContainerType.LOOSE_COLLIDER) {
                @Override
                protected void onCollisionBegin(Entity fruit, Entity looseCollider) {
                    FXGL.getGameTimer().runOnceAfter(() -> {
                        if (fruit.isActive() && fruit.isColliding(looseCollider)) {
                            endGame();
                        }
                    }, Duration.seconds(2));
                }
            });
        }
    }

    private void endGame() {
        FXGL.getDialogService().showMessageBox("Game Over, your score is: " + ScoreManager.getGameScore()+"\n Do you want to play again? ", () -> {
            ScoreManager.setGameScore(0);
            for (Enum  fruitType : FruitType.values()) {
                getGameWorld().getEntitiesByType(fruitType).forEach(Entity::removeFromWorld);
            }
        });
    }

    protected void initUI() {

        fruitFactory.nextFruitImageView = new ImageView();
        fruitFactory.nextFruitImageView.setFitWidth(60);
        fruitFactory.nextFruitImageView.setFitHeight(60);
        fruitFactory.nextFruitImageView.setX(1095);
        fruitFactory.nextFruitImageView.setY(120);
        getGameScene().addUINode(fruitFactory.nextFruitImageView);

        fruitFactory.currentFruitImageView = new ImageView();
        fruitFactory.currentFruitImageView.setX(rectanglePosition.getX());
        fruitFactory.currentFruitImageView.setY(rectanglePosition.getY());
        getGameScene().addUINode(fruitFactory.currentFruitImageView);

        Text scoreText = FXGL.getUIFactoryService().newText("", Color.WHITE, 60);
        scoreText.textProperty().bind(ScoreManager.getGameScoreProperty().asString());
        scoreText.setX(95);
        scoreText.setY(160);
        scoreText.setStroke(Color.BLACK);
        scoreText.setStrokeWidth(2);
        scoreText.setTextAlignment(TextAlignment.CENTER);
        getGameScene().addUINode(scoreText);

        Entity ringOfFruits = FXGL.entityBuilder()
                .view("ring_view.png")
                .at(1000, 400)
                .buildAndAttach();

        Entity NextFruitBubble = FXGL.entityBuilder()
                .view("next_fruit_view.png")
                .at(1040, 50)
                .buildAndAttach();

        Entity ScoreBubble = FXGL.entityBuilder()
                .view("score_view.png")
                .at(40, 50)
                .buildAndAttach();

        fruitFactory.initFruits();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
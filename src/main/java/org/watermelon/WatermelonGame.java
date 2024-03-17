package org.watermelon;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.IntroScene;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGL.*;

public class WatermelonGame extends GameApplication {
    final int APP_WIDTH = 1280;
    final int APP_HEIGHT = 720;
    static String playerName;
    private boolean dialogShown = false;
    private boolean newHighScore = false;
    private final Point2D rectanglePosition = new Point2D(0, 0);
    private Texture highScoreTexture;
    int highScore;
    final FruitFactory fruitFactory = new FruitFactory();
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setAppIcon("Watermelon.png");
        settings.setDeveloperMenuEnabled(true);
        settings.setWidth(APP_WIDTH);
        settings.setHeight(APP_HEIGHT);
        settings.setTitle("WaterMelon Game");
        settings.setVersion("0.1");
        settings.setManualResizeEnabled(true);
        settings.setFullScreenAllowed(true);
        settings.setGameMenuEnabled(true);
        settings.setFullScreenFromStart(true);
        settings.setIntroEnabled(true);
        settings.setSceneFactory(new SceneFactory() {
            @NotNull
            @Override
            public IntroScene newIntro() {
                return new MyIntroScene();
            }
        });
    }
    protected void initGame() {
        double wallThickness = 1;
        double floorHeight = getAppHeight() - getAppHeight() * 0.0833;
        double wallHeight = getAppHeight() * 0.752;
        double floorWidth = getAppWidth();

        Text text = getUIFactoryService().newText("", Color.BLUE, 24.0);

        addUINode(text, 0, 150);

        Container container = new Container(wallThickness, floorHeight, wallHeight, floorWidth);
        container.createContainer();
        new Player(fruitFactory);
    }
    protected void initPhysics() {
        FruitCollisionsHandler.initCollisionHandlers();
        for (FruitType fruitType : FruitType.values()) {
            getPhysicsWorld().addCollisionHandler(new CollisionHandler(fruitType, ContainerType.LOOSE_COLLIDER) {
                @Override
                protected void onCollisionBegin(Entity fruit, Entity looseCollider) {
                    getGameTimer().runOnceAfter(() -> {
                        if (fruit.isActive() && fruit.isColliding(looseCollider)) {
                            endGame();
                        }
                    }, Duration.seconds(2));
                }
            });
        }
    }
    private void endGame() {
        HighScoreManager.saveHighScore(playerName,ScoreManager.getGameScore());
        getDialogService().showMessageBox("Well Played "+playerName+"!\nGame Over, your score is: " + ScoreManager.getGameScore()+"\n Do you want to play again? ", () -> {
            ScoreManager.setGameScore(0);
            for (FruitType  fruitType : FruitType.values()) {
                getGameWorld().getEntitiesByType(fruitType).forEach(Entity::removeFromWorld);
                HighScoreManager.reload();
            }
        });
    }
    @Override
    protected void onUpdate(double tpf) {
        if (!dialogShown) {
            showDialog();
            dialogShown = true;
        }
        if(!newHighScore && highScore<ScoreManager.getGameScore()){
            showHighScoreAnimation();
            newHighScore = true;
            play("newHighScore.wav");
        }
    }
    private void showHighScoreAnimation() {
        highScoreTexture = texture("HighScore.png");

        // Set initial position above the screen
        highScoreTexture.setTranslateX((double) getAppWidth() / 2 - highScoreTexture.getWidth() / 2);
        highScoreTexture.setTranslateY(-highScoreTexture.getHeight());

        // Add the texture to the game scene
        addUINode(highScoreTexture);
        animationBuilder()
                .duration(Duration.seconds(2))
                .interpolator(Interpolators.BOUNCE.EASE_OUT())
                .onFinished(() -> removeUINode(highScoreTexture)) // Remove the texture after the animation is complete
                .translate(highScoreTexture)
                .to(new Point2D(500,500))
                .buildAndPlay();
    }
    private void showDialog() {
        getDialogService().showInputBox("Enter your name:", player -> {
            if (!player.isEmpty()) {
                playerName = player;
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

        getGameScene().setCursorInvisible();

        fruitFactory.currentFruitImageView = new ImageView();
        fruitFactory.currentFruitImageView.setX(rectanglePosition.getX());
        fruitFactory.currentFruitImageView.setY(rectanglePosition.getY());
        getGameScene().addUINode(fruitFactory.currentFruitImageView);

        Text scoreText = getUIFactoryService().newText("", Color.WHITE, 55);
        scoreText.setStroke(Color.BLACK);
        scoreText.setStrokeWidth(2);
        scoreText.setTextAlignment(TextAlignment.CENTER);
        scoreText.textProperty().bind(ScoreManager.getGameScoreProperty().asString());
        scoreText.setX(90);
        scoreText.setY(160);
        getGameScene().addUINode(scoreText);
        // Adjust the score text position dynamically based on the length of the score string
        ScoreManager.getGameScoreProperty().addListener((obs, oldScore, newScore) -> {
            double xOffset = (newScore.toString().length() - 1) * 8;
            scoreText.setX(90 - xOffset);
        });

        HighScoreManager.loadHighScores();
        highScore = HighScoreManager.getHighScore();

        entityBuilder()
                .view("ring_view.png")
                .at(1000, 400)
                .buildAndAttach();

        entityBuilder()
                .view("next_fruit_view.png")
                .at(1040, 50)
                .buildAndAttach();

        entityBuilder()
                .view("score_view.png")
                .at(40, 50)
                .buildAndAttach();

        fruitFactory.initFruits();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
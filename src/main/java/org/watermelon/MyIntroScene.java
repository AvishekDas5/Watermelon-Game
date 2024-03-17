package org.watermelon;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.IntroScene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class MyIntroScene extends IntroScene {

    private final List<Animation<?>> animations = new ArrayList<>();

    public MyIntroScene() {
        // Load and position the watermelon image
        Texture watermelonTexture = FXGL.getAssetLoader().loadTexture("Watermelon.png");
        watermelonTexture.setScaleX(0.25);
        watermelonTexture.setScaleY(0.25);
        watermelonTexture.setTranslateX(FXGL.getAppWidth() / 2.0 - watermelonTexture.getWidth() / 2.0);
        watermelonTexture.setTranslateY(FXGL.getAppHeight() / 2.0 - watermelonTexture.getHeight() / 2.0);

        // Create Watermelon Game text
        Text text = new Text("Watermelon Game");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        text.setFill(Color.WHITE);
        text.setOpacity(0);
        text.setTranslateX(FXGL.getAppWidth() / 2.0 - text.getBoundsInLocal().getWidth() / 2.0);
        text.setTranslateY(FXGL.getAppHeight() / 2.0 + 150);

        // Add a glow effect to the text
        text.setEffect(new DropShadow(10, Color.BLACK));

        // Create animations
        var textureAnimation = FXGL.animationBuilder()
                .duration(Duration.seconds(1.5))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .scale(watermelonTexture)
                .from(new Point2D(0, 0))
                .to(new Point2D(1, 1))
                .build();

        var textAnimation = FXGL.animationBuilder()
                .duration(Duration.seconds(1.5))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .fadeIn(text)
                .build();

        // Add animations to the list
        animations.add(textureAnimation);
        animations.add(textAnimation);

        // Set finishIntro as the action when all animations finish
        animations.get(animations.size() - 1).setOnFinished(this::finishIntro);

        // Add elements to the scene
        getContentRoot().getChildren().addAll(
                new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight()),
                watermelonTexture,
                text
        );
    }

    @Override
    protected void onUpdate(double tpf) {
        animations.forEach(a -> a.onUpdate(tpf));
    }

    @Override
    public void startIntro() {
        animations.forEach(Animation::start);
    }
}

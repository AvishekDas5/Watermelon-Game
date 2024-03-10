package org.watermelon.fruits;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import org.watermelon.FruitType;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class Fruit {
    String textureName;
    double bBoxRadius;
    PhysicsComponent fruitPhysics = new PhysicsComponent();
    Point2D position;

    FruitType fruitType;

    public Fruit(String textureName, double bBoxRadius, FruitType fruitType) {

        this.textureName = textureName;
        this.bBoxRadius = bBoxRadius;
        this.fruitPhysics = new PhysicsComponent();
        this.fruitType = fruitType;

        fruitPhysics.setBodyType(BodyType.DYNAMIC);
        FixtureDef fruitFixtureDef = new FixtureDef();
        fruitFixtureDef.setDensity(0.01f);
        fruitPhysics.setFixtureDef(fruitFixtureDef);
    }

    public Entity buildFruit() {
        return FXGL.entityBuilder()
                .type(fruitType)
                .at(position)
                .view(textureName)
                .bbox(new HitBox(BoundingShape.circle(bBoxRadius)))
                .with(fruitPhysics)
                .with(new CollidableComponent(true))
                .build();
    }

    public Image getTexture() {
        return FXGL.getAssetLoader().loadImage(textureName);
    }
}

package org.watermelon;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.getAppWidth;

public class Container {
    private final double wallThickness;
    private final double floorHeight;
    private final double wallHeight;
    private final double floorWidth;

    private final PhysicsComponent containerPhysics;
    private final PhysicsComponent rightWallPhysics;

    public Container(double wallThickness, double floorHeight, double wallHeight, double floorWidth) {
        this.wallThickness = wallThickness;
        this.floorHeight = floorHeight;
        this.wallHeight = wallHeight;
        this.floorWidth = floorWidth;

        containerPhysics = new PhysicsComponent();
        rightWallPhysics = new PhysicsComponent();
    }

    public void createContainer() {
        FXGL.entityBuilder()
                .type(ContainerType.FLOOR)
                .at(0, floorHeight)
                .viewWithBBox(new javafx.scene.shape.Rectangle(floorWidth, wallThickness, Color.TRANSPARENT))
                .with(new CollidableComponent(true))
                .with(containerPhysics)
                .buildAndAttach();

        FXGL.entityBuilder()
                .type(ContainerType.WALL)
                .at(387.5, floorHeight - wallHeight)
                .viewWithBBox(new javafx.scene.shape.Rectangle(wallThickness, wallHeight, Color.TRANSPARENT))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .buildAndAttach();

        FXGL.entityBuilder()
                .type(ContainerType.WALL)
                .at(getAppWidth() - wallThickness - 387.5, floorHeight - wallHeight)
                .viewWithBBox(new Rectangle(wallThickness, wallHeight, Color.TRANSPARENT))
                .with(rightWallPhysics, new CollidableComponent(true))
                .buildAndAttach();

        FXGL.entityBuilder()
                .type(ContainerType.LOOSE_COLLIDER)
                .at(0, 120)
                .viewWithBBox(new Rectangle(floorWidth, 15, Color.TRANSPARENT))
                .with(new CollidableComponent(true))
                .buildAndAttach();

        FXGL.entityBuilder()
                .view("background_view.png")
                .buildAndAttach();

        FXGL.entityBuilder()
                .view("container_view.png")
                .at(387.5, 135)
                .zIndex(10)
                .buildAndAttach();

    }

}

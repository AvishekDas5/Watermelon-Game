package org.example;
import com.almasb.fxgl.entity.Entity;

public class ContainerEntity {
    private Entity floor;
    private Entity leftWall;
    private Entity rightWall;

    public ContainerEntity(Entity floor, Entity leftWall, Entity rightWall) {
        this.floor = floor;
        this.leftWall = leftWall;
        this.rightWall = rightWall;
    }

    public Entity getFloor() {
        return floor;
    }

    public Entity getLeftWall() {
        return leftWall;
    }

    public Entity getRightWall() {
        return rightWall;
    }
}

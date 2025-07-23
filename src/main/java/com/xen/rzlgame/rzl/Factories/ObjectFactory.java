package com.xen.rzlgame.rzl.Factories;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class ObjectFactory implements EntityFactory {

    @Spawns("floor")
    public Entity newFloor(SpawnData data) {

        PhysicsComponent pc = new PhysicsComponent();
        pc.setBodyType(BodyType.STATIC);
        return entityBuilder()
                .type(EntityType.FLOOR)
                .with(pc)
                .with(new CollidableComponent(true))
                .collidable()
                .at(0, 500)
                .viewWithBBox(new Rectangle(2400, 200, Color.GREEN))
                .buildAndAttach();


    }
}

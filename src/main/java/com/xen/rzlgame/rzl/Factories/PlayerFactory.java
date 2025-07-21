package com.xen.rzlgame.rzl.Factories;

import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.xen.rzlgame.rzl.Components.PlayerComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {

        PhysicsComponent pc = new PhysicsComponent();
        pc.setBodyType(BodyType.DYNAMIC);

        return entityBuilder()
                .type(EntityType.PLAYER)
                .at(300, 300)
                .with(pc)
                .viewWithBBox(new Rectangle(25, 25, Color.BLUE))
                .with(new CollidableComponent(true))
                .with(new PlayerComponent())
                .buildAndAttach();
    }

    @Spawns("attack")
    public Entity newAttack(SpawnData data) {

        return entityBuilder(data)
                .viewWithBBox(new Rectangle(40, 10, Color.ORANGE))
                .build();

    }
}

package com.xen.rzlgame.rzl.Factories;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.xen.rzlgame.rzl.Components.NpcIdComponent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class NPCFactory implements EntityFactory {

    @Spawns("Maria Clara")
    public Entity newMariaClara(SpawnData data) {

        PhysicsComponent pc = new PhysicsComponent();
        pc.setBodyType(BodyType.STATIC);

        return entityBuilder()
                .type(EntityType.NPC)
                .at(300, 475)
                .with(pc)
                .viewWithBBox(new Rectangle(25, 25, Color.GREEN))
                .collidable()
                .with(new NpcIdComponent("Maria Clara"))
                .buildAndAttach();
    }

    @Spawns("Elias")
    public Entity newElias(SpawnData data) {

        PhysicsComponent pc = new PhysicsComponent();
        pc.setBodyType(BodyType.STATIC);

        return entityBuilder()
                .type(EntityType.NPC)
                .at(200, 475)
                .with(pc)
                .with(new NpcIdComponent("Elias"))
                .viewWithBBox(new Rectangle(25, 25, Color.YELLOW))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }
}

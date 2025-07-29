package com.xen.rzlgame.rzl.Factories;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.xen.rzlgame.rzl.Components.Animations.NPCAnimationComponent;
import com.xen.rzlgame.rzl.Components.NpcComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class NPCFactory implements EntityFactory {

    @Spawns("Maria Clara")
    public Entity newMariaClara(SpawnData data) {

        PhysicsComponent pc = new PhysicsComponent();
        pc.setBodyType(BodyType.STATIC);

        return entityBuilder(data)
                .type(EntityType.NPC)
                .at(300, 465)
                .with(pc)
                .with(new NpcComponent("Maria Clara"))
                .with(new NPCAnimationComponent())
                .bbox(new HitBox(BoundingShape.box(20, 55)))
                .collidable()
                .buildAndAttach();
    }

    @Spawns("Elias")
    public Entity newElias(SpawnData data) {

        PhysicsComponent pc = new PhysicsComponent();
        pc.setBodyType(BodyType.STATIC);

        return entityBuilder()
                .type(EntityType.NPC)
                .at(150, 450)
                .with(pc)
                .with(new NpcComponent("Elias"))
                .with(new NPCAnimationComponent())
                .bbox(new HitBox(BoundingShape.box(20, 75)))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }
}

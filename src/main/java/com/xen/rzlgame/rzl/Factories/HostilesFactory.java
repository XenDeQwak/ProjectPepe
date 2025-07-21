package com.xen.rzlgame.rzl.Factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.xen.rzlgame.rzl.Components.AttackComponent;
import com.xen.rzlgame.rzl.Components.BossComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class HostilesFactory implements EntityFactory {

    @Spawns("Boss")
    public Entity newBoss(SpawnData data) {

        PhysicsComponent pc = new PhysicsComponent();
        pc.setBodyType(BodyType.STATIC);

        return entityBuilder()
                .at(400, 100)
                .type(EntityType.BOSS)
                .viewWithBBox(new Rectangle(25, 25, Color.RED))
                .with(pc)
                .with(new BossComponent())
                .collidable()
                .buildAndAttach();
    }

    @Spawns("bossAttack")
    public Entity newAttack(SpawnData data) {

        return FXGL.entityBuilder(data)
                .type(EntityType.BOSS_ATTACK)
                .viewWithBBox(new Rectangle(40, 40, Color.ORANGE))
                .with(new AttackComponent())
                .collidable()
                .build();

    }
}

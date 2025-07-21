package com.xen.rzlgame.rzl.Factories;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.xen.rzlgame.rzl.Components.EnemyComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class HostilesFactory implements EntityFactory {

    @Spawns("Enemy")
    public Entity newEnemy(SpawnData data) {

        PhysicsComponent pc = new PhysicsComponent();
        pc.setBodyType(BodyType.STATIC);

        return entityBuilder()
                .at(400, 300)
                .type(EntityType.ENEMY)
                .viewWithBBox(new Rectangle(25, 25, Color.RED))
                .with(pc)
                .with(new EnemyComponent())
                .collidable()
                .buildAndAttach();
    }
}

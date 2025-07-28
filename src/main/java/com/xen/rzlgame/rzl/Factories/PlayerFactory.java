package com.xen.rzlgame.rzl.Factories;

import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.xen.rzlgame.rzl.Components.Animations.PlayerAnimationComponent;
import com.xen.rzlgame.rzl.Components.AttackComponent;
import com.xen.rzlgame.rzl.Components.PlayerComponent;


import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {

        PhysicsComponent pc = new PhysicsComponent();
        pc.setBodyType(BodyType.DYNAMIC);
        pc.addGroundSensor(new HitBox(BoundingShape.box(30, 33)));

        return entityBuilder()
                .type(EntityType.PLAYER)
                .at(300, 450)
                .with(pc)
                .bbox(new HitBox(BoundingShape.box(30, 33)))
                .with(new PlayerAnimationComponent())
                .collidable()
                .with(new PlayerComponent())
                .buildAndAttach();
    }

    @Spawns("attack")
    public Entity newAttack(SpawnData data) {

        return entityBuilder(data)
                .type(EntityType.PLAYER_ATTACK)
                .bbox(new HitBox(BoundingShape.box(30, 10)))
                .with(new AttackComponent())
                .collidable()
                .build();

    }
}

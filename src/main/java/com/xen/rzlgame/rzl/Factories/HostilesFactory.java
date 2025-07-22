package com.xen.rzlgame.rzl.Factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
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

        var grid = new AStarGrid(800 / 40, 600 / 40);
        PhysicsComponent pc = new PhysicsComponent();
        pc.setBodyType(BodyType.KINEMATIC);


        return entityBuilder()
                .at(400, 100)
                .type(EntityType.BOSS)
                .viewWithBBox(new Rectangle(25, 25, Color.RED))
                .with(pc)
                .with(new BossComponent())
                .with(new CellMoveComponent(40, 40, 150))
                .with(new AStarMoveComponent(grid))
                .collidable()
                .buildAndAttach();
    }

    @Spawns("bossAttackA")
    public Entity newBossAttackA(SpawnData data) {

        return FXGL.entityBuilder(data)
                .type(EntityType.BOSS_ATTACK)
                .viewWithBBox(new Rectangle(40, 40, Color.ORANGE))
                .with(new AttackComponent())
                .collidable()
                .build();

    }

    @Spawns("bossAttackB")
    public Entity newBossAttackB(SpawnData data) {

        return FXGL.entityBuilder(data)
                .type(EntityType.BOSS_ATTACK)
                .viewWithBBox(new Rectangle(80, 40, Color.ORANGE))
                .with(new AttackComponent())
                .collidable()
                .build();

    }
}

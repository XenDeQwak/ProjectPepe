package com.xen.rzlgame.rzl.Handlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.xen.rzlgame.rzl.Components.PlayerComponent;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class InputHandler {

    private final PhysicsComponent physics;
    private final Entity player;
    private final Set<KeyCode> pressedKeys = EnumSet.noneOf(KeyCode.class);

    public InputHandler(Entity player) {
        this.player = player;
        this.physics = player.getComponent(PhysicsComponent.class);
    }

    public void initInput(Input input) {
        Map<KeyCode, Point2D> moves = Map.of(
                KeyCode.UP, new Point2D(0, -1),
                KeyCode.DOWN, new Point2D(0, 1),
                KeyCode.LEFT, new Point2D(-1, 0),
                KeyCode.RIGHT, new Point2D(1, 0)
        );

        moves.forEach((key, direction) -> {
            input.addAction(new UserAction("Move " + key) {
                @Override
                protected void onAction() {
                    pressedKeys.add(key);
                    updateVelocity(moves);
                }

                @Override
                protected void onActionEnd() {
                    pressedKeys.remove(key);
                    updateVelocity(moves);
                }
            }, key);
        });

        input.addAction(new UserAction("Player Attack"){
            @Override
            protected void onActionBegin() {
                player.getComponent(PlayerComponent.class).attack();
            }

            @Override
            protected void onAction() {
            }
        }, KeyCode.X);
    }

    private void updateVelocity(Map<KeyCode, Point2D> moves) {
        Point2D total = Point2D.ZERO;
        for (KeyCode key : pressedKeys) {
            total = total.add(moves.get(key));
        }

        if (!total.equals(Point2D.ZERO)) {
            total = total.normalize().multiply(300);
        }

        physics.setVelocityX(total.getX());
        physics.setVelocityY(total.getY());
    }


}

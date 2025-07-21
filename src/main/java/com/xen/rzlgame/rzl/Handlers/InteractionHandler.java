package com.xen.rzlgame.rzl.Handlers;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.xen.rzlgame.rzl.Factories.EntityType;
import javafx.scene.input.KeyCode;

public class InteractionHandler {

    private Entity player;
    private PhysicsHandler ph;

    public InteractionHandler(Entity player, PhysicsHandler ph) {
        this.player = player;
        this.ph = ph;
    }

    public void initInput(Input input) {
        input.addAction(new UserAction("Interact"){
            @Override
            protected void onActionBegin() {
                switch (ph.getCurrentNpc()) {
                    case "JAMES": {
                        System.out.println("IM JAMES");
                        break;
                    }
                    case "BRIAN": {
                        System.out.println("IM BRIAN");
                        break;
                    }
                }
            }
        }, KeyCode.Z);
    }
}

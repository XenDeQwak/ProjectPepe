package com.xen.rzlgame.rzl.Handlers;

import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerNPCCollisionHandler;
import javafx.scene.input.KeyCode;

public class InteractionHandler {

    private PlayerNPCCollisionHandler ph;

    public InteractionHandler(PlayerNPCCollisionHandler ph) {
        this.ph = ph;
    }

    public void initInput(Input input) {
        input.addAction(new UserAction("Interact"){
            @Override
            protected void onActionBegin() {
                switch (ph.getCurrentNpc()) {
                    case "JAMES": {
                        var lines = FXGL.getAssetLoader().loadText("test.txt");
                        var cs = new Cutscene(lines);
                        FXGL.getCutsceneService().startCutscene(cs);
                        break;
                    }
                    case "BRIAN": {
                        break;
                    }
                }
            }
        }, KeyCode.Z);
    }
}

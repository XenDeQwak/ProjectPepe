package com.xen.rzlgame.rzl.Handlers;

import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.xen.rzlgame.rzl.Handlers.Collisions.PlayerNPCCollisionHandler;
import javafx.scene.input.KeyCode;

public class InteractionHandler {

    private PlayerNPCCollisionHandler ph;
    private WaveHandler wave;
    private int count = 0;

    public InteractionHandler(PlayerNPCCollisionHandler ph, WaveHandler wave) {
        this.ph = ph;
        this.wave = wave;
    }

    public void initInput(Input input) {
        input.addAction(new UserAction("Interact"){
            @Override
            protected void onActionBegin() {
                switch (ph.getCurrentNpc()) {
                    case "Maria Clara": {
                        playCutscene("maria.txt");
                        break;
                    }
                    case "Elias": {
                        playCutscene("elias.txt");
                        break;
                    }
                }
            }
        }, KeyCode.Z);
    }
    public void playCutscene(String fileName) {
        var lines = FXGL.getAssetLoader().loadText(fileName);
        var cs = new Cutscene(lines);
        FXGL.getCutsceneService().startCutscene(cs, () -> {
            count++;
            if (count >= 2) wave.setCutsceneActive(false);
        });
    }

}

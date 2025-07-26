package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.xen.rzlgame.rzl.Factories.EntityType;

public class NpcComponent extends Component {

    private final String id;

    public NpcComponent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void removeFromWorld() {
        if (!FXGL.getGameWorld().getEntitiesByType(EntityType.ENEMY).isEmpty()) entity.removeFromWorld();
    }

    @Override
    public void onUpdate(double tpf) {
        removeFromWorld();
    }
}

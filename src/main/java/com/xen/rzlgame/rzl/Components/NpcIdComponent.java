package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.entity.component.Component;

public class NpcIdComponent extends Component {

    private final String id;

    public NpcIdComponent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

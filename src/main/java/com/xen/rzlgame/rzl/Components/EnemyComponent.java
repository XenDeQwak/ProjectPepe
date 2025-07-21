package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.entity.component.Component;

public class EnemyComponent extends Component {

    private int health = 100;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

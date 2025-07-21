package com.xen.rzlgame.rzl.Components;

import com.almasb.fxgl.entity.component.Component;

public class AttackComponent extends Component {

    private int playerDamage = 10;
    private int bossDamage = 40;

    public int getPlayerDamage() {
        return playerDamage;
    }

    public int getBossDamage() {
        return bossDamage;
    }
}

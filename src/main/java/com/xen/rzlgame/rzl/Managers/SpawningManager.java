package com.xen.rzlgame.rzl.Managers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class SpawningManager {

    private int atkWidth = 40;
    private int atkHeight = 10;
    private Map<String, Entity> spawner = new HashMap<>();

    public void spawnAll() {
        spawner.put("player", FXGL.spawn("player"));
        spawner.put("James", FXGL.spawn("James"));
        spawner.put("Brian", FXGL.spawn("Brian"));
    }

    public Entity get(String name) {
        return spawner.get(name);
    }

    public int getAtkWidth() {
        return atkWidth;
    }

    public int getAtkHeight() {
        return atkHeight;
    }

    public Entity getPlayer() {
        return get("player");
    }

    public String getAttack() {
        return "attack";
    }

    public Entity[] getAllNPCs() {
        return new Entity[] {
                get("James"),
                get("Brian")
        };
    }
}

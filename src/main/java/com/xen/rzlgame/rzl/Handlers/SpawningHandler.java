package com.xen.rzlgame.rzl.Handlers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class SpawningHandler {

    private int atkWidth = 40;
    private int atkHeight = 10;
    private static final String PLAYER = "player";
    private static final String NPC_MARIA_CLARA = "Maria Clara";
    private static final String BOSS = "Boss";
    private static final String NPC_ELIAS = "Elias";
    private static final String FLOOR = "floor";

    private Map<String, Entity> spawner = new HashMap<>();

    public void spawnAll() {
        spawner.put(PLAYER, FXGL.spawn(PLAYER));
        spawner.put(NPC_MARIA_CLARA, FXGL.spawn(NPC_MARIA_CLARA));
        spawner.put(NPC_ELIAS, FXGL.spawn(NPC_ELIAS));
        spawner.put(FLOOR, FXGL.spawn(FLOOR));
    }

    public Entity get(String name) {
        return spawner.get(name);
    }

    public Entity getBoss() {
        return get(BOSS);
    }

    public int getAtkWidth() {
        return atkWidth;
    }

    public int getAtkHeight() {
        return atkHeight;
    }

    public Entity getPlayer() {
        return get(PLAYER);
    }

    public String getAttack() {
        return "attack";
    }

    public Entity[] getAllNPCs() {
        return new Entity[] {
                get(NPC_MARIA_CLARA),
                get(NPC_ELIAS)
        };
    }
}

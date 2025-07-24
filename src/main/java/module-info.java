open module com.xen.rzlgame.rzl {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.almasb.fxgl.all;
    requires transitive com.almasb.fxgl.entity;
    requires javafx.base;
    requires com.almasb.fxgl.core;
    requires com.almasb.fxgl.io;
    requires javafx.media;

    exports com.xen.rzlgame.rzl;
    exports com.xen.rzlgame.rzl.Factories;
    exports com.xen.rzlgame.rzl.Handlers;
    exports com.xen.rzlgame.rzl.Handlers.Collisions;
}
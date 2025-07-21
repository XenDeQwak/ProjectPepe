module com.xen.rzlgame.rzl {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;

    opens com.xen.rzlgame.rzl to javafx.fxml;
    exports com.xen.rzlgame.rzl;
    exports com.xen.rzlgame.rzl.Factories;
    opens com.xen.rzlgame.rzl.Factories to javafx.fxml;
    exports com.xen.rzlgame.rzl.Handlers;
    opens com.xen.rzlgame.rzl.Handlers to javafx.fxml;
}
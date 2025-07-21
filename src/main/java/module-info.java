module com.xen.rzlgame.rzl {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.xen.rzlgame.rzl to javafx.fxml;
    exports com.xen.rzlgame.rzl;
}
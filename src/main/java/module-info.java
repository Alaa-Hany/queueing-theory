module com.alaa.queueing_theory.queueingtheory {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires exp4j;
    requires jfreechart;
    requires jfreechart.fx;

    opens com.alaa.queueing_theory.app to javafx.fxml;
    exports com.alaa.queueing_theory.app;
    exports com.alaa.queueing_theory.app.controllers;
    opens com.alaa.queueing_theory.app.controllers to javafx.fxml;
}
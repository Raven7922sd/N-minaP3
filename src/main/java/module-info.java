module com.johan.nomina {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;

    opens com.johan.nomina to javafx.fxml, com.google.gson;
    exports com.johan.nomina;

}
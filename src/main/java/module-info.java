module fr.istic.prga.tp6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens fr.istic.prga.tp6 to javafx.fxml;
    exports fr.istic.prga.tp6;
    exports fr.istic.prga.tp6.controleur;
    opens fr.istic.prga.tp6.controleur to javafx.fxml;
}
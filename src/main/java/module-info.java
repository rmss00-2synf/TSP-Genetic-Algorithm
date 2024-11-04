module org.example.algorithmegenetique {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.algorithmegenetique to javafx.fxml;
    exports org.example.algorithmegenetique;
}
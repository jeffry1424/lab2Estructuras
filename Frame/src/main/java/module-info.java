module datos.lab2.frame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires com.formdev.flatlaf;
    requires com.formdev.flatlaf.extras;
    requires AbsoluteLayout.RELEASE190;


    opens datos.lab2.frame to javafx.fxml;
    exports datos.lab2.frame;
}
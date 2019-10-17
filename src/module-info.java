module JavaFXEjemplo {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports emma;
    exports emma.logic;
    exports emma.views;
    exports emma.models;

    opens emma.views to javafx.fxml;
}
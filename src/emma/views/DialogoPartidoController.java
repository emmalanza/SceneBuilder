package emma.views;
import emma.logic.Logica;
import emma.models.Partido;
import emma.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Date;

public class DialogoPartidoController {

    //hola

    @FXML
    private TextField tv_local;
    @FXML
    private TextField tv_r1;
    @FXML
    private TextField tv_r2;
    @FXML
    private TextField tv_visitante;
    @FXML
    private ComboBox<?> cb_division;
    @FXML
    private DatePicker dp_fecha;

    @FXML
    private Button btn_aceptar;
    @FXML
    void altaPartido(ActionEvent event) {

        String local, visitante, div;
        int resul1, resul2;
        Date fecha = null;

        local = tv_local.getText();
        visitante = tv_visitante.getText();
        fecha = Utils.convertirLocalDate2Date(dp_fecha.getValue());
        resul1 = Integer.parseInt(tv_r1.getText());
        resul2 = Integer.parseInt(tv_r2.getText());
        div = (String) cb_division.getSelectionModel().getSelectedItem();

        Partido p = new Partido(local, visitante, div, resul1, resul2, fecha);

        Logica.getInstance().addPartido(p);

        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }
    void modificarPartido(ActionEvent event) {

        String local, visitante, div;
        int resul1, resul2;
        Date fecha = null;

        local = tv_local.getText();
        visitante = tv_visitante.getText();
        fecha = Utils.convertirLocalDate2Date(dp_fecha.getValue());
        resul1 = Integer.parseInt(tv_r1.getText());
        resul2 = Integer.parseInt(tv_r2.getText());
        div = (String) cb_division.getSelectionModel().getSelectedItem();

        Partido p = new Partido(local, visitante, div, resul1, resul2, fecha);

        Logica.getInstance().addPartido(p);

        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }
}

package emma.views;
import emma.logic.Logica;
import emma.models.Partido;
import emma.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;


public class DialogoPartidoController extends BaseController implements Initializable {

    private Partido partidoM;
    @FXML
    private TextField tv_local;
    @FXML
    private TextField tv_r1;
    @FXML
    private TextField tv_r2;
    @FXML
    private TextField tv_visitante;
    @FXML
    private ComboBox<String> cb_division;
    @FXML
    private DatePicker dp_fecha;
    @FXML
    private Button btn_aceptar;
    @FXML
    private Button btn_cancelar;

    @FXML
    void altaModificarPartido(ActionEvent event) {

        if(partidoM!=null){

            partidoM.setLocal(tv_local.getText());
            partidoM.setVisitante(tv_visitante.getText());
            partidoM.setResul1(Integer.parseInt(tv_r1.getText()));
            partidoM.setResul2(Integer.parseInt(tv_r2.getText()));
            partidoM.setDivision((String)cb_division.getSelectionModel().getSelectedItem());
            Date date = Utils.convertirLocalDate2Date(dp_fecha.getValue());
            partidoM.setFecha(date);
            Logica.getInstance().modificarPartido(partidoM);

        }

        else {

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

        }

        Stage stage = getStage();
        stage.close();
    }

    public boolean setPartidoModificar(Partido partidoM)
    {
        this.partidoM = partidoM;

        if(partidoM!=null) {

            tv_local.setText(partidoM.getLocal());
            tv_visitante.setText(partidoM.getVisitante());
            tv_r1.setText(String.valueOf(partidoM.getResul1()));
            tv_r2.setText(String.valueOf(partidoM.getResul2()));
            cb_division.getSelectionModel().select(partidoM.getDivision());
            LocalDate localD = Utils.convertiDate2LocalDate(partidoM.getFecha());
            dp_fecha.setValue(localD);
            return true;

        } else{

            Alert alert_null = new Alert(Alert.AlertType.WARNING);
            alert_null.setTitle("OJO");
            alert_null.setContentText("¡Debes seleccionar un registro!");
            alert_null.showAndWait();
            return false;

        }
    }

    public void btn_cancelar(ActionEvent event) {
        Stage stage = getStage();
        stage.close();
    }

    public void initialize (URL url, ResourceBundle resourceBundle){

        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(tv_local, Validator.createEmptyValidator("Este campo no puede estar vacío."));
        validationSupport.registerValidator(tv_visitante, Validator.createEmptyValidator("Este campo no puede estar vacío."));
        validationSupport.registerValidator(tv_r1, Validator.createEmptyValidator("Este campo no puede estar vacío."));
        validationSupport.registerValidator(tv_r2, Validator.createEmptyValidator("Este campo no puede estar vacío."));
        validationSupport.registerValidator(cb_division, Validator.createEmptyValidator("Este campo no puede estar vacío."));
        validationSupport.registerValidator(dp_fecha, Validator.createEmptyValidator("Este campo no puede estar vacío."));


        btn_aceptar.disableProperty().bind(validationSupport.invalidProperty());

    }

}

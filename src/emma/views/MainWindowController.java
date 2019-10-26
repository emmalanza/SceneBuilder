package emma.views;

import emma.logic.Logica;
import emma.models.Partido;
import emma.views.filters.Filtro;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

        private Filtro filtro;

        @FXML
        private Menu menu_alta;

        @FXML
        private TableView<Partido> tv_partidos;

        @FXML
        private ComboBox<String> cb_filtro;

        @FXML
        private Button btn_borrar;

        @FXML
        private Button btn_cargar;

        @FXML
        private Button btn_guardar;

        @FXML
        private Button btn_salir;

        @FXML
        void altaPartido(ActionEvent event) {
            BaseController controller = cargarDialogo("DialogoPartido.fxml", 600,400);
            controller.abrirDialogo(true);
            filtrar();
        }

        @FXML
        void modificarPartido(ActionEvent event) {

            DialogoPartidoController controller =
                    (DialogoPartidoController)cargarDialogo("DialogoPartido.fxml",600,400);
            Partido partido = tv_partidos.getSelectionModel().getSelectedItem();
            boolean m = controller.setPartidoModificar(partido);
            if(m==true){
            controller.abrirDialogo(true);
            filtrar();}
                }


        @FXML
        void borrar_partido(ActionEvent event) {

            Partido p = tv_partidos.getSelectionModel().getSelectedItem();

            if (p == null) {
                Alert alert_null = new Alert(Alert.AlertType.WARNING);
                alert_null.setTitle("OJO");
                alert_null.setContentText("¡Debe seleccionar un registro!");
                alert_null.showAndWait();

            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("ALERTA");
                alert.setHeaderText("Borrado de registro.");
                alert.setContentText("¿Seguro que quiere borrar el registro?");
                alert.showAndWait();
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                ;
                if ((alert.getResult() == ButtonType.OK)) {
                    Logica.getInstance().borrarPartido(p);
                    filtrar();
                    alert2.setContentText("Partido borrado correctamente.");
                    alert2.showAndWait();
                } else {
                    alert2.setContentText("Borrado cancelado.");
                    alert2.showAndWait();
                }
            }
        }

        @FXML
        void salir(ActionEvent event){

           /* Stage stage = getStage();
            stage.close();*/

            Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
            stage.close();

        }


        public void initialize(URL url, ResourceBundle resourceBundle){
            tv_partidos.setItems(Logica.getInstance().getLista());
            filtro = new Filtro(Logica.getInstance().getLista());
            cb_filtro.getSelectionModel().selectLast();
            cb_filtro.valueProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                            tv_partidos.setItems(filtro.filtrar(newValue));

                    }
            });

        }

        private void filtrar(){
                tv_partidos.setItems(filtro.filtrar(cb_filtro.getSelectionModel().getSelectedItem()));
        }
    }


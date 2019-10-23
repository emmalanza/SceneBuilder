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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

        private Filtro filtro;

        @FXML
        private Menu menu_alta;

        @FXML
        private TableView<Partido> tv_partidos;

        @FXML
        private ComboBox<String> cb_filtro;

        @FXML
        void altaPartido(ActionEvent event) throws IOException {

                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getResource("DialogoPartido.fxml"));

                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();
                filtrar();
        }

        @FXML
        void modificarPartido(ActionEvent event) {

                try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogoPartido.fxml"));
                        Parent root = fxmlLoader.load();
                        DialogoPartidoController controller = fxmlLoader.getController();
                        Partido partido = tv_partidos.getSelectionModel().getSelectedItem();
                        controller.setPartidoModificar(partido);

                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(new Scene(root, 300, 275));
                        stage.showAndWait();
                        filtrar();
                }
                catch (IOException e)
                {
                        e.printStackTrace();
                }
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


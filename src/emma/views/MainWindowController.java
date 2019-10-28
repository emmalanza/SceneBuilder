package emma.views;

import emma.logic.Logica;
import emma.models.Partido;
import emma.views.filters.Filtro;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
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
        public void guardar (ActionEvent event) {

            FileChooser fileChooser = new FileChooser();
            //Deja al usuario solo seleccionar ciertos tipos de archivo
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(getStage());

            ObjectOutputStream oos = null;
            ArrayList<Partido> lista = new ArrayList<Partido>();

            for(int i = 0; i<Logica.getInstance().getLista().size();i++){
                lista.add((Partido) Logica.getInstance().getLista().get(i));
            }

            if(file!=null){

                try{

                    oos  = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(lista);

                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    try {
                        oos.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            }

        }


        @FXML
        public void cargar(ActionEvent actionEvent) {

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(getStage());

            ObjectInputStream ois = null;
            ArrayList<Partido> lista = new ArrayList<Partido>();

            if (file != null) {


                try{

                    ois  = new ObjectInputStream(new FileInputStream(file));
                    lista = (ArrayList) ois.readObject();

                }catch (IOException e){
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        ois.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

                for(int i = 0;i<lista.size(); i++){
                    Logica.getInstance().getLista().add(lista.get(i));
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


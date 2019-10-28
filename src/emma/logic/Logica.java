package emma.logic;

import emma.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.SimpleDateFormat;

public class Logica {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static Logica INSTANCE = null;

    private Logica() {
    }

    public static Logica getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Logica();
        return INSTANCE;
    }

    private ObservableList<Partido> listaPartidos = FXCollections.observableArrayList();


    public void addPartido(Partido partido) {
        listaPartidos.add(partido);
    }

    public void modificarPartido(Partido p) {

        int posicion = listaPartidos.indexOf(p);
        listaPartidos.set(posicion, p);
    }

    public void borrarPartido(Partido p) {
        listaPartidos.remove(p);
    }

    public ObservableList getLista() {
        return listaPartidos;
    }


}

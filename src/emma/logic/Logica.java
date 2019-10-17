package emma.logic;

import emma.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
    private ArrayList<Partido> listaPartidos2 = new ArrayList<Partido>();

    FileInputStream fis;
    FileOutputStream fos;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public void addPartido(Partido partido) {
        listaPartidos2.add(partido);
        listaPartidos.add(partido);
    }

    public void modificarPartido(Partido p, int indice) {
        listaPartidos.set(indice, p);
    }

    public void borrarPartido(int indice) {
        listaPartidos.remove(indice);
    }

    public ObservableList getLista() {
        return listaPartidos;
    }

    public ObservableList filtrar(String filtro) {
        ObservableList<Partido> lista_filtro = FXCollections.observableArrayList();
        if (filtro.equals("Primera") || filtro.equals("Segunda") || filtro.equals("Tercera")) {
            for (int i = 0; i < listaPartidos.size(); i++) {
                if (listaPartidos.get(i).getDivision().equals(filtro)) {
                    lista_filtro.add(listaPartidos.get(i));
                }
            }
            return lista_filtro;
        } else {
            return listaPartidos;
        }
    }

}

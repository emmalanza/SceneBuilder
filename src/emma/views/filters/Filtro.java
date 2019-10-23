package emma.views.filters;

import emma.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Filtro {

    private ObservableList<Partido> listaPartidos;
    private ObservableList<Partido> listaPartidosF;

    public Filtro (ObservableList<Partido>listaPartidos){

        this.listaPartidos = listaPartidos;
        listaPartidosF = FXCollections.observableArrayList();

    }

    public ObservableList<Partido> filtrar (String div){

        if(!div.equals("Todas")){

            listaPartidosF.clear();
            for (Partido partido : listaPartidos){

                if(partido.getDivision().equals(div))
                    listaPartidosF.add(partido);
            }

            return listaPartidosF;

        }else{

            return listaPartidos;
        }

    }

}

package emma.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Partido implements Comparable<Partido>, Serializable {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String local;
    private String visitante;
    private String division;
    private int resul1;
    private int resul2;
    private String marcador;
    private Date fecha;

    public Partido(String local, String visitante, String division, int resul1, int resul2, Date fecha) {
        this.local = local;
        this.visitante = visitante;
        this.division = division;
        this.resul1 = resul1;
        this.resul2 = resul2;
        this.fecha = fecha;
    }

    public String getLocal() {
        return local;
    }

    public String getVisitante() {
        return visitante;
    }

    public String getDivision() {
        return division;
    }

    public int getResul1() {
        return resul1;
    }

    public int getResul2() {
        return resul2;
    }

    public Date getFecha() { return fecha; }

    public String getSfecha() {
        String Sfecha = sdf.format(fecha);
        return Sfecha;
    }
    public String getMarcador() {
        marcador = resul1 + "-" + resul2;
        return marcador;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setResul1(int resul1) {
        this.resul1 = resul1;
    }

    public void setResul2(int resul2) {
        this.resul2 = resul2;
    }

    public void setMarcador(String marcador) {
        this.marcador = marcador;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int compareTo(Partido o) {
        return fecha.compareTo(o.fecha);
    }
}


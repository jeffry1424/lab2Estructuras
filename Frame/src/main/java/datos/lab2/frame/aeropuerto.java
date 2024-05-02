package datos.lab2.frame;

import java.util.ArrayList;

public class aeropuerto {

    String codigo, nombre, ciudad, pais;
    double latitud, longitud;
    ArrayList<vuelo> vuela = new ArrayList<>();

    public aeropuerto(String codigo, String nombre, String ciudad, String pais, double latitud, double longitud) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public void añadirVuelo(vuelo v) {
        vuela.add(v);
    }

    public String mostrar_info() {
        return "Codigo: " + codigo + "\nNombre: " + nombre + "\nLocalidad: " + ciudad + ", " + pais + "\nLatitud: " + latitud + "\nLongitud: " + longitud;
    }

}

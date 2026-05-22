package ar.edu.utn.donatrack.donaciones;

import java.util.ArrayList;

import java.util.List;

public class Categoria {
    private String nombre;
    private boolean requiereEstadoUsadoNuevo;

    private List<Subcat> subcats;

    public Categoria(String nombre, boolean requiereEstadoUsadoNuevo) {
        this.nombre = nombre;
        this.requiereEstadoUsadoNuevo = requiereEstadoUsadoNuevo;
        this.subcats = new ArrayList<>();
    }

    public void agregarSubcat(Subcat subcat) {
        this.subcats.add(subcat);
    }

    public String getNombre() { return nombre; }
    public boolean isRequiereEstadoUsadoNuevo() { return requiereEstadoUsadoNuevo; }
}

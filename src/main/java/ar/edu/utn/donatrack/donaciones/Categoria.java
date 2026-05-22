package ar.edu.utn.donatrack.donaciones;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String nombre;
    private boolean requiereEstadoUsadoNuevo;
    private List<Subcategoria> subcategorias;

    public Categoria(String nombre, boolean requiereEstadoUsadoNuevo) {
        this.nombre = nombre;
        this.requiereEstadoUsadoNuevo = requiereEstadoUsadoNuevo;
        this.subcategorias = new ArrayList<>();
    }

    public void agregarSubcategoria(Subcategoria subcategoria) {
        this.subcategorias.add(subcategoria);
    }

    public String getNombre() { return nombre; }
    public boolean isRequiereEstadoUsadoNuevo() { return requiereEstadoUsadoNuevo; }
}

package ar.edu.utn.donatrack.entidades;

import ar.edu.utn.donatrack.donaciones.Subcategoria;
import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;
import java.util.ArrayList;
import java.util.List;

public abstract class Necesidad {
    protected Subcategoria subcategoria;
    protected String descripcion;
    protected double cantidadObjetivo;
    protected List<DonacionSegmentada> donacionesRecibidas;

    public Necesidad(Subcategoria subcategoria, String descripcion, double cantidadObjetivo) {
        this.subcategoria = subcategoria;
        this.descripcion = descripcion;
        this.cantidadObjetivo = cantidadObjetivo;
        this.donacionesRecibidas = new ArrayList<>();
    }

    public void recibirDonacion(DonacionSegmentada donacion) {
        if (donacion.getSubcategoria().equals(this.subcategoria)) {
            this.donacionesRecibidas.add(donacion);
        } else {
            throw new IllegalArgumentException("La donación no corresponde a la subcategoría de la necesidad.");
        }
    }

    public abstract boolean estaSatisfecha();

    protected double getCantidadRecibida() {
        return donacionesRecibidas.stream()
                .mapToDouble(DonacionSegmentada::getTotalCantidad)
                .sum();
    }
}

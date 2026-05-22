package ar.edu.utn.donatrack.entidades;

import ar.edu.utn.donatrack.donaciones.Subcat;
import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;
import java.util.ArrayList;
import java.util.List;

public abstract class Necesidad {
    protected Subcat subcat;
    protected String descripcion;
    protected double cantDeseada;
    protected List<DonacionSegmentada> donacionesQueLlegaron;

    public Necesidad(Subcat subcat, String descripcion, double cantDeseada) {
        this.subcat = subcat;
        this.descripcion = descripcion;

        this.cantDeseada = cantDeseada;
        this.donacionesQueLlegaron = new ArrayList<>();
    }

    public void recibirDonacion(DonacionSegmentada donacion) {
        if (donacion.getSubcat().equals(this.subcat)) {
            this.donacionesQueLlegaron.add(donacion);
        } else {
            throw new IllegalArgumentException("La donación no corresponde a la subcategoría de la necesidad.");
        }
    }

    public abstract boolean estaSatisfecha();

    protected double getCantidadRecibida() {
        return donacionesQueLlegaron.stream()
                .mapToDouble(DonacionSegmentada::getTotalCantidad)
                .sum();
    }
}

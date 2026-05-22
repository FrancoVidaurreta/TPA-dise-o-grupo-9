package ar.edu.utn.donatrack.entidades;

import ar.edu.utn.donatrack.donaciones.Subcat;
import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;

public class NecesidadRecurrente extends Necesidad {
    
    
    private String periodo; 

    public NecesidadRecurrente(Subcat subcat, String descripcion, double cantDeseada, String periodo) {
        super(subcat, descripcion, cantDeseada);
        this.periodo = periodo;
    }

    @Override
    public boolean estaSatisfecha() {
        
        return getCantidadRecibida() >= cantDeseada;
    }
}

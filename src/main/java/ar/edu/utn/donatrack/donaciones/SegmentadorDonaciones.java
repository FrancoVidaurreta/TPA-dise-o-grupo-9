package ar.edu.utn.donatrack.donaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.time.LocalDate;


public class SegmentadorDonaciones {

    
    private static class ClaveAgrupacion {
        Subcat subcat;
        Boolean esUsado;
        LocalDate fechaVenc;

        public ClaveAgrupacion(Subcat subcat, Boolean esUsado, LocalDate fechaVenc) {
            this.subcat = subcat;
            this.esUsado = esUsado;
            this.fechaVenc = fechaVenc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ClaveAgrupacion that = (ClaveAgrupacion) o;
            return Objects.equals(subcat, that.subcat) &&
                   Objects.equals(esUsado, that.esUsado) &&
                   Objects.equals(fechaVenc, that.fechaVenc);
        }

        @Override
        public int hashCode() {
            return Objects.hash(subcat, esUsado, fechaVenc);
        }
    }

    public List<DonacionSegmentada> segmentar(DonacionGeneral laDonacion) {
        Map<ClaveAgrupacion, DonacionSegmentada> lasPartitas = new HashMap<>();


        for (Bien bien : laDonacion.getBienes()) {
            ClaveAgrupacion clave = new ClaveAgrupacion(
                    bien.getSubcat(),
                    bien.getEsUsado(),
                    bien.getFechaVencimiento()
            );

            DonacionSegmentada segmentada = lasPartitas.computeIfAbsent(clave, 
                k -> new DonacionSegmentada(
                    laDonacion.getDonante(), 
                    k.subcat, 
                    k.esUsado, 
                    k.fechaVenc
                )
            );
            
            segmentada.agregarBien(bien);
        }

        return new ArrayList<>(lasPartitas.values());
    }
}

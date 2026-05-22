package ar.edu.utn.donatrack.donaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.time.LocalDate;

public class SegmentadorDonaciones {

    // Clase auxiliar para agrupar bienes por los 3 criterios
    private static class ClaveAgrupacion {
        Subcategoria subcategoria;
        Boolean esUsado;
        LocalDate fechaVencimiento;

        public ClaveAgrupacion(Subcategoria subcategoria, Boolean esUsado, LocalDate fechaVencimiento) {
            this.subcategoria = subcategoria;
            this.esUsado = esUsado;
            this.fechaVencimiento = fechaVencimiento;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ClaveAgrupacion that = (ClaveAgrupacion) o;
            return Objects.equals(subcategoria, that.subcategoria) &&
                   Objects.equals(esUsado, that.esUsado) &&
                   Objects.equals(fechaVencimiento, that.fechaVencimiento);
        }

        @Override
        public int hashCode() {
            return Objects.hash(subcategoria, esUsado, fechaVencimiento);
        }
    }

    public List<DonacionSegmentada> segmentar(DonacionGeneral donacionGeneral) {
        Map<ClaveAgrupacion, DonacionSegmentada> segmentadas = new HashMap<>();

        for (Bien bien : donacionGeneral.getBienes()) {
            ClaveAgrupacion clave = new ClaveAgrupacion(
                    bien.getSubcategoria(),
                    bien.getEsUsado(),
                    bien.getFechaVencimiento()
            );

            DonacionSegmentada segmentada = segmentadas.computeIfAbsent(clave, 
                k -> new DonacionSegmentada(
                    donacionGeneral.getDonante(), 
                    k.subcategoria, 
                    k.esUsado, 
                    k.fechaVencimiento
                )
            );
            
            segmentada.agregarBien(bien);
        }

        return new ArrayList<>(segmentadas.values());
    }
}

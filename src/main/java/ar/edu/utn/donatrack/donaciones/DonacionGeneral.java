package ar.edu.utn.donatrack.donaciones;

import ar.edu.utn.donatrack.donantes.Donante;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonacionGeneral {
    private Donante donante;
    private LocalDate fechaRegistro;
    private String descripcionGeneral;
    private List<Bien> bienes;

    public DonacionGeneral(Donante donante, String descripcionGeneral) {
        this.donante = donante;
        this.descripcionGeneral = descripcionGeneral;
        this.fechaRegistro = LocalDate.now();
        this.bienes = new ArrayList<>();
    }

    public void agregarBien(Bien bien) {
        this.bienes.add(bien);
    }

    public List<Bien> getBienes() { return bienes; }
    public Donante getDonante() { return donante; }
}

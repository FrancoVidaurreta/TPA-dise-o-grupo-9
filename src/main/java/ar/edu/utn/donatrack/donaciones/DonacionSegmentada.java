package ar.edu.utn.donatrack.donaciones;

import ar.edu.utn.donatrack.donaciones.estados.EstadoDonacion;
import ar.edu.utn.donatrack.donaciones.estados.EnDeposito;
import ar.edu.utn.donatrack.donantes.Donante;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonacionSegmentada {
    private Donante donante;
    private Subcategoria subcategoria;
    private Boolean esUsado;
    private LocalDate fechaVencimiento;
    private List<Bien> bienes;
    private EstadoDonacion estado;

    public DonacionSegmentada(Donante donante, Subcategoria subcategoria, Boolean esUsado, LocalDate fechaVencimiento) {
        this.donante = donante;
        this.subcategoria = subcategoria;
        this.esUsado = esUsado;
        this.fechaVencimiento = fechaVencimiento;
        this.bienes = new ArrayList<>();
        this.estado = new EnDeposito(this);
    }

    public void agregarBien(Bien bien) {
        this.bienes.add(bien);
    }

    // Patrón State
    public void cambiarEstado(EstadoDonacion nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void asignar() { estado.asignar(); }
    public void planificarRuta() { estado.planificarRuta(); }
    public void iniciarTraslado() { estado.iniciarTraslado(); }
    public void confirmarEntrega() { estado.confirmarEntrega(); }
    public void fallarEntrega(String justificacion) { estado.fallarEntrega(justificacion); }
    public void vencer() { estado.vencer(); }

    // Getters para segmentación
    public Subcategoria getSubcategoria() { return subcategoria; }
    public Boolean getEsUsado() { return esUsado; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public double getTotalCantidad() {
        return bienes.stream().mapToDouble(Bien::getCantidad).sum();
    }
}

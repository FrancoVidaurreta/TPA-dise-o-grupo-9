package ar.edu.utn.donatrack.donaciones;

import ar.edu.utn.donatrack.donaciones.estados.EstadoDonacion;
import ar.edu.utn.donatrack.donaciones.estados.EnDeposito;
import ar.edu.utn.donatrack.donantes.Donante;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonacionSegmentada {
    private Donante donante;

    private Subcat subcat;
    private Boolean esUsado;
    private LocalDate fechaVenc;
    private List<Bien> bienes;
    private EstadoDonacion estado;

    public DonacionSegmentada(Donante donante, Subcat subcat, Boolean esUsado, LocalDate fechaVenc) {
        this.donante = donante;
        this.subcat = subcat;
        this.esUsado = esUsado;

        this.fechaVenc = fechaVenc;
        this.bienes = new ArrayList<>();
        this.estado = new EnDeposito(this);
    }

    public void agregarBien(Bien bien) {
        this.bienes.add(bien);
    }

    
    public void cambiarEstado(EstadoDonacion nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void asignar() { estado.asignar(); }
    public void planificarRuta() { estado.planificarRuta(); }
    public void iniciarTraslado() { estado.iniciarTraslado(); }
    public void confirmarEntrega() { estado.confirmarEntrega(); }
    public void fallarEntrega(String justificacion) { estado.fallarEntrega(justificacion); }
    public void vencer() { estado.vencer(); }

    
    public Subcat getSubcat() { return subcat; }
    public Boolean getEsUsado() { return esUsado; }
    public LocalDate getFechaVencimiento() { return fechaVenc; }
    public double getTotalCantidad() {
        return bienes.stream().mapToDouble(Bien::getCantidad).sum();
    }
}

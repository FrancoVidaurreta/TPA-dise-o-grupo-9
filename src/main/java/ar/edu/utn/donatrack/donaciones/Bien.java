package ar.edu.utn.donatrack.donaciones;

import java.time.LocalDate;

public class Bien {
    private String descripcion;
    private String foto; 
    private Categoria categoria;
    private Subcat subcat;
    private Boolean esUsado; 
    private LocalDate fechaVenc; 
    private double cantidad;
    private String unidadMedida; 

    public Bien(String descripcion, Subcat subcat, double cantidad, String unidadMedida) {
        this.descripcion = descripcion;
        this.subcat = subcat;
        this.categoria = subcat.getCategoria();
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    
    public void setFoto(String foto) { this.foto = foto; }
    public void setEsUsado(Boolean esUsado) { this.esUsado = esUsado; }
    public void setFechaVencimiento(LocalDate fechaVenc) { this.fechaVenc = fechaVenc; }

    
    public Subcat getSubcat() { return subcat; }
    public Boolean getEsUsado() { return esUsado; }
    public LocalDate getFechaVencimiento() { return fechaVenc; }
    public double getCantidad() { return cantidad; }
}

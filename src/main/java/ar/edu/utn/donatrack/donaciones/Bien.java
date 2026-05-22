package ar.edu.utn.donatrack.donaciones;

import java.time.LocalDate;

public class Bien {
    private String descripcion;
    private String foto; // Opcional, puede ser un path
    private Categoria categoria;
    private Subcategoria subcategoria;
    private Boolean esUsado; // true=Usado, false=Nuevo, null=No aplica
    private LocalDate fechaVencimiento; // Opcional (para perecederos)
    private double cantidad;
    private String unidadMedida; // kg, unidades, etc.

    public Bien(String descripcion, Subcategoria subcategoria, double cantidad, String unidadMedida) {
        this.descripcion = descripcion;
        this.subcategoria = subcategoria;
        this.categoria = subcategoria.getCategoria();
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    // Setters para propiedades opcionales
    public void setFoto(String foto) { this.foto = foto; }
    public void setEsUsado(Boolean esUsado) { this.esUsado = esUsado; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    // Getters
    public Subcategoria getSubcategoria() { return subcategoria; }
    public Boolean getEsUsado() { return esUsado; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public double getCantidad() { return cantidad; }
}

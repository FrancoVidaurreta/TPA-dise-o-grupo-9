package ar.edu.utn.donatrack.donaciones;

public class Subcat {
    private String nombre;

    private Categoria categoria;

    public Subcat(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public String getNombre() { return nombre; }
    public Categoria getCategoria() { return categoria; }
}

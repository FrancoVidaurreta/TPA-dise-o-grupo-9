package ar.edu.utn.donatrack.donantes;

import ar.edu.utn.donatrack.notificaciones.MedioDeContacto;
import java.util.ArrayList;
import java.util.List;

public class Representante {
    private String nombre;
    private String apellido;
    private List<MedioDeContacto> mediosDeContacto;

    public Representante(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeContacto = new ArrayList<>();
    }

    public void agregarMedioDeContacto(MedioDeContacto medio) {
        this.mediosDeContacto.add(medio);
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
}

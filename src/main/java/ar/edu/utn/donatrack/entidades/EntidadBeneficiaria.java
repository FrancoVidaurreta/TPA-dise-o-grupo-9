package ar.edu.utn.donatrack.entidades;

import ar.edu.utn.donatrack.donantes.Representante;
import java.util.ArrayList;
import java.util.List;

public class EntidadBeneficiaria {
    private String razonSocial;
    private String direccion;
    private String telefono;
    private List<Representante> representantes;
    private List<Necesidad> necesidades;

    public EntidadBeneficiaria(String razonSocial, String direccion, String telefono) {
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.representantes = new ArrayList<>();
        this.necesidades = new ArrayList<>();
    }

    public void agregarRepresentante(Representante representante) {
        this.representantes.add(representante);
    }

    public void registrarNecesidad(Necesidad necesidad) {
        this.necesidades.add(necesidad);
    }

    public List<Necesidad> getNecesidades() { return necesidades; }
    public String getRazonSocial() { return razonSocial; }
}

package ar.edu.utn.donatrack.donantes;

import java.util.ArrayList;
import java.util.List;

public class PersonaJuridica extends Donante {
    private String razonSocial;
    private TipoOrganizacion tipo;
    private String rubro;
    private String cuit;
    private List<Representante> representantes;

    public PersonaJuridica(String razonSocial, TipoOrganizacion tipo, String rubro, String cuit, String correoElectronico) {
        super();
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
        this.cuit = cuit;
        this.representantes = new ArrayList<>();

        
        ar.edu.utn.donatrack.notificaciones.MedioDeContacto medioEmail = 
            new ar.edu.utn.donatrack.notificaciones.MedioDeContacto(correoElectronico, new ar.edu.utn.donatrack.notificaciones.NotificadorMail());
        this.agregarMedioDeContacto(medioEmail);
    }

    public void agregarRepresentante(Representante representante) {
        this.representantes.add(representante);
    }

    
    public String getRazonSocial() { return razonSocial; }
    public String getCuit() { return cuit; }
    public List<Representante> getRepresentantes() { return representantes; }
}

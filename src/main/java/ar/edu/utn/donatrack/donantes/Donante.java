package ar.edu.utn.donatrack.donantes;

import ar.edu.utn.donatrack.notificaciones.MedioDeContacto;
import java.util.ArrayList;
import java.util.List;

public abstract class Donante {
    protected List<MedioDeContacto> formitasDeContacto;
    protected MedioDeContacto contactoFav;

    public Donante() {
        this.formitasDeContacto = new ArrayList<>();
    }

    public void agregarMedioDeContacto(MedioDeContacto medio) {
        this.formitasDeContacto.add(medio);
        if (this.contactoFav == null) {
            this.contactoFav = medio;
        }
    }

    public void setMedioDeContactoPredeterminado(MedioDeContacto medio) {
        if (!formitasDeContacto.contains(medio)) {
            formitasDeContacto.add(medio);
        }
        this.contactoFav = medio;
    }

    public void notificar(String mensaje) {
        if (contactoFav != null) {
            contactoFav.notificar(mensaje);

        }
    }

    public List<MedioDeContacto> getMediosDeContacto() {
        return formitasDeContacto;
    }
}

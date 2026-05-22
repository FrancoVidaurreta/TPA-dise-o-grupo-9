package ar.edu.utn.donatrack.donantes;

import ar.edu.utn.donatrack.notificaciones.MedioDeContacto;
import java.util.ArrayList;
import java.util.List;

public abstract class Donante {
    protected List<MedioDeContacto> mediosDeContacto;
    protected MedioDeContacto medioDeContactoPredeterminado;

    public Donante() {
        this.mediosDeContacto = new ArrayList<>();
    }

    public void agregarMedioDeContacto(MedioDeContacto medio) {
        this.mediosDeContacto.add(medio);
        if (this.medioDeContactoPredeterminado == null) {
            this.medioDeContactoPredeterminado = medio;
        }
    }

    public void setMedioDeContactoPredeterminado(MedioDeContacto medio) {
        if (!mediosDeContacto.contains(medio)) {
            mediosDeContacto.add(medio);
        }
        this.medioDeContactoPredeterminado = medio;
    }

    public void notificar(String mensaje) {
        if (medioDeContactoPredeterminado != null) {
            medioDeContactoPredeterminado.notificar(mensaje);
        }
    }

    public List<MedioDeContacto> getMediosDeContacto() {
        return mediosDeContacto;
    }
}

package ar.edu.utn.donatrack.notificaciones;

public class MedioDeContacto {
    private String valor; 
    private Notificador notificador;

    public MedioDeContacto(String valor, Notificador notificador) {
        this.valor = valor;
        this.notificador = notificador;
    }

    public void notificar(String mensaje) {
        this.notificador.enviar(this.valor, mensaje);
    }

    public String getValor() {
        return valor;
    }
}

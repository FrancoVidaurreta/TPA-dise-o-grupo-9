package ar.edu.utn.donatrack.notificaciones;

public class MedioDeContacto {
    private String valor; // puede ser un mail o un numero o lo que esa
    private Notificador notificador;// un objeto que puede ser notificadorMail o sms o wpp

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

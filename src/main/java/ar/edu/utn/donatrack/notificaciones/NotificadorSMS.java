package ar.edu.utn.donatrack.notificaciones;

public class NotificadorSMS implements Notificador {
    @Override
    public void enviar(String contacto, String mensaje) {
        System.out.println("Simulando envío de SMS al número " + contacto + ": " + mensaje);
    }
}

package ar.edu.utn.donatrack.notificaciones;

public class NotificadorMail implements Notificador {
    @Override
    public void enviar(String contacto, String mensaje) {
        // En una futura iteración esto llamará a una API (ej. SendGrid)
        System.out.println("Simulando envío de EMAIL a " + contacto + ": " + mensaje);
    }
}

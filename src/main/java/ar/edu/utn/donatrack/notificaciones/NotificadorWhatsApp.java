package ar.edu.utn.donatrack.notificaciones;

public class NotificadorWhatsApp implements Notificador {
    @Override
    public void enviar(String contacto, String mensaje) {
        // En una futura iteración esto llamará a una API (ej. Twilio)
        System.out.println("Simulando envío de WHATSAPP al número " + contacto + ": " + mensaje);
    }
}

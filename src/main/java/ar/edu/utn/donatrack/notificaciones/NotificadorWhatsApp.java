package ar.edu.utn.donatrack.notificaciones;

public class NotificadorWhatsApp implements Notificador {
    @Override
    public void enviar(String contacto, String mensaje) {
        
        System.out.println("mandando un WHATSAPP al número " + contacto + ": " + mensaje);
    }
}

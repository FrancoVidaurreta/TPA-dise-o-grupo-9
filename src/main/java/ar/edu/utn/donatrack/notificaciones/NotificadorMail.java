package ar.edu.utn.donatrack.notificaciones;

public class NotificadorMail implements Notificador {
    @Override
    public void enviar(String contacto, String mensaje) {
        
        System.out.println("mandando un EMAIL a " + contacto + ": " + mensaje);
    }
}

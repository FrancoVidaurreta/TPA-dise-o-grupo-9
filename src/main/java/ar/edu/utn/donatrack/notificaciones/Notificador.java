package ar.edu.utn.donatrack.notificaciones;

public interface Notificador {
    /**
     * Envia un mensaje a un destinatario simulando la integración con servicios externos.
     * @param contacto El medio de contacto (mail, teléfono, etc.)
     * @param mensaje El contenido del mensaje
     */
    void enviar(String contacto, String mensaje);
}

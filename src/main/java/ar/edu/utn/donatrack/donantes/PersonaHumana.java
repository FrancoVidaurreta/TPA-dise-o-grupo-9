package ar.edu.utn.donatrack.donantes;

public class PersonaHumana extends Donante {
    private String nombre;
    private String apellido;
    private int edad;
    private String documento; 
    private String genero;
    private String direccion;

    public PersonaHumana(String nombre, String apellido, int edad, String documento, String genero, String direccion, String correoElectronico) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.documento = documento;
        this.genero = genero;
        this.direccion = direccion;
        
        
        ar.edu.utn.donatrack.notificaciones.MedioDeContacto medioEmail = 
            new ar.edu.utn.donatrack.notificaciones.MedioDeContacto(correoElectronico, new ar.edu.utn.donatrack.notificaciones.NotificadorMail());
        this.agregarMedioDeContacto(medioEmail);
    }

    
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDocumento() { return documento; }
}

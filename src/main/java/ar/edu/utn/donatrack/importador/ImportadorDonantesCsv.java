package ar.edu.utn.donatrack.importador;

import ar.edu.utn.donatrack.donantes.Donante;
import ar.edu.utn.donatrack.donantes.PersonaHumana;
import ar.edu.utn.donatrack.donantes.PersonaJuridica;
import ar.edu.utn.donatrack.donantes.TipoOrganizacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportadorDonantesCsv {

    // Simula una base de datos de donantes en memoria (por email como clave para
    // simplificar)
    private Map<String, Donante> donantesExistentes = new HashMap<>();

    public void importar(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltar encabezado
                }

                String[] datos = linea.split(",");
                // Formato: TipoPersona, TipoDoc, Documento, Nombre/Razon Social, Email,
                // Telefono
                if (datos.length < 6)
                    continue;

                String tipoPersona = datos[0].trim();
                String tipoDoc = datos[1].trim();
                String documento = datos[2].trim();
                String nombreOrazonSocial = datos[3].trim();
                String email = datos[4].trim();
                String telefono = datos[5].trim();
                Donante donante = donantesExistentes.get(email);

                if (donante == null) {
                    // Crear nuevo donante
                    if ("HUMANA".equalsIgnoreCase(tipoPersona)) {
                        // Separar nombre y apellido de forma simplificada
                        String[] nombreCompleto = nombreOrazonSocial.split(" ", 2);
                        String nombre = nombreCompleto[0];
                        String apellido = nombreCompleto.length > 1 ? nombreCompleto[1] : "";

                        donante = new PersonaHumana(nombre, apellido, 0, documento, "", "");
                    } else if ("JURIDICA".equalsIgnoreCase(tipoPersona)) {
                        donante = new PersonaJuridica(nombreOrazonSocial, TipoOrganizacion.EMPRESA, "", documento);
                    }

                    if (donante != null) {
                        donantesExistentes.put(email, donante);
                    }
                } else {
                    // Actualizar donante existente (Lógica simplificada para la Entrega 1)
                    // En un escenario real, se actualizarían los campos correspondientes.
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public Map<String, Donante> getDonantesExistentes() {
        return donantesExistentes;
    }
}

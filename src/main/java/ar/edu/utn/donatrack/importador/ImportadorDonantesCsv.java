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

    private Map<String, Donante> genteAnotada = new HashMap<>();

    public void importar(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            boolean esLaPrimera = true;

            while ((linea = br.readLine()) != null) {
                if (esLaPrimera) {
                    esLaPrimera = false;
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length < 6)
                    continue;

                String tipoPersona = datos[0].trim();
                String tipoDoc = datos[1].trim();
                String documento = datos[2].trim();
                String nombrecito = datos[3].trim();
                String email = datos[4].trim();

                String telefono = datos[5].trim();
                Donante donante = genteAnotada.get(email);

                if (donante == null) {

                    if ("HUMANA".equalsIgnoreCase(tipoPersona)) {

                        String[] nombreCompleto = nombrecito.split(" ", 2);
                        String nombre = nombreCompleto[0];
                        String apellido = nombreCompleto.length > 1 ? nombreCompleto[1] : "";

                        donante = new PersonaHumana(nombre, apellido, 0, documento, "", "", email);
                    } else if ("JURIDICA".equalsIgnoreCase(tipoPersona)) {
                        donante = new PersonaJuridica(nombrecito, TipoOrganizacion.EMPRESA, "", documento, email);
                    }

                    if (donante != null) {
                        genteAnotada.put(email, donante);
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public Map<String, Donante> getDonantesExistentes() {
        return genteAnotada;
    }
}

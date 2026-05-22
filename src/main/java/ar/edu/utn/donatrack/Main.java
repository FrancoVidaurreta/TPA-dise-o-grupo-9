package ar.edu.utn.donatrack;

import ar.edu.utn.donatrack.donaciones.*;
import ar.edu.utn.donatrack.donantes.PersonaHumana;
import ar.edu.utn.donatrack.entidades.EntidadBeneficiaria;
import ar.edu.utn.donatrack.entidades.NecesidadExtraordinaria;
import ar.edu.utn.donatrack.importador.ImportadorDonantesCsv;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Iniciando el sistemaa DonaTrack ===");
        DonaTrackSistema plataforma = new DonaTrackSistema();

        
        System.out.println("\n--- Trayendo donantes desde CSV ---");
        ImportadorDonantesCsv importador = new ImportadorDonantesCsv();
        
        importador.importar("donantes_import_20000_UTF8_BOM.csv");
        
        
        PersonaHumana ana = new PersonaHumana("Ana", "Perez", 30, "12345678", "F", "Calle Falsa 123", "ana@mail.com");
        plataforma.registrarDonante(ana);
        System.out.println("Donante registrada: " + ana.getNombre());


        
        Categoria catMobiliario = new Categoria("Mobiliario", true);

        Subcat sillas = new Subcat("Sillas", catMobiliario);
        Subcat mesas = new Subcat("Mesas", catMobiliario);

        Categoria catAlimentos = new Categoria("Alimentos", false);
        Subcat fideos = new Subcat("Fideos Secos", catAlimentos);

        
        System.out.println("\n--- Ana realiza una donacion en el depósito ---");
        DonacionGeneral donacion = new DonacionGeneral(ana, "Mudanza y limpieza de alacena");
        
        Bien sillaUsada1 = new Bien("Silla de madera", sillas, 1, "unidades");
        sillaUsada1.setEsUsado(true);
        Bien sillaUsada2 = new Bien("Silla de madera", sillas, 1, "unidades");
        sillaUsada2.setEsUsado(true);
        Bien mesaUsada = new Bien("Mesa de roble", mesas, 1, "unidades");
        mesaUsada.setEsUsado(true);
        Bien fideosNuevos = new Bien("Paquete fideos", fideos, 10, "paquetes");
        fideosNuevos.setFechaVencimiento(LocalDate.of(2027, 1, 1));
        
        donacion.agregarBien(sillaUsada1);
        donacion.agregarBien(sillaUsada2);
        donacion.agregarBien(mesaUsada);
        donacion.agregarBien(fideosNuevos);

        plataforma.ingresarDonacion(donacion);

        
        System.out.println("\n--- Asi quedo el depo ---");

        for (DonacionSegmentada ds : plataforma.getDonacionesEnDeposito()) {
            System.out.println("Subcat: " + ds.getSubcat().getNombre() + 
                               " | Cantidad total: " + ds.getTotalCantidad() + 

                               " | Usado: " + ds.getEsUsado());
        }

        
        System.out.println("\n--- anotando necesidades ---");

        EntidadBeneficiaria escuela = new EntidadBeneficiaria("Escuela Rural 10", "Ruta 40 km 12", "11223344");
        plataforma.registrarEntidad(escuela);
        NecesidadExtraordinaria necSillas = new NecesidadExtraordinaria(sillas, "Reposición por inundación", 2);
        escuela.registrarNecesidad(necSillas);
        System.out.println("La Escuela Rural 10 necesita 2 Sillas. Satisfecha? " + necSillas.estaSatisfecha());

        
        System.out.println("\n--- dando las cosas ---");
        
        DonacionSegmentada donacionSillas = plataforma.getDonacionesEnDeposito().stream()
                .filter(ds -> ds.getSubcat() == sillas).findFirst().get();
        
        donacionSillas.asignar(); 
        necSillas.recibirDonacion(donacionSillas);
        
        System.out.println("Se asignaron las sillas a la escuela.");
        System.out.println("Necesidad de sillas satisfecha? " + necSillas.estaSatisfecha());

        
        System.out.println("\n--- movimiento logistico ---");
        donacionSillas.planificarRuta();

        donacionSillas.iniciarTraslado();
        donacionSillas.confirmarEntrega();
        
        System.out.println("Estado de la donacion cambiado hasta 'Entregada'.");
        
        
        System.out.println("\n--- avisando al donante ---");
        ana.notificar("¡Tus donaciones de sillas ya fueron entregadas a la Escuela Rural 10! Gracias por colaborar.");
    }
}

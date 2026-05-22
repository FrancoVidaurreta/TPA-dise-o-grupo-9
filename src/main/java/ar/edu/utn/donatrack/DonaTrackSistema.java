package ar.edu.utn.donatrack;

import ar.edu.utn.donatrack.donaciones.DonacionGeneral;

import ar.edu.utn.donatrack.donaciones.DonacionSegmentada;
import ar.edu.utn.donatrack.donaciones.SegmentadorDonaciones;
import ar.edu.utn.donatrack.donantes.Donante;

import ar.edu.utn.donatrack.entidades.EntidadBeneficiaria;

import java.util.ArrayList;
import java.util.List;

public class DonaTrackSistema {
    private List<Donante> donantes;
    private List<EntidadBeneficiaria> entidades;

    private List<DonacionSegmentada> donacionesEnDeposito;
    private SegmentadorDonaciones segmentador;

    public DonaTrackSistema() {
        this.donantes = new ArrayList<>();
        this.entidades = new ArrayList<>();
        this.donacionesEnDeposito = new ArrayList<>();
        this.segmentador = new SegmentadorDonaciones();
    }

    public void registrarDonante(Donante donante) {
        this.donantes.add(donante);
    }

    public void registrarEntidad(EntidadBeneficiaria entidad) {
        this.entidades.add(entidad);

    }

    public void ingresarDonacion(DonacionGeneral laDonacion) {
        
        List<DonacionSegmentada> lasPartitas = this.segmentador.segmentar(laDonacion);
        
        
        this.donacionesEnDeposito.addAll(lasPartitas);
        System.out.println("se dividio la dona en la donacion en " + lasPartitas.size() + " sub-donaciones y están en depósito.");
    }

    public List<DonacionSegmentada> getDonacionesEnDeposito() {
        return donacionesEnDeposito;
    }

    public List<Donante> getDonantes() {
        return donantes;
    }
}

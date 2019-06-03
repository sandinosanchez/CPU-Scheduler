package com.SimuladorSO;

import java.util.ArrayList;

public class ParticionFijaFf extends Memoria {

    public ParticionFijaFf(Integer tamMemoria) {
        super(tamMemoria);
    }

    public ParticionFijaFf(Integer tamMemoria, Integer cantParticiones, ArrayList<Integer> tamParticiones) {
        super(tamMemoria, cantParticiones, tamParticiones);
    }

    public ParticionFijaFf() {
    }

    @Override
    boolean cargarProceso(Proceso proceso) {
        for (Particion p: super.getParticionesArrayList()) {
            if (p.particionVacia() && proceso.getTam() <= p.getTamParticion()) {
                p.setProcesoEnParticion(proceso);
                return true;
            }
        }
        return false;
    }
}

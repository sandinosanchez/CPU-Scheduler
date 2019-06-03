package com.SimuladorSO;

import java.util.Collections;

public class SimuladorSRTF extends ModuloCPU {

    SimuladorSRTF(Cola colaNuevos, Memoria memoriaPrincipal) {
        super(colaNuevos, memoriaPrincipal);
    }


    // Retorna true si la rafaga de el siguiente proceso a ejecutarse es menor que el proceso en ejecucion
    public boolean getProcesoMenorTiempo(int raf){
        return raf > getColaListos().getCola().get(1).getRafagaEjec();
    }

    @Override
    void ejecutarProceso(int clock) {
        int rafagaEjec = super.getProcesoEnEjecucionCPU().getRafagaEjec();
        if (getProcesoMenorTiempo(rafagaEjec)){
            Collections.swap(super.getColaListos().getCola(), 1, 0);
        }
        super.getColaListos().getProcesoAEjecutar().ejecutarCiclo(clock);
    }

    @Override
    void ordernarColaListos() {
            super.orderBySJ();
    }
}

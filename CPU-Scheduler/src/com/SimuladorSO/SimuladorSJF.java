package com.SimuladorSO;

public class SimuladorSJF extends ModuloCPU{

    SimuladorSJF(Cola colaNuevos, Memoria memoriaPrincipal) {
        super(colaNuevos, memoriaPrincipal);
    }

    @Override
    void ejecutarProceso(int clock) {
        super.getColaListos().getProcesoAEjecutar().ejecutarCiclo(clock);
    }

    @Override
    void ordernarColaListos() {
        super.orderBySJ();
    }

}

package com.SimuladorSO;

import java.util.Collections;

public class SimuladorRR extends ModuloCPU {

    private int quantum;
    private int tempQuantum;

    SimuladorRR(Cola colaNuevos, Memoria memoriaPrincipal, int quuantum) {
        super(colaNuevos, memoriaPrincipal);
        this.quantum = quuantum;
        this.tempQuantum = quantum;
    }

    @Override
    void ejecutarProceso(int clock) {
        super.getProcesoEnEjecucionCPU().ejecutarCiclo(clock);
        tempQuantum--;
        if (tempQuantum == 0) {
            if (!super.getProcesoEnEjecucionCPU().isFinished())
                Collections.rotate(super.getColaListos().getCola(), -1);
            tempQuantum = quantum;
        }
    }

    @Override
    void ordernarColaListos() {
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getTempQuantum() {
        return tempQuantum;
    }

    public void setTempQuantum(int tempQuantum) {
        this.tempQuantum = tempQuantum;
    }
}

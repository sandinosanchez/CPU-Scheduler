package com.SimuladorSO;


import java.util.ArrayList;

public class Simulador {

    // Memoria del simulador
    private Memoria memoriaPrincipal;

    // Distintas colas
    private Cola colaListos;
    private Cola colaNuevos;
    private Cola colaES;

    // Clock
    private int clockSimulador;

    // Algoritmo seleccionado para la simulacion
    private ModuloCPU algoritmoPrincipalCpu;

    // Proceso en ejecucion
    private Proceso procesoEjecucion;

    // CPU ocupada
    private boolean cpuOcupada;


    private Simulador(Memoria memoriaPrincipal, ArrayList<Proceso> colaNuevos){

        // Inicializo la memoria del simulador
        this.memoriaPrincipal = memoriaPrincipal;

        // Inicializo el clock del simulador
        this.clockSimulador = 0;

        // Inicializo las distintas colas del simulador
        this.colaES = new Cola();
        this.colaNuevos = new Cola(colaNuevos.size(), colaNuevos);
        this.colaListos = new Cola();

        // Inicializo a la cpu como desocupada
        this.cpuOcupada = false;

        // Inicializo el proceso en ejecucion a null
        this.procesoEjecucion = null;

    }


    // Getters y Setters

    public Memoria getMemoriaPrincipal() {
        return memoriaPrincipal;
    }

    public void setMemoriaPrincipal(Memoria memoriaPrincipal) {
        this.memoriaPrincipal = memoriaPrincipal;
    }

    public Cola getColaListos() {
        return colaListos;
    }

    public void setColaListos(Cola colaListos) {
        this.colaListos = colaListos;
    }

    public Cola getColaNuevos() {
        return colaNuevos;
    }

    public void setColaNuevos(Cola colaNuevos) {
        this.colaNuevos = colaNuevos;
    }

    public Cola getColaES() {
        return colaES;
    }

    public void setColaES(Cola colaES) {
        this.colaES = colaES;
    }

    public int getClockSimulador() {
        return clockSimulador;
    }

    public void setClockSimulador(int clockSimulador) {
        this.clockSimulador = clockSimulador;
    }

    public ModuloCPU getAlgoritmoPrincipalCpu() {
        return algoritmoPrincipalCpu;
    }

    public void setAlgoritmoPrincipalCpu(ModuloCPU algoritmoPrincipalCpu) {
        this.algoritmoPrincipalCpu = algoritmoPrincipalCpu;
    }

    public Proceso getProcesoEjecucion() {
        return procesoEjecucion;
    }

    public void setProcesoEjecucion(Proceso procesoEjecucion) {
        this.procesoEjecucion = procesoEjecucion;
    }

    public boolean isCpuOcupada() {
        return cpuOcupada;
    }

    public void setCpuOcupada(boolean cpuOcupada) {
        this.cpuOcupada = cpuOcupada;
    }
}

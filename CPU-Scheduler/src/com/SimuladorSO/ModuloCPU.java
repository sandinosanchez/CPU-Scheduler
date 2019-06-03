package com.SimuladorSO;

import java.util.ArrayList;

public abstract class ModuloCPU {

    private Cola colaNuevos;
    private Cola colaListos;
    private Cola colaES;

    private int clock;

    private Memoria memoriaPrincipal;

    private Proceso procesoEnEjecucion;

    private boolean cpuOcupada;

    ModuloCPU(Cola colaNuevos, Memoria memoriaPrincipal){
        this.colaNuevos = colaNuevos;
        this.colaES = new Cola();
        this.colaListos = new Cola();
        this.memoriaPrincipal = memoriaPrincipal;
        this.cpuOcupada = false;
        this.clock = 0;
    }


    abstract void ejecutarProceso(int clock);

    public Cola getColaNuevos() {
        return colaNuevos;
    }

    public String memoriaToString(){
        return memoriaPrincipal.toString();
    }

    public void cargarColaMemoria(int clock){
        for (Proceso p: colaNuevos.getCola()) {
            if (p.gettArribo() == clock){
                memoriaPrincipal.addColaMemoria(p);
            }
        }
    }



    public void cargarMemoria() {
        ArrayList<Proceso> deleteList = new ArrayList<>();
        for (Proceso p : memoriaPrincipal.getColaMemoria()) {
            if (memoriaPrincipal.cargarProceso(p)) {
                colaListos.agregarProceso(p);
                deleteList.add(p);
            }
        }
        for (Proceso p: deleteList) {
            memoriaPrincipal.getColaMemoria().remove(p);
        }

    }

    public void ejecutarProcesoES(int clock){
        if (!colaES.getCola().isEmpty())
            colaES.getProcesoAEjecutar().ejecutarCiclo(clock);
    }

    public void cargarColaES(){
        if (procesoEnEjecucion.tipoRafaga() == 1){
            colaNuevos.getCola().add(procesoEnEjecucion);
            colaListos.getCola().remove(procesoEnEjecucion);
        }
    }

    public void incClock(){
        clock++;
    }

    public boolean elimProcesoTerm(){
        if (getProcesoEnEjecucionCPU().isFinished()){
            colaListos.eliminarProceso(procesoEnEjecucion);
            memoriaPrincipal.quitarProceso(procesoEnEjecucion);
            return true;
        }
        return false;
    }

    abstract void ordernarColaListos();

    public void orderBySJ(){
        colaListos.ordenarByRafagaTotalRestante();
    }

    public void ordrByTiempoArrivo(){
        colaNuevos.ordenarByTiempoArrivo();
    }

    public void setColaNuevos(Cola colaNuevos) {
        this.colaNuevos = colaNuevos;
    }

    public Cola getColaListos() {
        return colaListos;
    }

    public void setColaListos(Cola colaListos) {
        this.colaListos = colaListos;
    }

    public Cola getColaES() {
        return colaES;
    }

    public void setColaES(Cola colaES) {
        this.colaES = colaES;
    }

    public Proceso getProcesoEnEjecucionCPU() {
        return procesoEnEjecucion;
    }

    public Proceso getProcEjecES(){
        return colaES.getCola().get(0);
    }

    public void setProcesoEnEjecucion() {
        this.procesoEnEjecucion = colaListos.getProcesoAEjecutar();
    }

    public void setProcesoEnEjecucion(int index){
        this.procesoEnEjecucion = colaListos.getCola().get(index);
    }

    public boolean isCpuOcupada() {
        return cpuOcupada;
    }

    public void setCpuOcupada() {
        cpuOcupada = !cpuOcupada;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public Memoria getMemoriaPrincipal() {
        return memoriaPrincipal;
    }
}

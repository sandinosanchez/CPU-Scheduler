package com.SimuladorSO;

import java.util.ArrayList;
import java.util.Comparator;

public class Cola {

    private ArrayList<Proceso> cola;
    private int colaSize;

    public Cola(int colaSize, ArrayList<Proceso> cola) {
        this.cola = cola;
        this.colaSize = colaSize;
    }

    public Cola (){
        this.cola = new ArrayList<>();
    }

    public void ordenarByTiempoArrivo(){
        cola.sort(Comparator.comparing(Proceso::gettArribo));
    }

    public void ordenarByRafagaTotalRestante(){
        cola.sort(Comparator.comparing(Proceso::getRafagaRestanteTotal));
    }

    public Proceso getProceso(int pid){
        return cola.get(pid);
    }

    public Proceso getProcesoAEjecutar(){
        return cola.get(0);
    }

    public void eliminarProceso(Proceso p){
        cola.remove(p);
    }

    public void agregarProceso(Proceso p){
        cola.add(p);
    }

    public boolean colaVacia(){
        return cola.isEmpty();
    }

    public int tamCola(){
        return cola.size();
    }

    public int getColaSize() {
        return colaSize;
    }

    public void setColaSize(int size){
        this.colaSize = size;
    }

    public ArrayList<Proceso> getCola() {
        return cola;
    }

    @Override
    public String toString() {
        return "Cola{" +
                "cola=" + cola.toString() +
                ", colaSize=" + colaSize +
                '}';
    }
}

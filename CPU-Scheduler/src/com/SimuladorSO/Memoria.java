package com.SimuladorSO;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Memoria {

    private SimpleIntegerProperty tamMemoria;
    private ArrayList<Particion> particionesArrayList;
    private ArrayList<Proceso> colaMemoria;

    public Memoria(Integer tamMemoria) {
        this.tamMemoria = new SimpleIntegerProperty(tamMemoria);
        this.particionesArrayList = new ArrayList<>();
        Particion p = new Particion(tamMemoria);
        getParticionesArrayList().add(p);
        this.colaMemoria = new ArrayList<>();
    }

    public Memoria (Integer tamMemoria, Integer cantParticiones, ArrayList<Integer> tamParticiones){
        this.tamMemoria = new SimpleIntegerProperty(tamMemoria);
        this.particionesArrayList = new ArrayList<>(cantParticiones);
        this.colaMemoria = new ArrayList<>();
        for (int i = 0; i < cantParticiones ; i++) {
            Particion nuevaParticion = new Particion(tamParticiones.get(i));
            this.particionesArrayList.add(nuevaParticion);
        }
    }

    public Memoria(){}


    abstract boolean cargarProceso(Proceso proceso);

    public String colaMemoriaToString(){
        return colaMemoria.toString();
    }

    @Override
    public String toString() {
        return "Memoria{" +
                tamMemoria.getValue() +
                ", Particiones = " + arrayToString() +
                '}';
    }

    public void addColaMemoria(Proceso p){
        colaMemoria.add(p);
    }

    public String arrayToString(){
        String output;
        StringBuilder finalOutput = new StringBuilder();
        for (Particion p: particionesArrayList) {
                output = "(" +
                        particionesArrayList.indexOf(p) +
                        "," + p.getTamParticion();
                        //", "+ p.getProcesoEnParticion().toString() + ")";
//                if (p.getProcesoEnParticion().toString() != null) {
//                    output += ", " + p.getProcesoEnParticion().toString() + ")";
//                }
                try {
                    output += (", "+ p.getProcesoEnParticion().toString() + ")");
                    //output.concat(", "+ p.getProcesoEnParticion().toString() + ")");
                } catch (Exception e){
                    System.out.println("El proceso es demaciado grande");
                }
                finalOutput.append(output);
        }
        return finalOutput.toString();
    }

    public void quitarProceso(Proceso p){
        int part = 0;
        for (Particion pt: particionesArrayList) {
            if (pt.getProcesoEnParticion() == p)
                part = particionesArrayList.indexOf(pt);
        }
        particionesArrayList.get(part).setProcesoEnParticion(null);
    }

    // Devuelve el espacio libre de la memoria
    public int espacioLibre(){
        int espacioLibre = 0;
        for (Particion p: particionesArrayList)
            if (p.particionVacia()) espacioLibre += p.getTamParticion();
        return espacioLibre;
    }
    // Ordenar lista de particiones por tamaño
    public void ordernarByTamParticion(){
        particionesArrayList.sort(Comparator.comparing(Particion::getTamParticion));
    }

    public int getTamMemoria() {
        return tamMemoria.get();
    }

    public SimpleIntegerProperty tamMemoriaProperty() {
        return tamMemoria;
    }

    public void setTamMemoria(int tamMemoria) {
        this.tamMemoria.set(tamMemoria);
    }

    public ArrayList<Particion> getParticionesArrayList() {
        return particionesArrayList;
    }

    public void setParticionesArrayList(ArrayList<Particion> particionesArrayList) {
        this.particionesArrayList = particionesArrayList;
    }

    public ArrayList<Proceso> getColaMemoria() {
        return colaMemoria;
    }

    public void setColaMemoria(ArrayList<Proceso> colaMemoria) {
        this.colaMemoria = colaMemoria;
    }
}

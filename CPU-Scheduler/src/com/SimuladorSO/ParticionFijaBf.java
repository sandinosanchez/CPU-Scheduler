package com.SimuladorSO;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParticionFijaBf extends Memoria {

    public ParticionFijaBf(Integer tamMemoria) {
        super(tamMemoria);
    }

    public ParticionFijaBf(Integer tamMemoria, Integer cantParticiones, ArrayList<Integer> tamParticiones) {
        super(tamMemoria, cantParticiones, tamParticiones);
    }

    public ParticionFijaBf() {
    }

    @Override
    public boolean cargarProceso(Proceso proceso) {
        HashMap<Integer, Integer> bestFitMap = new HashMap<>();
        for (Particion p: super.getParticionesArrayList()) {
            if (p.particionVacia() && p.getTamParticion() >= proceso.getTam()) {
                // Carga el index de las particiones libres con sus respectiva fragmentacion externa
                bestFitMap.put(super.getParticionesArrayList().indexOf(p), p.getTamParticion() - proceso.getTam());
            }
        }
        if (!bestFitMap.isEmpty()) {
            // Extrae la particion en la cual se produce menor fragmentacion interna
            int min = bestFitMap.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
            super.getParticionesArrayList().get(min).setProcesoEnParticion(proceso);
            return true;
        }
        return false;
    }

//    private SimpleIntegerProperty tamMemoria;
//    private ArrayList<Particion> particionesArrayList;
//
//
//    public ParticionFijaBf (Integer tamMemoria, Integer cantParticiones, ArrayList<Integer> tamParticiones){
//        this.tamMemoria = new SimpleIntegerProperty(tamMemoria);
//        this.particionesArrayList = new ArrayList<>(cantParticiones);
//        for (int i = 0; i < cantParticiones ; i++) {
//            Particion nuevaParticion = new Particion(tamParticiones.get(i));
//            this.particionesArrayList.add(nuevaParticion);
//        }
//    }
//
//
//
//    @Override
//    void cargaFirstFit(Proceso proceso) {
//        for (Particion p: super.getParticionesArrayList()) {
//            if (p.particionVacia() && proceso.getTam() <= p.getTamParticion()){
//                p.setProcesoEnParticion(proceso);
//                break;
//            }
//        }
//    }
//
//    @Override
//    void cargar(Proceso proceso) {
//        HashMap<Integer, Integer> bestFitMap = new HashMap<>();
//        for (Particion p: super.getParticionesArrayList()) {
//            if (p.particionVacia() && p.getTamParticion() >= proceso.getTam()) {
//                // Carga el index de las particiones libres con sus respectiva fragmentacion externa
//                bestFitMap.put(super.getParticionesArrayList().indexOf(p), p.getTamParticion() - proceso.getTam());
//            }
//        }
//            if (!bestFitMap.isEmpty()) {
//                // Extrae la particion en la cual se produce menor fragmentacion interna
//                int min = bestFitMap.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
//                super.getParticionesArrayList().get(min).setProcesoEnParticion(proceso);
//            }
//    }
//
//    @Override
//    public int getTamMemoria() {
//        return tamMemoria.get();
//    }
//
//    @Override
//    public SimpleIntegerProperty tamMemoriaProperty() {
//        return tamMemoria;
//    }
//
//    public void setTamMemoria(int tamMemoria) {
//        this.tamMemoria.set(tamMemoria);
//    }
//
//    @Override
//    public ArrayList<Particion> getParticionesArrayList() {
//        return particionesArrayList;
//    }
//
//    @Override
//    public void setParticionesArrayList(ArrayList<Particion> particionesArrayList) {
//        this.particionesArrayList = particionesArrayList;
//    }
//
//    @Override
//    public String toString() {
//        return "Memoria{" +
//                "tamMemoria=" + tamMemoria +
//                ", particionesArrayList=" + arrayToString() +
//                '}';
//    }

}

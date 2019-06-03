package com.SimuladorSO;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParticionVariableBf extends Memoria{


    public ParticionVariableBf(int tamMemoria) {
        super(tamMemoria);
        //this.tamLibre = tamMemoria;
    }

//    public ParticionVariableBf(Integer tamMemoria, Integer cantParticiones, ArrayList<Integer> tamParticiones, int tamLibre) {
//        super(tamMemoria, cantParticiones, tamParticiones);
//        this.tamLibre = tamLibre;
//    }

    //public ParticionVariableBf(int tamLibre) {
    //    this.tamLibre = tamLibre;
    //}

    /**private SimpleIntegerProperty tamMemoria;
    private ArrayList<Particion> particionesArrayList;

    public ParticionVariableBf(Integer tamMemoria) {
        this.tamMemoria = new SimpleIntegerProperty(tamMemoria);
        this.particionesArrayList = new ArrayList<>();
    }**/

    @Override
    boolean cargarProceso(Proceso proceso) {
        HashMap<Integer, Integer> bestFitMap = new HashMap<>();
        for (Particion p: super.getParticionesArrayList()) {
            if (p.particionVacia() && p.getTamParticion() >= proceso.getTam()){
                // Carga el index de las particiones libres con sus respectiva fragmentacion externa
                bestFitMap.put(super.getParticionesArrayList().indexOf(p), p.getTamParticion() - proceso.getTam());
            }
            if (!bestFitMap.isEmpty()) {
                // Extrae la particion en la cual se produce menor fragmentacion interna
                int min = bestFitMap.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
                super.getParticionesArrayList().get(min).setProcesoEnParticion(proceso);
                int difTam = diferenciaTam(p.getTamParticion(), proceso.getTam());
                if (difTam > 0)
                    generarParticion(min, difTam);
                return  true;
                //setTamLibre(tamLibre - proceso.getTam());
            }
        }
        return false;
    }

    private void generarParticion(int difTam, int pIndex){
        Particion p = new Particion(difTam);
        super.getParticionesArrayList().set(pIndex, p);
    }

    private int diferenciaTam(int tam1 , int tam2){
        return tam1 - tam2;
    }

//    @Override
//    void cargaFirstFit(Proceso proceso) {
//        for (Particion p: super.getParticionesArrayList()) {
//            if (proceso.getTam() <= p.getTamParticion() && p.particionVacia()){
//                p.setProcesoEnParticion(proceso);
//                int difTam = diferenciaTam(p.getTamParticion(), proceso.getTam());
//                if (difTam > 0)
//                    generarParticion(super.getParticionesArrayList().indexOf(p), difTam);
//                setTamLibre(tamLibre - proceso.getTam());
//                break;
//            }
//        }
//    }
//
//    @Override
//    void cargaBestFit(Proceso proceso) {
//        HashMap<Integer, Integer> bestFitMap = new HashMap<>();
//        for (Particion p: super.getParticionesArrayList()) {
//            if (p.particionVacia() && p.getTamParticion() >= proceso.getTam()){
//                // Carga el index de las particiones libres con sus respectiva fragmentacion externa
//                bestFitMap.put(super.getParticionesArrayList().indexOf(p), p.getTamParticion() - proceso.getTam());
//            }
//            if (!bestFitMap.isEmpty()) {
//                // Extrae la particion en la cual se produce menor fragmentacion interna
//                int min = bestFitMap.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
//                super.getParticionesArrayList().get(min).setProcesoEnParticion(proceso);
//                int difTam = diferenciaTam(p.getTamParticion(), proceso.getTam());
//                if (difTam > 0)
//                    generarParticion(min, difTam);
//                setTamLibre(tamLibre - proceso.getTam());
//            }
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "Memoria{" +
//                "tamMemoria=" + tamMemoria +
//                ", particionesArrayList=" + arrayToString() +
//                '}';
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

//    public int getTamLibre() {
//        return tamLibre;
//    }
//
//    private void setTamLibre(int tamLibre) {
//        this.tamLibre = tamLibre;
//    }


}

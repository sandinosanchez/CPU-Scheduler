package com.SimuladorSO;

import javafx.beans.property.SimpleIntegerProperty;

public class Particion {

    private SimpleIntegerProperty tamParticion;
    private Proceso procesoEnParticion;

    public Particion(Integer tamParticion) {
        this.tamParticion = new SimpleIntegerProperty(tamParticion);
        this.procesoEnParticion = null;
    }

    @Override
    public String toString() {
        return "{" +
                "," + tamParticion +
                "," + procesoEnParticion +
                '}';
    }

    boolean particionVacia(){
        return procesoEnParticion == null;
        //return procesoEnParticion != null;
    }


    public int getFragmentacionInterna(){
        return tamParticion.getValue() - procesoEnParticion.getTam();
    }

    public int getTamParticion() {
        return tamParticion.get();
    }

    public SimpleIntegerProperty tamParticionProperty() {
        return tamParticion;
    }

    public void setTamParticion(int tamParticion) {
        this.tamParticion.set(tamParticion);
    }

    public Proceso getProcesoEnParticion() {
        return procesoEnParticion;
    }

    public void setProcesoEnParticion(Proceso procesoEnParticion) {
        this.procesoEnParticion = procesoEnParticion;
    }
}

package com.SimuladorSO;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;

public class Proceso {
    private SimpleIntegerProperty id;
    private ArrayList<Rafaga> rafaga;
    private SimpleIntegerProperty tam;
    private SimpleIntegerProperty tArribo;
    private int comienzoEjecucion;
    private int rafagaRestanteTotal;
    private int finEjecucion;
    private boolean finished;
    private  int estadoProceso;

    public Proceso(int id, ArrayList<Integer> rafaga, int tam, int tArribo) {
        this.id = new SimpleIntegerProperty(id);
        ArrayList<Rafaga> rafagaArrayList = new ArrayList<>();
        // Se carga el tipo de rafaga 0 = CPU ; 1 = E/S
        for (int i = 0; i < rafaga.size(); i++) {
            Rafaga raf = new Rafaga();
            // Se suma 1 (i+1) porque los arreglos comienzan con 0
            if (i % 2  == 0) {
                raf.setTipoRafaga(0);
            } else raf.setTipoRafaga(1);
            raf.setValorRafaga(rafaga.get(i));
            rafagaArrayList.add(raf);
        }
        this.rafaga = rafagaArrayList;
        this.tam = new SimpleIntegerProperty(tam);
        this.tArribo = new SimpleIntegerProperty(tArribo);
        this.finished = false;
        // 0 = No ejecucion, 1 = Ejecutando, 2 = Bloqueado
        this.estadoProceso = 0;
        this.rafagaRestanteTotal = getRafagaRestanteTotal();
    }

    public int tipoRafaga(){
        return rafaga.get(0).getTipoRafaga();
    }

    public int getRafagaEjec(){
        return rafaga.get(0).getValorRafaga();
    }

    public void ejecutarCiclo(int simClock){
        if (getRafagaRestanteTotal() == rafagaRestanteTotal) comienzoEjecucion = simClock;
        rafaga.get(0).descontarRafaga();
        if (rafaga.get(0).getValorRafaga() == 0) rafaga.remove(0);
        if (rafaga.isEmpty()) setFinished(true);
    }

    public void setRafagaRestanteTotal(){
        for (Rafaga r: rafaga) {
            rafagaRestanteTotal += r.getValorRafaga();
        }
    }

    public int getRafagaRestanteTotal(){
        int rafTotalTemp = 0;
        for (Rafaga r: rafaga) rafTotalTemp += r.getValorRafaga();
        return rafTotalTemp;
    }

    public int getEstadoProceso(){
        return estadoProceso;
    }

    public boolean isFinished(){
        return rafaga.isEmpty();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    int getTam() {
        return tam.get();
    }

    public SimpleIntegerProperty tamProperty() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam.set(tam);
    }

    public int gettArribo() {
        return tArribo.get();
    }

    public SimpleIntegerProperty tArriboProperty() {
        return tArribo;
    }

    public void settArribo(int tArribo) {
        this.tArribo.set(tArribo);
    }

    public int getComienzoEjecucion() {
        return comienzoEjecucion;
    }

    public void setComienzoEjecucion(int comienzoEjecucion) {
        this.comienzoEjecucion = comienzoEjecucion;
    }

    public int getFinEjecucion() {
        return finEjecucion;
    }

    public void setFinEjecucion(int finEjecucion) {
        this.finEjecucion = finEjecucion;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setEstadoProceso(int estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public String getRafaga() {

        String[] a = new String[rafaga.size()];
        for (int i = 0; i < rafaga.size() ; i++) {
            a[i] = Integer.toString(rafaga.get(i).getValorRafaga());
        }
        return String.join("-", a);
    }

    public void setRafaga(ArrayList<Rafaga> rafaga) {
        this.rafaga = rafaga;
    }

    @Override
    public String toString() {
        return  "{" +
                id.getValue() +
                ", " + rafaga.toString() +
                //", " + tam.getValue() +
                //", " +tArribo.getValue() +
                '}';
    }
}

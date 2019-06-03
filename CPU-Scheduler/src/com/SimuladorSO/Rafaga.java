package com.SimuladorSO;

public class Rafaga {

    private int valorRafaga;
    private int tipoRafaga;

    public Rafaga(int valorRafaga, int tipoRafaga) {
        this.valorRafaga = valorRafaga;
        this.tipoRafaga = tipoRafaga;
    }

    public Rafaga(){

    }

    public void descontarRafaga(){
        this.valorRafaga--;
    }

    public int getTipoRafaga() {
        return tipoRafaga;
    }

    public void setTipoRafaga(int tipoRafaga) {
        this.tipoRafaga = tipoRafaga;
    }

    public int getValorRafaga() {
        return valorRafaga;
    }

    public void setValorRafaga(int valorRafaga) {
        this.valorRafaga = valorRafaga;
    }

    @Override
    public String toString() {
        return  Integer.toString(valorRafaga);
    }
}

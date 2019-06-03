package com.SimuladorSO;

public class ParticionVariableFf extends Memoria {

    public ParticionVariableFf(Integer tamMemoria) {
        super(tamMemoria);
    }

    private void generarParticion(int pIndex, int difTam){
        Particion p = new Particion(difTam);
        super.getParticionesArrayList().add(pIndex, p);
    }

    private int diferenciaTam(int tam1 , int tam2){
        return tam1 - tam2;
    }

    @Override
    boolean cargarProceso(Proceso proceso) {
        for (Particion p: super.getParticionesArrayList()) {
            if (proceso.getTam() <= p.getTamParticion() && p.particionVacia()){
                p.setProcesoEnParticion(proceso);
                int difTam = diferenciaTam(p.getTamParticion(), proceso.getTam());
                if (difTam > 0)
                    generarParticion(super.getParticionesArrayList().indexOf(p), difTam);
                //setTamLibre(tamLibre - proceso.getTam());
                return true;
            }
        }
        return false;
    }
}

package com.SimuladorSO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // Definimos la tabla de procesos y sus columnas
    @FXML private TableView<Proceso> tabla;
    @FXML private TableColumn<Proceso,Integer> id;
    @FXML private TableColumn<Proceso,Integer> tArribo;
    @FXML private TableColumn<Proceso,Integer> tam;
    @FXML private TableColumn<Proceso,String> rafaga;

    private ModuloCPU moduloCPU;

    @FXML TableView<Proceso> tablaSim;
    @FXML TableColumn<Proceso, Integer> pid;
    @FXML TableColumn<Proceso, Integer> estadoProceso;
    @FXML TableColumn<Proceso, Integer> rafagaProceso;

    //Label
    @FXML Label labelProcesoCPU;
    @FXML Label labelClockCPU;

    // Definimos los TextFields utilizados
    @FXML private TextField insertTiempoArribo;
    @FXML private TextField insertProcesoId;
    @FXML private TextField insertTam;
    @FXML private TextField insertRafaga;
    @FXML private TextField cantidadParticiones;
    @FXML private TextField planificadorQuantumTextField;
    @FXML private TextField memoriaTamTextField;
    @FXML private TextField tamParticionesTextField;

    // Panes
    @FXML private Pane configuracionSimuladorPane;
    @FXML private Pane panelDeControlSimuladorPane;
    @FXML private Pane resultadosSimulacionPane;

    // Botones
    @FXML private Button insertButton;
    @FXML private Button configuracionSimuladorButton;
    @FXML private Button panelDeControlSimulacionButton;
    @FXML private Button resultadosSimulacionButton;
    @FXML private Button iniciarSimButton;

    // ChoiceBox
    @FXML private ChoiceBox<String> choiceBoxTipoParticiones;

    //ToggleGroups
    @FXML public ToggleGroup memoriaToggleGroup;
    @FXML public ToggleGroup cpuToggleGroup;

    // RadioButtons para los algoritmos de planificacion y de memoria
    @FXML RadioButton memoriaFirstFitRaddioButon;
    @FXML RadioButton memoriaBestFitRadioButton;
    @FXML RadioButton planificadorFCFSRaddioButton;
    @FXML RadioButton planificadorRRRadioButton;
    @FXML RadioButton planificadorSJFRadioButton;
    @FXML RadioButton planificadorSRTFRadioButton;


    // Metodo para ingresar elementos a la tabla de procesos
    public void setTablaProcesosIngresar(){
        Proceso p = new Proceso(isInt(insertProcesoId),
                                conversorIntArray(insertRafaga),
                                isInt(insertTam),
                                isInt(insertTiempoArribo));

        tabla.getItems().add(p);
        insertProcesoId.clear();
        insertTiempoArribo.clear();
        insertTam.clear();
        insertRafaga.clear();

    }

    public Memoria setearMemoria(){
        if (choiceBoxTipoParticiones.getValue().equalsIgnoreCase("Fijas")){
            if (memoriaFirstFitRaddioButon.isSelected()){
                return new ParticionFijaFf(isInt(memoriaTamTextField),
                        isInt(cantidadParticiones),
                        conversorIntArray(tamParticionesTextField));
            } else if (memoriaBestFitRadioButton.isSelected()){
                return new ParticionFijaBf(isInt(memoriaTamTextField),
                        isInt(cantidadParticiones),
                        conversorIntArray(tamParticionesTextField));
            }
        } else if(choiceBoxTipoParticiones.getValue().equalsIgnoreCase("Variables")){
            if (memoriaFirstFitRaddioButon.isSelected()) {
                return new ParticionVariableFf(isInt(memoriaTamTextField));
            } else if (memoriaBestFitRadioButton.isSelected()){
                return new ParticionVariableBf(isInt(memoriaTamTextField));
            }
        }
        return null;
    }

    public void iniciarSimulacion() throws InterruptedException {

        ObservableList<Proceso> procesosList = FXCollections.observableArrayList();
        procesosList.setAll(tabla.getItems());
        Cola colaNuevos = new Cola();
        colaNuevos.getCola().addAll(procesosList);
        if (planificadorSJFRadioButton.isSelected()){
            moduloCPU = new SimuladorSJF(colaNuevos, setearMemoria());
        } else if (planificadorSRTFRadioButton.isSelected()){
            moduloCPU = new SimuladorSJF(colaNuevos, setearMemoria());
        } else if(planificadorRRRadioButton.isSelected()){
            moduloCPU = new SimuladorRR(colaNuevos, setearMemoria(), isInt(planificadorQuantumTextField));
        } else {
            moduloCPU = null;
        }

        moduloCPU.ordrByTiempoArrivo();
        System.out.println("Cola Nuevos = "+colaNuevos.toString());

        while (!moduloCPU.getColaNuevos().getCola().isEmpty()){

            // Carga cola de memoria tArrivo == clock
            moduloCPU.cargarColaMemoria(moduloCPU.getClock());
            System.out.println("Cola Memoria: "+moduloCPU.getMemoriaPrincipal().colaMemoriaToString());
            //System.out.println("Memoria : "+moduloCPU.memoriaToString());

            // Carga la memoria siempre que se pueda
            moduloCPU.cargarMemoria();
            moduloCPU.ordernarColaListos();

            //Carga la cola de ES
            moduloCPU.cargarColaES();

            //Setea el proceso en ejecucion (siempre el primero de la cola de listos)
            moduloCPU.setProcesoEnEjecucion();


//            labelProcesoCPU.setText(Integer.toString(moduloCPU.getProcesoEnEjecucionCPU().getId()));
//            System.out.println(moduloCPU.memoriaToString());
//            System.out.println("cola listos = "+moduloCPU.getColaListos().toString());
//            System.out.println("clock : "+ moduloCPU.getClock());
//            System.out.println("cpu ocupada: "+moduloCPU.isCpuOcupada());
//            System.out.println("Proceso en ejecucion: "+ moduloCPU.getProcesoEnEjecucionCPU().getId());
//            System.out.println("----------------------");

            // Ejecuta un instante de cada cola E/S, CPU
            moduloCPU.ejecutarProceso(moduloCPU.getClock());
            moduloCPU.ejecutarProcesoES(moduloCPU.getClock());

            // Se verifica si el proceso que ejecuto un instante termino
            moduloCPU.elimProcesoTerm();

            // Incrementa el clock del simulador
            moduloCPU.incClock();


            Thread.sleep(1000);
        }
//        tabla.setItems(procesosList);
    }

    @FXML
    public void handleMenuBarButtons(ActionEvent event){
        if (event.getSource() == configuracionSimuladorButton)
            configuracionSimuladorPane.toFront();
        else if (event.getSource() == panelDeControlSimulacionButton){
            panelDeControlSimuladorPane.toFront();
        } else if (event.getSource() == resultadosSimulacionButton){
            resultadosSimulacionPane.toFront(); }
    }

    // Metodo que transforma el string extraido de la tabla a integer
    private int isInt(TextField input){
        try {
            return Integer.parseInt(input.getText());
        } catch (NumberFormatException e){
            return 0;
        }
    }

    // Metodo que transforma el string extraido de la tabla a un array de integers
    private ArrayList<Integer> conversorIntArray(TextField input){
        String[] c = input.getText().split("-");
        int[] ints = new int[c.length];
        for (int i = 0; i <c.length ; i++) ints[i] = Integer.valueOf(c[i]);
        ArrayList<Integer> tempArray = new ArrayList<>(ints.length);
        for (int anInt : ints) {
            tempArray.add(anInt);
        }
        return tempArray;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        configuracionSimuladorPane.toFront();

        // Seteamos el valor de las celdas de la tableView
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tArribo.setCellValueFactory(new PropertyValueFactory<>("tArribo"));
        tam.setCellValueFactory(new PropertyValueFactory<>("tam"));
        rafaga.setCellValueFactory(new PropertyValueFactory<>("rafaga"));

        // Seteamos los valores del choiceBox de la memoria
        choiceBoxTipoParticiones.setItems(FXCollections.observableArrayList(
                "Fijas","Variables"));
    }
}

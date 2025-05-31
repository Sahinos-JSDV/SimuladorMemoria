/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sahin
 */
public class ControlPaginada implements ActionListener{
    private MemPaginada vista;
    private ControlContigua vistaContigua; //Objeto para poder controlar la visibilidad del simulador de la memoria contigua
    private LRU lru;
    private FIFO fifo;
    private DefaultTableModel tabla = new DefaultTableModel();
    private DefaultTableModel tabla2 = new DefaultTableModel();
    private double[] rendimientos = new double[2];
    
    public ControlPaginada(MemPaginada vista){
        this.vista = vista;
       this.vista.rbtnFIFO.addActionListener(this);
       this.vista.rbtnLRU.addActionListener(this);
       this.vista.rbtnNumero.addActionListener(this);
       this.vista.rbtnTam.addActionListener(this);
       this.vista.btnCambiar.addActionListener(this);
       this.vista.btnGuardar.addActionListener(this);
       this.vista.boton.addActionListener(this);
       this.vista.btnModificar.addActionListener(this);
       rendimientos[0] = 0;
       rendimientos[1] = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(vista.rbtnNumero.isSelected()){
            vista.labelTamSO.setVisible(false);
            vista.txtTamSO.setVisible(false);
            vista.labelTamMar.setVisible(false);
            vista.txtTamMarco.setVisible(false);
            vista.labelNumMar.setVisible(true);
            vista.contadorMarcos.setVisible(true);
        }
        if(vista.rbtnTam.isSelected()){
            vista.labelNumMar.setVisible(false);
//            vista.contadorMarcos.setVisible(false);
            vista.labelTamSO.setVisible(true);
            vista.txtTamSO.setVisible(true);
            vista.labelTamMar.setVisible(true);
            vista.txtTamMarco.setVisible(true);
            
        }
        if(e.getSource()==vista.boton){
            inhabilitarEdicion();
            vista.btnModificar.setEnabled(true);
            if(vista.rbtnFIFO.isSelected()){
                ejecutarFIFO();
            }else{
                ejecutarLRU();
            }
            
            actualizarMejor();
        }
        if(e.getSource()==vista.btnCambiar){
            ocultar();
            vistaContigua.mostrar();
        }
       if(e.getSource()==vista.btnModificar){
           habilitarEdicion();
           vista.btnModificar.setEnabled(false);
       }    
    }
    
    //Métodos para ejecución de los algoritmos
    public void ejecutarLRU(){
        String cadenaReferencia = vista.txtCadenaReferencia.getText();
        String[] cadena = cadenaReferencia.split(" ");
        int[] paginas = new int[cadena.length];
        
        for(int i = 0; i < cadena.length; i++){
            paginas[i] = Integer.parseInt(cadena[i]);
        }
        
        int n = 0;
            if(vista.rbtnNumero.isSelected()){
                n = Integer.parseInt(vista.contadorMarcos.getValue().toString());
            }
            if(vista.rbtnTam.isSelected()){
                n = (int) (Integer.parseInt(vista.txtTamSO.getText())/Integer.parseInt(vista.txtTamMarco.getText()));
            }
            String[] cabeceras = new String[n+2];
            cabeceras[0] = "Referencia";
            for(int i = 0; i < n; i++){
                cabeceras[i+1] = "Marco "+i;
            }
            cabeceras[cabeceras.length-1] = "Fallo";
            crearTabla(cabeceras);
        
        lru.setPaginas(paginas);
        lru.setCantidadFrames(n);
        lru.setCantidadPaginas(paginas.length);
        lru.lru();
        mostrarDatosLRU(vista.tabla, vista.tablaCola);
        
    }
    
    public void ejecutarFIFO(){
        String cadenaReferencia = vista.txtCadenaReferencia.getText();
        String[] cadena = cadenaReferencia.split(" ");
        int[] paginas = new int[cadena.length];
        
        for(int i = 0; i < cadena.length; i++){
            paginas[i] = Integer.parseInt(cadena[i]);
        }
        
        int n = 0;
            if(vista.rbtnNumero.isSelected()){
                n = Integer.parseInt(vista.contadorMarcos.getValue().toString());
            }
            if(vista.rbtnTam.isSelected()){
                n = (int) (Integer.parseInt(vista.txtTamSO.getText())/Integer.parseInt(vista.txtTamMarco.getText()));
            }
            String[] cabeceras = new String[n+2];
            cabeceras[0] = "Referencia";
            for(int i = 0; i < n; i++){
                cabeceras[i+1] = "Marco "+i;
            }
            cabeceras[cabeceras.length-1] = "Fallo";
            crearTabla(cabeceras);
        
        fifo.setPaginas(paginas);
        fifo.setCantidadFrames(n);
        fifo.setCantidadPaginas(paginas.length);
        fifo.fifo();
        mostrarDatosFIFO(vista.tabla, vista.tablaCola);
        
    }
    
    //Método para añadir los datos necesarios a la tabla
    public void mostrarDatosLRU(JTable tabla, JTable tabla2){
        this.tabla = (DefaultTableModel) tabla.getModel();
        this.tabla2 = (DefaultTableModel) tabla2.getModel();
        int[][] datos = lru.getMatriz();
        int[] fallos = lru.getFallos();
        ArrayList<Queue<Integer>> estadosCola = lru.getEstadosCola();
        int[] paginas = lru.getPaginas();
        int cantidadPaginas = lru.getCantidadPaginas(), cantidadFrames = lru.getCantidadFrames();
        int fallosTotales = 0;
        
        
        Object[] fila = new Object[cantidadPaginas+2];
        Object[] cola = new Object[2];
        for(int i = 0; i < cantidadPaginas; i++){
            this.tabla.addRow(fila);
        }
        
        for(int i = 0; i < cantidadPaginas; i++){
            this.tabla.setValueAt(paginas[i], i, 0);
            for(int j = 0; j < cantidadFrames; j++){
                if(datos[j][i]== -1){
                    this.tabla.setValueAt("---", i, j+1);
                }else{
                    this.tabla.setValueAt(datos[j][i], i, j+1);
                }
            }
            if(fallos[i]==1){
                this.tabla.setValueAt("X", i, cantidadFrames+1);
                fallosTotales++;
            }else{
                this.tabla.setValueAt("-", i, cantidadFrames+1);
            }
            cola[0] = i+1;
            cola[1] = estadosCola.get(i);
            this.tabla2.addRow(cola);
        }
        
        vista.labelFallosTotales.setText("Fallos Totales: "+fallosTotales);
        double razonFallos = (double) fallosTotales/cantidadPaginas;
        double rendimiento = (1-razonFallos)*100;
        vista.labelRazonFallos.setText("Razón de fallos: "+ razonFallos);
        vista.labelRendimiento.setText("Rendimiento: "+rendimiento);
        rendimientos[1] = rendimiento; //Posicion 1 para rendimiento de lru
        
    }//Fin MostrarDatosLRU
    
     public void mostrarDatosFIFO(JTable tabla, JTable tabla2){
        this.tabla = (DefaultTableModel) tabla.getModel();
        this.tabla2 = (DefaultTableModel) tabla2.getModel();
        int[][] datos = fifo.getMatriz();
        int[] fallos = fifo.getFallos();
        ArrayList<Queue<Integer>> estadosCola = fifo.getEstadosCola();
        int[] paginas = fifo.getPaginas();
        int cantidadPaginas = fifo.getCantidadPaginas(), cantidadFrames = fifo.getCantidadFrames();
        int fallosTotales = 0;
        
        Object[] fila = new Object[cantidadPaginas+2];
        Object[] cola = new Object[2];
        for(int i = 0; i < cantidadPaginas; i++){
            this.tabla.addRow(fila);
        }
        
        for(int i = 0; i < cantidadPaginas; i++){
            this.tabla.setValueAt(paginas[i], i, 0);
            for(int j = 0; j < cantidadFrames; j++){
                if(datos[j][i]== -1){
                    this.tabla.setValueAt("---", i, j+1);
                }else{
                    this.tabla.setValueAt(datos[j][i], i, j+1);
                }
            }
            if(fallos[i]==1){
                this.tabla.setValueAt("X", i, cantidadFrames+1);
                fallosTotales++;
            }else{
                this.tabla.setValueAt("-", i, cantidadFrames+1);
            }
            cola[0] = i+1;
            cola[1] = estadosCola.get(i);
            this.tabla2.addRow(cola);
        }        
        
        vista.labelFallosTotales.setText("Fallos Totales: "+fallosTotales);
        double razonFallos = (double) fallosTotales/cantidadPaginas;
        double rendimiento = (1-razonFallos)*100;
        vista.labelRazonFallos.setText("Razón de fallos: "+ razonFallos);
        vista.labelRendimiento.setText("Rendimiento: "+rendimiento);
        rendimientos[0] = rendimiento; //posicion 0 para el rendimiento de fifo
        
    }//Fin MostrarDatosFIFO
    
    //Método para la actualización de la tabla en la vista
    public void crearTabla(String[] cabeceras){
        vista.tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            cabeceras
        ));
        
        vista.tablaCola.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String[] {"Iteración: ","Estado de la cola: "}
        ));



        vista.scrollTabla.setViewportView(vista.tabla);

    }
    //Métodos para poder controlar la vista de la interfaz del simulador de memoria paginada
    
    public void mostrar(){
        vista.setVisible(true);
    }
    
    public void ocultar(){
        vista.setVisible(false);
    }
    
    public void setVistaContigua(ControlContigua vistaContigua){
        this.vistaContigua = vistaContigua;
    }
    
    public void setLRU(LRU lru){
        this.lru = lru;
    }
    
    public void setFIFO(FIFO fifo){
        this.fifo = fifo;
    }
    
    public void actualizarMejor(){
        vista.labelFIFO.setText("FIFO: "+rendimientos[0]+" %");
        vista.labelLRU.setText("LRU: "+rendimientos[1]+" %");
        
        if(rendimientos[0] < rendimientos[1]){
            vista.labelMejorRendimiento.setText("Mejor algoritmo para la cadena ingresada: LRU");
        }else{
            vista.labelMejorRendimiento.setText("Mejor algoritmo para la cadena ingresada: FIFO");
        }
    }
    
    public void inhabilitarEdicion(){
        vista.boton.setEnabled(false);
        vista.btnGuardar.setEnabled(true);
        vista.txtCadenaReferencia.setEnabled(false);
        vista.contadorMarcos.setEnabled(false);
        vista.txtTamMarco.setEnabled(false);
        vista.txtTamSO.setEnabled(false);
        vista.rbtnFIFO.setEnabled(false);
        vista.rbtnLRU.setEnabled(false);
        vista.rbtnNumero.setEnabled(false);
        vista.rbtnTam.setEnabled(false);
    }
    public void habilitarEdicion(){
        vista.boton.setEnabled(true);
        vista.btnGuardar.setEnabled(false);
        vista.txtCadenaReferencia.setEnabled(true);
        vista.contadorMarcos.setEnabled(true);
        vista.txtTamMarco.setEnabled(true);
        vista.txtTamSO.setEnabled(true);
        vista.rbtnFIFO.setEnabled(true);
        vista.rbtnLRU.setEnabled(true);
        vista.rbtnNumero.setEnabled(true);
        vista.rbtnTam.setEnabled(true);
    }
}
/*if(e.getSource()==vista.boton){
            int n = 0;
            if(vista.rbtnNumero.isSelected()){
                n = Integer.parseInt(vista.contadorMarcos.getValue().toString());
            }
            if(vista.rbtnTam.isSelected()){
                n = (int) (Integer.parseInt(vista.txtTamSO.getText())/Integer.parseInt(vista.txtTamMarco.getText()));
            }
            String[] cabeceras = new String[n+2];
            cabeceras[0] = "Referencia";
            for(int i = 0; i < n; i++){
                cabeceras[i+1] = "Marco "+i;
            }
            cabeceras[n+1] = "Fallo";
            crearTabla(cabeceras);
        }*/
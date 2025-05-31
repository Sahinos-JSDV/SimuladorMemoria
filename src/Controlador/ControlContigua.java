/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Sahin
 */
public class ControlContigua implements ActionListener{
    private ModelContigua modelo;
    private MemContigua vista;
    private ControlPaginada panelPaginada;//Objeto para poder controlar la visibilidad del simulador de la memoria paginada
    private DefaultTableModel tabla = new DefaultTableModel();
    private dibujoMemoria memoria;
    //Instanciar objeto de la clase Ajustes
    //Ajustes algoritmo =  new Ajustes();
    
    
    public ControlContigua(MemContigua vista , ModelContigua modelo){
        this.vista = vista;
        this.modelo= modelo;
        this.vista.btnAddProceso.addActionListener(this);
        this.vista.btnBorProceso.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnCambiar.addActionListener(this);
        this.vista.guardarConf.addActionListener(this);
        this.vista.btnReconf.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){


        if(e.getSource()==vista.btnAddProceso){
            vista.btnGuardar.setEnabled(true);
            mostrarProcesos(vista.tablaProcesos);
            modelo.setTam_SO(Integer.parseInt(vista.txtSO.getText()));
            modelo.setLongitudMemoria(Integer.parseInt(vista.txtMemoria.getText()));
            modelo.setEncolarProcesos(vista.encolarProcesos.isSelected());
            modelo.inicializaMemoria();
            modelo.creaProceso(Integer.parseInt(vista.txtAddPID.getText()),Integer.parseInt(vista.txtAddTamProc.getText()),
                    vista.seleccionAjuste.getSelectedIndex()+1);
        }
        
        if(e.getSource()==vista.btnBorProceso){
            vista.btnGuardar.setEnabled(true);
            borrarProceso(vista.tablaProcesos);
            modelo.destruyeProceso(Integer.parseInt(vista.txtBorProceso.getText()));
        }

        if(e.getSource()==vista.btnGuardar){
            vista.btnGuardar.setEnabled(false);
            modelo.verificarEspacioLibre();
            String textoMemoria = modelo.imprimeMemoria(); // Obtenemos el texto de la memoria del modelo
            vista.panelTxtRep.setText(textoMemoria);
            dibujarMemoria();
            vista.getContentPane().add(memoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 1280, 100));
            vista.panelMemGraf.setVisible(false);
            memoria.setVisible(false);
            memoria.setVisible(true);
        }

        if(e.getSource()==vista.guardarConf){
            inhabilitarConf();
        }
        
        if(e.getSource()==vista.btnReconf){
            habilitarConf();
        }

        if(e.getSource()==vista.btnCambiar){
            ocultar();
            panelPaginada.mostrar();
        }
    }
    
    public void mostrarProcesos(JTable tabla){
        this.tabla = (DefaultTableModel) tabla.getModel();
        String[] nuevaFila = new String[2];
        nuevaFila[0] = vista.txtAddPID.getText();
        nuevaFila[1] = vista.txtAddTamProc.getText();
        this.tabla.addRow(nuevaFila);
    }
    
    public void borrarProceso(JTable tabla){
        this.tabla = (DefaultTableModel) tabla.getModel();
        //int index = 0;
        for(int i = 0; i <= tabla.getRowCount()-1;i++){
            if(vista.txtBorProceso.getText().equals(tabla.getValueAt(i, 0))){
                this.tabla.removeRow(i);
                break;
            }
        }
        //this.tabla.removeRow(index);
    }
    
    //Métodos para poder controlar la vista de la interfaz del simulador de memoria contigua
    public void mostrar(){
        vista.setVisible(true);
    }
    
    public void ocultar(){
        vista.setVisible(false);
    }
    
    public void setPanelPaginada(ControlPaginada panelPaginada){
        this.panelPaginada = panelPaginada;
    }
    
    public void dibujarMemoria(){
        memoria = new dibujoMemoria(){

            @Override
            public void removeAll() {
                super.removeAll(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
                double x = 0;
                double ancho = 0;
                @Override
                public void paintComponent(Graphics g){
                    //Array de colores para los diferentes procesos
                    Color[] colores = {new Color(255,185,0), new Color(0, 146, 255), new Color(200, 255, 92), new Color(188,166,255), new Color(255,185,134), new Color(195,0,255), new Color(89,255,217), new Color(255,250,119)};
                    super.paintComponent(g);
                    boolean primerElemento = true;
                    for(int[] bloque : modelo.listaControl){
                        //System.out.println(bloque[1]+" "+bloque[2]+" "+bloque[3]);//Datos contenidos en cada bloque
                        x = (double) bloque[1]*(memoria.getWidth()/modelo.LONGITUD_MEMORIA);
                        ancho = (bloque[2])*(memoria.getWidth()/modelo.LONGITUD_MEMORIA);
                        // Producir nuevo int aleatorio entre 0 y 6 y sumarle uno para los inidices de 1 a 7
                        int intAleatorio = (int) (Math.random() * 7) + 1;
                        //System.out.println("X: "+x+" Ancho: "+ancho);
                        if (primerElemento && bloque[3] == -1) {
                            g.setColor(colores[0]);
                            g.fillRect((int) x, 0,(int) ancho, memoria.getHeight());
                            g.setColor(Color.BLACK);
                            g.drawString("SO", (int) (x+(ancho/2)), memoria.getHeight()/2);
                        }
                        else if(bloque[0]==0){
                            g.setColor(new Color(51,51,51));
                            g.fillRect((int) x, 0, (int) ancho, memoria.getHeight());
                        }
                        else {
                            g.setColor(colores[intAleatorio]);
                            g.fillRect((int) x, 0, (int)ancho, memoria.getHeight());
                            g.setColor(Color.BLACK);
                            g.drawString("P"+bloque[3], (int) (x+(ancho/2)), memoria.getHeight()/2);
                        }
                        
                }         
            }
        };
    }
    
    public void inhabilitarConf(){
        vista.seleccionAjuste.setEnabled(false);
        vista.txtMemoria.setEnabled(false);
        vista.txtSO.setEnabled(false);
        vista.btnReconf.setEnabled(true);
        vista.encolarProcesos.setEnabled(false);
        vista.guardarConf.setEnabled(false);
    }
    
    public void habilitarConf(){
        vista.seleccionAjuste.setEnabled(true);
        vista.txtMemoria.setEnabled(true);
        vista.txtSO.setEnabled(true);
        vista.btnReconf.setEnabled(true);
        vista.encolarProcesos.setEnabled(true);
        vista.guardarConf.setEnabled(true);
    }
}

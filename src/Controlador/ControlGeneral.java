/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Sahin
 */
import Modelo.*;
import Vista.*;
public class ControlGeneral {
    public static void main(String args[]){
        MemContigua vMC = new MemContigua();
        MemPaginada vMP = new MemPaginada();
        ModelContigua mMC = new ModelContigua();
        ControlContigua controlContigua = new ControlContigua(vMC, mMC);
        ControlPaginada controlPaginada = new ControlPaginada(vMP);
        LRU lru = new LRU();
        FIFO fifo = new FIFO();
        
        controlPaginada.setLRU(lru);
        controlPaginada.setFIFO(fifo);
        controlContigua.setPanelPaginada(controlPaginada);
        controlPaginada.setVistaContigua(controlContigua);
        vMC.setVisible(true);
    }
}

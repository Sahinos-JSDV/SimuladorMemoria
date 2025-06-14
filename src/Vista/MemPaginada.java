/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import com.formdev.flatlaf.themes.*;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Sahin
 */
public class MemPaginada extends javax.swing.JFrame {

    /**
     * Creates new form MemPaginada
     */
    public MemPaginada() {
        
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        FlatMacDarkLaf.setup();
        
        initComponents();
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        algoritmo = new javax.swing.ButtonGroup();
        grupoMarcos = new javax.swing.ButtonGroup();
        panelConfiguracion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCadenaReferencia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        rbtnFIFO = new javax.swing.JRadioButton();
        rbtnLRU = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        panelMarcos = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        rbtnNumero = new javax.swing.JRadioButton();
        rbtnTam = new javax.swing.JRadioButton();
        labelTamSO = new javax.swing.JLabel();
        txtTamSO = new javax.swing.JTextField();
        labelTamMar = new javax.swing.JLabel();
        txtTamMarco = new javax.swing.JTextField();
        labelNumMar = new javax.swing.JLabel();
        SpinnerNumberModel modelo = new SpinnerNumberModel();
        modelo.setMaximum(12);
        modelo.setMinimum(2);
        modelo.setValue(2);
        contadorMarcos = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        btnCambiar = new javax.swing.JButton();
        boton = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        panelSimulación = new javax.swing.JPanel();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        panelEstados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCola = new javax.swing.JTable();
        labelRazonFallos = new javax.swing.JLabel();
        labelRendimiento = new javax.swing.JLabel();
        labelFallosTotales = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        labelFIFO = new javax.swing.JLabel();
        labelLRU = new javax.swing.JLabel();
        labelMejorRendimiento = new javax.swing.JLabel();

        algoritmo.add(rbtnFIFO);
        algoritmo.add(rbtnLRU);

        grupoMarcos.add(rbtnNumero);
        grupoMarcos.add(rbtnTam);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelConfiguracion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configuración.", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        panelConfiguracion.setPreferredSize(new java.awt.Dimension(210, 720));
        panelConfiguracion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Cadena de referencia:");
        panelConfiguracion.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        txtCadenaReferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCadenaReferenciaActionPerformed(evt);
            }
        });
        panelConfiguracion.add(txtCadenaReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 220, -1));

        jLabel2.setText("Algoritmo de reemplazo:");
        panelConfiguracion.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 20));

        rbtnFIFO.setSelected(true);
        rbtnFIFO.setText("FIFO");
        rbtnFIFO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFIFOActionPerformed(evt);
            }
        });

        rbtnLRU.setText("LRU");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(rbtnFIFO)
                .addGap(31, 31, 31)
                .addComponent(rbtnLRU)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnFIFO)
                    .addComponent(rbtnLRU)))
        );

        panelConfiguracion.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 220, 20));
        panelConfiguracion.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 220, -1));

        panelMarcos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Marcos:");
        panelMarcos.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, -1));

        rbtnNumero.setSelected(true);
        rbtnNumero.setText("Por número");
        panelMarcos.add(rbtnNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        rbtnTam.setText("Por tamaño");
        panelMarcos.add(rbtnTam, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        labelTamSO.setText("Tamaño del sistema operativo:");
        panelMarcos.add(labelTamSO, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));
        labelTamSO.setVisible(false);
        panelMarcos.add(txtTamSO, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));
        txtTamSO.setVisible(false);

        labelTamMar.setText("Tamaño de cada marco:");
        panelMarcos.add(labelTamMar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));
        labelTamMar.setVisible(false);
        panelMarcos.add(txtTamMarco, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));
        txtTamMarco.setVisible(false);

        labelNumMar.setText("Número de marcos:");
        panelMarcos.add(labelNumMar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 130, -1));
        panelMarcos.add(contadorMarcos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));
        //jSpinner1.getValue().toString();
        contadorMarcos.setModel(modelo);

        panelConfiguracion.add(panelMarcos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 220, 180));
        panelConfiguracion.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 220, 10));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelConfiguracion.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 90, -1));

        btnCambiar.setText("Cambiar a memoria contigua");
        panelConfiguracion.add(btnCambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 200, -1));

        boton.setText("Ejecutar Algoritmo");
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });
        panelConfiguracion.add(boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        btnModificar.setText("Modificar");
        panelConfiguracion.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 110, -1));
        btnModificar.setEnabled(false);

        getContentPane().add(panelConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, -1));

        panelSimulación.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Simulación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        panelSimulación.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Referencia", "Marco 0", "Marco 1", "Fallo"
            }
        ));
        scrollTabla.setViewportView(tabla);

        panelSimulación.add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 600, 650));

        getContentPane().add(panelSimulación, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 640, 720));

        panelEstados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos extra de la simulación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        panelEstados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaCola.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Iteración:", "Estado de la cola:"
            }
        ));
        jScrollPane1.setViewportView(tablaCola);

        panelEstados.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 315, 470));

        labelRazonFallos.setText("Razón de fallos:");
        panelEstados.add(labelRazonFallos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 210, -1));

        labelRendimiento.setText("Rendimiento: ");
        panelEstados.add(labelRendimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 230, -1));

        labelFallosTotales.setText("Número de fallos totales: ");
        panelEstados.add(labelFallosTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 220, -1));
        panelEstados.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 583, 380, 10));

        jLabel4.setText("Rendimientos comparados:");
        panelEstados.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, -1, -1));

        labelFIFO.setText("FIFO:");
        panelEstados.add(labelFIFO, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, -1, -1));

        labelLRU.setText("LRU:");
        panelEstados.add(labelLRU, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 620, -1, -1));

        labelMejorRendimiento.setText("Mejor algoritmo para la cadena ingresada: ");
        panelEstados.add(labelMejorRendimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 660, -1, -1));

        getContentPane().add(panelEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 400, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCadenaReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCadenaReferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCadenaReferenciaActionPerformed

    private void rbtnFIFOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFIFOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnFIFOActionPerformed

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup algoritmo;
    public javax.swing.JButton boton;
    public javax.swing.JButton btnCambiar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JSpinner contadorMarcos;
    private javax.swing.ButtonGroup grupoMarcos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JLabel labelFIFO;
    public javax.swing.JLabel labelFallosTotales;
    public javax.swing.JLabel labelLRU;
    public javax.swing.JLabel labelMejorRendimiento;
    public javax.swing.JLabel labelNumMar;
    public javax.swing.JLabel labelRazonFallos;
    public javax.swing.JLabel labelRendimiento;
    public javax.swing.JLabel labelTamMar;
    public javax.swing.JLabel labelTamSO;
    private javax.swing.JPanel panelConfiguracion;
    private javax.swing.JPanel panelEstados;
    private javax.swing.JPanel panelMarcos;
    private javax.swing.JPanel panelSimulación;
    public javax.swing.JRadioButton rbtnFIFO;
    public javax.swing.JRadioButton rbtnLRU;
    public javax.swing.JRadioButton rbtnNumero;
    public javax.swing.JRadioButton rbtnTam;
    public javax.swing.JScrollPane scrollTabla;
    public javax.swing.JTable tabla;
    public javax.swing.JTable tablaCola;
    public javax.swing.JTextField txtCadenaReferencia;
    public javax.swing.JTextField txtTamMarco;
    public javax.swing.JTextField txtTamSO;
    // End of variables declaration//GEN-END:variables
}

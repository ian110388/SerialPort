/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jssc.SerialPortException;
import jssc.SerialPortList;
import modelo.config;
import serialport.Globals;

/**
 *
 * @author angelivan
 */
public class Port extends javax.swing.JFrame {
    
    /**
     * Creates new form Port
     */
    public Port() {
        initComponents();
        ListarPuertos();
    }
    
    public void setconfig() {
        ComboPort.getModel().setSelectedItem(Globals.port);
        ComboBaudRate.setSelectedItem(String.valueOf(Globals.baud_rate));
        ComboParity.setSelectedItem(Globals.parity);
        ComboBits.setSelectedItem(String.valueOf(Globals.bits));
        ComboStopBits.setSelectedItem(String.valueOf(Globals.stop_bits));
        ComboFlowControl.setSelectedItem(Globals.flow_control);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        ComboPort = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        ComboBaudRate = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ComboParity = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        ComboBits = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        ComboStopBits = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        ComboFlowControl = new javax.swing.JComboBox<>();
        ButtonOk = new javax.swing.JButton();
        ButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Serial port"));

        ComboPort.setPreferredSize(new java.awt.Dimension(150, 30));
        ComboPort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboPortMouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Port:");

        ComboBaudRate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "300", "600", "1200", "2400", "4800", "9600", "19200", "38400", "57600", "115200", "230400", "460800", "576000", "921600", "1000000", "1500000", "2000000" }));
        ComboBaudRate.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Baud Rate:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Parity:");

        ComboParity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none", "odd", "even" }));
        ComboParity.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Bits:");

        ComboBits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "6", "7", "8" }));
        ComboBits.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Stopbits:");

        ComboStopBits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));
        ComboStopBits.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Flow control:");

        ComboFlowControl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none", "RTS/CTS", "Xon/Xoff" }));
        ComboFlowControl.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboBits, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboStopBits, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboFlowControl, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboPort, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboBaudRate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboParity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboParity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboBaudRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboFlowControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboStopBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        ButtonOk.setText("OK");
        ButtonOk.setPreferredSize(new java.awt.Dimension(90, 30));
        ButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonOkActionPerformed(evt);
            }
        });

        ButtonCancel.setText("Cancel");
        ButtonCancel.setPreferredSize(new java.awt.Dimension(90, 30));
        ButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboPortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboPortMouseClicked
        ComboPort.removeAllItems();
        ListarPuertos();
    }//GEN-LAST:event_ComboPortMouseClicked

    // https://github.com/google/gson/blob/main/UserGuide.md
    private void ButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonOkActionPerformed
        Globals.port = ComboPort.getSelectedItem().toString();
        
        System.out.println(Globals.serial_port);
        
        if(Globals.serial_port != null){
            System.out.println("El puerto no es nulo");
            try {
                Globals.serial_port.closePort();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("El puerto es nulo");
        }
        
        
        config cfg = new config();
        Gson gson = new Gson();
        cfg.setPort(ComboPort.getSelectedItem().toString());
        cfg.setBaud_rate(Integer.parseInt(ComboBaudRate.getSelectedItem().toString()));
        cfg.setParity(ComboParity.getSelectedItem().toString());
        cfg.setBits(Integer.parseInt(ComboBits.getSelectedItem().toString()));
        cfg.setStop_bits(Integer.parseInt(ComboStopBits.getSelectedItem().toString()));
        cfg.setFlow_control(ComboFlowControl.getSelectedItem().toString());
        
        Globals.port = cfg.getPort();
        Globals.baud_rate = cfg.getBaud_rate();
        Globals.parity = cfg.getParity();
        Globals.bits = cfg.getBits();
        Globals.stop_bits = cfg.getStop_bits();
        Globals.flow_control = cfg.getFlow_control();
        
//        try {
//            Globals.p.Conectar(Globals.port);
//        } catch (SerialPortException ex) {
//            JOptionPane.showMessageDialog(this, ex);
//        }
        
        try {
            FileWriter output = new FileWriter("config.json");
            gson.toJson(cfg, output);
            output.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        //String json = gson.toJson(cfg);
        //System.out.println(json);
        
        this.setVisible(false);
    }//GEN-LAST:event_ButtonOkActionPerformed

    private void ButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_ButtonCancelActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        setconfig();
    }//GEN-LAST:event_formWindowActivated
    
    private void ListarPuertos(){
        String[] ports = SerialPortList.getPortNames();
        for(String port: ports){
            ComboPort.addItem(port);
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCancel;
    private javax.swing.JButton ButtonOk;
    private javax.swing.JComboBox<String> ComboBaudRate;
    private javax.swing.JComboBox<String> ComboBits;
    private javax.swing.JComboBox<String> ComboFlowControl;
    private javax.swing.JComboBox<String> ComboParity;
    private javax.swing.JComboBox<String> ComboPort;
    private javax.swing.JComboBox<String> ComboStopBits;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    // End of variables declaration//GEN-END:variables
}

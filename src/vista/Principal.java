/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.google.gson.Gson;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.plaf.FileChooserUI;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import modelo.config;

import serialport.Globals;

/**
 *
 * @author angelivan
 */
public final class Principal extends javax.swing.JFrame {
    Gson g = new Gson();
    config cfg = new config();
    Port w_port = null;
    private String st ="";
    private String s = "";

    /**
     * Creates new form Principal
     */
    public Principal() {
        
        UIManager.put( "Button.arc", 15 );
        UIManager.put( "ToolBar.background",Color.getColor("#f8fafc") );
        UIManager.put( "Component.arc", 15 );
        //UIManager.put( "Component.arrowType", "chevron" );
        UIManager.put( "Component.arrowType", "triangle" );
        UIManager.put( "TextComponent.arc", 15 );
        
        //ButtonConnect.putClientProperty("Button.hoverBackground","#f8fafc");
        
        
        
        //FlatLightLaf.setup();
        //FlatArcOrangeIJTheme.setup();
        //FlatXcodeDarkIJTheme.setup();
        //FlatMacDarkLaf.setup();
        
        try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        
        
        
        
        initComponents();
        
        
        loadConfig();
        
        
        //CREANDO INSTANCIA DE VENTANAS
        Globals.wport = new Port();
        Globals.wport.setTitle("Configuration");
        Globals.wport.setLocationRelativeTo(null);
        Globals.wport.setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        
        
        
    }
    
    public void loadConfig() {
        File config = new File("config.json");
        if(config.exists() && config.length() > 0){
            try {
                BufferedReader reader = new BufferedReader(new FileReader("config.json"));
                cfg = g.fromJson(reader, config.class);
                Globals.port = cfg.getPort();
                Globals.baud_rate = cfg.getBaud_rate();
                Globals.parity = cfg.getParity();
                Globals.bits = cfg.getBits();
                Globals.stop_bits = cfg.getStop_bits();
                Globals.flow_control = cfg.getFlow_control();
                
                System.out.println(cfg.getPort());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void Conectar(String port){
        
        Globals.serial_port = new SerialPort(port);
        try {
            Globals.serial_port.openPort();
            this.setTitle(Globals.title + " - " + Globals.port + " - " + Globals.baud_rate);
            Globals.serial_port.setParams(
                    // SerialPort.BAUDRATE_115200,
                    // SerialPort.DATABITS_8,
                    // SerialPort.STOPBITS_1,
                    // SerialPort.PARITY_NONE
                    Globals.baud_rate,
                    Globals.bits,
                    Globals.stop_bits,
                    SerialPort.PARITY_NONE);
                                            
            Globals.serial_port.setEventsMask(SerialPort.MASK_RXCHAR);
            //serialPort1.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
            //                      SerialPort.FLOWCONTROL_RTSCTS_OUT);
            Globals.serial_port.addEventListener((SerialPortEvent serialPortEvent) -> {
                //System.out.println(serialPortEvent.getEventType());
                //System.out.println(serialPortEvent.getEventValue());
                try {

                    if(serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) {
                        byte[] buf = Globals.serial_port.readBytes();

                        if (buf == null || buf.length <= 0) {
                            return;
                        }

                        s = new String(buf, 0, buf.length);
                        st += s;

                        if (st.contains("\r")){
                            textAreaTerm.append(st);
                            textAreaTerm.setCaretPosition( textAreaTerm.getDocument().getLength() );
                            try {
                                st = "";
                                s = "";
                            } catch (Exception e) {
                                System.out.println(e.getMessage()+ "*");
                            }
                        }


                    }


                } catch ( Exception e ) {
                    //log.logger.warning(e.toString());
                    System.out.println(e.toString());
                }


            });
            //System.out.println(s);

        } catch (SerialPortException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaTerm = new javax.swing.JTextArea();
        textField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        ButtonConnect = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuItemSave = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setAutoscrolls(true);

        textAreaTerm.setEditable(false);
        textAreaTerm.setColumns(20);
        textAreaTerm.setFont(textAreaTerm.getFont());
        textAreaTerm.setRows(5);
        textAreaTerm.setFocusable(false);
        jScrollPane1.setViewportView(textAreaTerm);

        textField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textField1KeyPressed(evt);
            }
        });

        jButton1.setText("Button");
        jButton1.setPreferredSize(new java.awt.Dimension(90, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        ButtonConnect.setBackground(new java.awt.Color(150, 150, 150));
        ButtonConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/view-fullscreen-symbolic.png"))); // NOI18N
        ButtonConnect.setToolTipText("");
        ButtonConnect.setFocusable(false);
        ButtonConnect.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButtonConnect.setMaximumSize(new java.awt.Dimension(40, 24));
        ButtonConnect.setPreferredSize(new java.awt.Dimension(40, 24));
        ButtonConnect.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(ButtonConnect);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setForeground(getBackground());

        jMenu1.setBackground(new java.awt.Color(19, 112, 206));
        jMenu1.setText("File");

        MenuItemSave.setText("Save As...");
        MenuItemSave.setPreferredSize(new java.awt.Dimension(150, 25));
        MenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSaveActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemSave);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(19, 112, 206));
        jMenu2.setForeground(new java.awt.Color(88, 88, 88));
        jMenu2.setText("Configuration");

        jMenuItem2.setText("Port");
        jMenuItem2.setPreferredSize(new java.awt.Dimension(150, 25));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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

    private void textField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Globals.serial_port.writeString(textField1.getText() + "\r\n");
                textField1.setText("");
            } catch (SerialPortException ex) {
                throw new RuntimeException(ex);
            }
        }
    }//GEN-LAST:event_textField1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Globals.serial_port.writeString(textField1.getText() + "\r\n");
            textField1.setText("");
        } catch (SerialPortException ex) {
            throw new RuntimeException(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Globals.wport.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void MenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSaveActionPerformed
        String content = textAreaTerm.getText();
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        
        try {
            FileWriter writer = new FileWriter(file);
            PrintWriter pr = new PrintWriter(writer);
            pr.print(content);
            pr.close();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MenuItemSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonConnect;
    private javax.swing.JMenuItem MenuItemSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextArea textAreaTerm;
    private javax.swing.JTextField textField1;
    // End of variables declaration//GEN-END:variables
}

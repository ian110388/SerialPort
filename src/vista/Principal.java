/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import com.google.gson.Gson;
import java.awt.Toolkit;
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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
        this.setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png")).getImage());
        initComponents();
        loadConfig();
        
        //this.setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png").getFile()).getImage());
        //this.setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png")).getImage());
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icon.png")));
        
        System.out.println(getClass().getResource("/resources/icon.png").getFile());
        
        //CREANDO INSTANCIA DE VENTANAS
        Globals.w_port = new Port();
        Globals.w_port.setTitle("Configuration");
        Globals.w_port.setLocationRelativeTo(null);
        Globals.w_port.setDefaultCloseOperation(HIDE_ON_CLOSE);
        Globals.w_port.setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png")).getImage());
        
        Globals.w_about = new Acerca();
        Globals.w_about.setTitle("About");
        Globals.w_about.setLocationRelativeTo(null);
        Globals.w_about.setDefaultCloseOperation(HIDE_ON_CLOSE);
        Globals.w_about.setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png")).getImage());
        
        
          
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
                    System.out.println(e.toString());
                }


            });

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
        textFieldMessage = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        ButtonConnect = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuFile = new javax.swing.JMenu();
        MenuItemSave = new javax.swing.JMenuItem();
        MenuConfiguration = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        MenuHelp = new javax.swing.JMenu();
        MenuItemAcerca = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setAutoscrolls(true);

        textAreaTerm.setEditable(false);
        textAreaTerm.setColumns(20);
        textAreaTerm.setFont(textAreaTerm.getFont());
        textAreaTerm.setRows(5);
        textAreaTerm.setFocusable(false);
        jScrollPane1.setViewportView(textAreaTerm);

        textFieldMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldMessageKeyPressed(evt);
            }
        });

        jButton1.setText("Send");
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
                        .addComponent(textFieldMessage)
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
                    .addComponent(textFieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setForeground(getBackground());

        MenuFile.setBackground(new java.awt.Color(19, 112, 206));
        MenuFile.setText("File");

        MenuItemSave.setText("Save As...");
        MenuItemSave.setPreferredSize(new java.awt.Dimension(150, 25));
        MenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSaveActionPerformed(evt);
            }
        });
        MenuFile.add(MenuItemSave);

        jMenuBar1.add(MenuFile);

        MenuConfiguration.setBackground(new java.awt.Color(19, 112, 206));
        MenuConfiguration.setForeground(new java.awt.Color(88, 88, 88));
        MenuConfiguration.setText("Configuration");

        jMenuItem2.setText("Port");
        jMenuItem2.setPreferredSize(new java.awt.Dimension(150, 25));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        MenuConfiguration.add(jMenuItem2);

        jMenuBar1.add(MenuConfiguration);

        MenuHelp.setText("Help");

        MenuItemAcerca.setText("Acerca");
        MenuItemAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemAcercaActionPerformed(evt);
            }
        });
        MenuHelp.add(MenuItemAcerca);

        jMenuBar1.add(MenuHelp);

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

    private void textFieldMessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldMessageKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Globals.serial_port.writeString(textFieldMessage.getText() + "\r\n");
                textFieldMessage.setText("");
            } catch (SerialPortException ex) {
                throw new RuntimeException(ex);
            }
        }
    }//GEN-LAST:event_textFieldMessageKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Globals.serial_port.writeString(textFieldMessage.getText() + "\r\n");
            textFieldMessage.setText("");
        } catch (SerialPortException ex) {
            throw new RuntimeException(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Globals.w_port.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void MenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSaveActionPerformed
        String content = textAreaTerm.getText();
        try {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                FileWriter writer = new FileWriter(file);
                PrintWriter pr = new PrintWriter(writer);
                pr.print(content);
                pr.close();
                writer.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_MenuItemSaveActionPerformed

    private void MenuItemAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemAcercaActionPerformed
        Globals.w_about.setVisible(true);
    }//GEN-LAST:event_MenuItemAcercaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonConnect;
    private javax.swing.JMenu MenuConfiguration;
    private javax.swing.JMenu MenuFile;
    private javax.swing.JMenu MenuHelp;
    private javax.swing.JMenuItem MenuItemAcerca;
    private javax.swing.JMenuItem MenuItemSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextArea textAreaTerm;
    private javax.swing.JTextField textFieldMessage;
    // End of variables declaration//GEN-END:variables
}

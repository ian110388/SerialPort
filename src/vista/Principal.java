/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import com.google.gson.Gson;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
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
    private String st ="";
    private String s = "";
    List<String> history = new ArrayList<>();
    int history_lenght = 0;
    int index = -1;
    
    //POP UP MENU ITEMS
    private boolean scroll = true;
    private JCheckBoxMenuItem AutoScroll;
    private JMenuItem SelectAll;
    private JMenuItem Copy;
    private JMenuItem Clear;
    
    //POP UP MENU DOWN ITEMS
    private JMenuItem Paste;
    
    private boolean connected = false;
            
    
    

    /**
     * Creates new form Principal
     */
    public Principal() {
        this.setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png")).getImage());
        initComponents();
        PopupMenuInit();
        PopupMenuDownInit();
        loadConfig();
        
        //this.setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png").getFile()).getImage());
        //this.setIconImage(new ImageIcon(getClass().getResource("/resources/icon.png")).getImage());
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icon.png")));
       
        
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
    
    public void PopupMenuInit() {
        // POP_CLEAR
        Clear = new JMenuItem("Clear");
        PopupMenu.add(Clear);
        // POP_SELECT ALL
        SelectAll = new JMenuItem("Select all");
        PopupMenu.add(SelectAll);
        // POP_COPY
        Copy = new JMenuItem("Copy");
        PopupMenu.add(Copy);
        
        // POP_AUTOSCROLL
        AutoScroll = new JCheckBoxMenuItem("AutoScroll");
        AutoScroll.setSelected(scroll);
        MenuItemAutoScroll.setSelected(scroll);
        PopupMenu.add(AutoScroll);
        textAreaTerm.setComponentPopupMenu(PopupMenu);
        
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textAreaTerm.setText("");
            }
        });
        
        Copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection sel = new StringSelection(textAreaTerm.getSelectedText());
                clip.setContents(sel, sel);
                
            }
        });
        
        SelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textAreaTerm.requestFocusInWindow();
                textAreaTerm.selectAll();
            }
        });
        
        
        AutoScroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(AutoScroll.isSelected()){
                    scroll = true;
                } else if (!AutoScroll.isSelected()) {
                    scroll = false;
                }
                MenuItemAutoScroll.setSelected(scroll);
            }
        });
    }
    
    public void PopupMenuDownInit() {
        // POP_PASTE
        Paste = new JMenuItem("Paste");
        PopupMenuDown.add(Paste);
        textFieldMessage.setComponentPopupMenu(PopupMenuDown);
        
        Paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                try {
                    String c = (String) clip.getData(DataFlavor.stringFlavor);
                    textFieldMessage.setText(c);
                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
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
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void Conectar(String port) throws SerialPortException{
        
        Globals.serial_port = new SerialPort(port);
        
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

                        s = new String(buf, 0, buf.length,"UTF8");
                        st += s;

                        if (st.contains("\r")){
                            textAreaTerm.append(st);
                            if(scroll){
                                textAreaTerm.setCaretPosition( textAreaTerm.getDocument().getLength() );
                            }
                            
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

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PopupMenu = new javax.swing.JPopupMenu();
        PopupMenuDown = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaTerm = new javax.swing.JTextArea();
        textFieldMessage = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ToolBar = new javax.swing.JToolBar();
        LabelConect = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        LabelPortStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuFile = new javax.swing.JMenu();
        MenuItemSave = new javax.swing.JMenuItem();
        MenuConfiguration = new javax.swing.JMenu();
        MenuItemPort = new javax.swing.JMenuItem();
        MenuItemAutoScroll = new javax.swing.JCheckBoxMenuItem();
        MenuHelp = new javax.swing.JMenu();
        MenuItemAcerca = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);

        textAreaTerm.setEditable(false);
        textAreaTerm.setColumns(20);
        textAreaTerm.setFont(textAreaTerm.getFont());
        textAreaTerm.setRows(5);
        jScrollPane1.setViewportView(textAreaTerm);

        textFieldMessage.setToolTipText("");
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

        ToolBar.setRollover(true);

        LabelConect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/unc.png"))); // NOI18N
        LabelConect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelConectMouseClicked(evt);
            }
        });
        ToolBar.add(LabelConect);
        ToolBar.add(jSeparator1);

        LabelPortStatus.setText("Disconnected");
        ToolBar.add(LabelPortStatus);

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
            .addComponent(ToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setForeground(getBackground());

        MenuFile.setBackground(new java.awt.Color(19, 112, 206));
        MenuFile.setText("File");

        MenuItemSave.setText("Save Output..");
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

        MenuItemPort.setText("Port");
        MenuItemPort.setPreferredSize(new java.awt.Dimension(150, 25));
        MenuItemPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemPortActionPerformed(evt);
            }
        });
        MenuConfiguration.add(MenuItemPort);

        MenuItemAutoScroll.setSelected(true);
        MenuItemAutoScroll.setText("Auto scroll");
        MenuItemAutoScroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemAutoScrollActionPerformed(evt);
            }
        });
        MenuConfiguration.add(MenuItemAutoScroll);

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
//        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            try {
//                Globals.serial_port.writeString(textFieldMessage.getText() + "\r\n");
//                history.add(textFieldMessage.getText());
//                textFieldMessage.setText("");
//            } catch (SerialPortException ex) {
//                throw new RuntimeException(ex);
//            }
//        } else if(evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            
//        }

        
        
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                try {
                    Globals.serial_port.writeString(textFieldMessage.getText() + "\r\n");
                    history.add(textFieldMessage.getText());
                    history_lenght = history.size();
//                    System.out.println(history_lenght);
                    textFieldMessage.setText("");
                } catch (SerialPortException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case KeyEvent.VK_UP:
                //textFieldMessage.setText(history.get(index));
                index = history_lenght-1 >= index+1 ? index+1 : index;
//                System.out.println("Tamaño:" + history_lenght + " index:" + index);
                textFieldMessage.setText(index >= 0?history.get(index):"");
                
                
//                if( history_lenght >= index+1 ){
//                    index++;
//                    System.out.println("Tamaño:" + history_lenght + " +index:" + index);
//                }
                
                break;
            case KeyEvent.VK_DOWN:
                //textFieldMessage.setText(history.get(index));
                index = history_lenght-1 >= index-1 && index >= 0? index-1 : index;
//                System.out.println("Tamaño:" + history_lenght + " index:" + index);
                textFieldMessage.setText(index >= 0?history.get(index):"");
                
                
//                if( history_lenght >= index-1 && index-1 >= 0){
//                    index--;
//                    System.out.println("Tamaño:" + history_lenght + " -index:" + index);
//                }
                
                break;
            default:
                //throw new AssertionError();
        }
    }//GEN-LAST:event_textFieldMessageKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Globals.serial_port.writeString(textFieldMessage.getText() + "\r\n");
            history.add(textFieldMessage.getText());
            history_lenght = history.size();
            textFieldMessage.setText("");
        } catch (SerialPortException ex) {
            throw new RuntimeException(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void MenuItemPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemPortActionPerformed
        Globals.w_port.setVisible(true);
    }//GEN-LAST:event_MenuItemPortActionPerformed

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

    private void MenuItemAutoScrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemAutoScrollActionPerformed
        // TODO add your handling code here:
        if(MenuItemAutoScroll.isSelected()){
            scroll = true;
        } else if(!MenuItemAutoScroll.isSelected()){
            scroll = false;
        }
        AutoScroll.setSelected(scroll);
    }//GEN-LAST:event_MenuItemAutoScrollActionPerformed

    private void LabelConectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelConectMouseClicked
        
        if(connected){
            try {
                Globals.serial_port.closePort();
                LabelConect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/unc.png")));
                connected = false;
                LabelPortStatus.setText("Disconnected");
            } catch (SerialPortException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
            
        } else {
            try {
                Globals.p.Conectar(Globals.port);
                LabelConect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/con.png")));
                connected = true;
                LabelPortStatus.setText("Connected");
            } catch (SerialPortException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_LabelConectMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(Globals.serial_port != null){
            if(Globals.serial_port.isOpened()){
                try {
                    Globals.serial_port.closePort();
                } catch (SerialPortException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelConect;
    private javax.swing.JLabel LabelPortStatus;
    private javax.swing.JMenu MenuConfiguration;
    private javax.swing.JMenu MenuFile;
    private javax.swing.JMenu MenuHelp;
    private javax.swing.JMenuItem MenuItemAcerca;
    private javax.swing.JCheckBoxMenuItem MenuItemAutoScroll;
    private javax.swing.JMenuItem MenuItemPort;
    private javax.swing.JMenuItem MenuItemSave;
    private javax.swing.JPopupMenu PopupMenu;
    private javax.swing.JPopupMenu PopupMenuDown;
    private javax.swing.JToolBar ToolBar;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JTextArea textAreaTerm;
    private javax.swing.JTextField textFieldMessage;
    // End of variables declaration//GEN-END:variables
}

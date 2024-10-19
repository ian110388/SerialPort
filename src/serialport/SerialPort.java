/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serialport;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import vista.Principal;

/**
 *
 * @author angelivan
 */
public class SerialPort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Globals.p = new Principal();
        Globals.p.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Globals.p.setSize(600,400);
        Globals.p.setLocationRelativeTo(null);
        Globals.p.setTitle(Globals.title);
        Globals.p.setVisible(true);
    }
    
}

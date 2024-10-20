/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serialport;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import javax.swing.UIManager;
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
        //FlatLaf.setGlobalExtraDefaults( Collections.singletonMap( "@accentColor", "#082f49" ) );
        UIManager.put( "Button.arc", 15 );
        //UIManager.put( "ToolBar.background",Color.getColor("#f8fafc") );
        UIManager.put( "Component.arc", 15 );
        //UIManager.put( "Component.arrowType", "chevron" );
        UIManager.put( "Component.arrowType", "triangle" );
        UIManager.put( "TextComponent.arc", 15 );
        
        //FlatLightLaf.setup();
        //FlatDarkLaf.setup();
        //FlatIntelliJLaf.setup();
        //FlatDarculaLaf.setup();
        //FlatArcOrangeIJTheme.setup();
        //FlatXcodeDarkIJTheme.setup();
        FlatMacDarkLaf.setup();
        //FlatMacLightLaf.setup();
        
        
        Globals.p = new Principal();
        Globals.p.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Globals.p.setSize(700,500);
        Globals.p.setLocationRelativeTo(null);
        Globals.p.setTitle(Globals.title);
        Globals.p.setVisible(true);
        
    }
    
}

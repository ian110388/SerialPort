/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serialport;

import vista.Port;
import vista.Principal;

/**
 *
 * @author angelivan
 */
public class Globals {
    // PRINCIPAL
    public static jssc.SerialPort serial_port = null;
    public static Principal p;
    public static String title = "Serial Port";
    // PORT
    public static Port wport;
    public static String port;
    public static int baud_rate;
    public static String parity;
    public static int bits;
    public static int stop_bits;
    public static String flow_control;
}

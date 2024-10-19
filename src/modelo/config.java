/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author angelivan
 */
public class config {
    private String port;
    private int baud_rate;
    private String parity;
    private int bits;
    private int stop_bits;
    private String flow_control;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getBaud_rate() {
        return baud_rate;
    }

    public void setBaud_rate(int baud_rate) {
        this.baud_rate = baud_rate;
    }

    public String getParity() {
        return parity;
    }

    public void setParity(String parity) {
        this.parity = parity;
    }

    public int getBits() {
        return bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }

    public int getStop_bits() {
        return stop_bits;
    }

    public void setStop_bits(int stop_bits) {
        this.stop_bits = stop_bits;
    }

    public String getFlow_control() {
        return flow_control;
    }

    public void setFlow_control(String flow_control) {
        this.flow_control = flow_control;
    }
    
    
}

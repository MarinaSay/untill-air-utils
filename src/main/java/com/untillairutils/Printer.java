package com.untillairutils;

public class Printer {
    public String name;
    public String brand;

    public Printer(String name, String brand){
       this.name=name;
       this.brand=brand;
    }

    public static Printer[] inputPrinters(){
        Printer[] printers = new Printer[2];
        printers[0] = new Printer("Printer1", "Citizen");
        printers[1] = new Printer("Printer2", "Citizen");
        return printers;
    }
}

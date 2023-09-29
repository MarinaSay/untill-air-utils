package com.untillairutils;

public class Printer {
    public String name;
    public String brand;

    public Printer(String name){
       this.name=name;

    }

    public static Printer[] inputPrinters(){
        Printer[] printers = new Printer[2];
        printers[0] = new Printer("Printer1");
        printers[1] = new Printer("printer2");
        return printers;
    }
}

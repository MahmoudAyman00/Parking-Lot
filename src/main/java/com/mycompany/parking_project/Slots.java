package com.mycompany.parking_project; 
import java.util.*;
import java.io.*;

public class Slots implements Serializable{
    public int slotID;
    public String date;
    public String time;
    public double fees;
    public boolean reserved;
//    private List<Reservation> reservations;

    public Slots(String date,String time, double fees,int slotID) {
        this.fees = fees;
        this.date = date;
        this.time=time;
        this.slotID= slotID;
//        this.reservations = new ArrayList<>();
    }

}

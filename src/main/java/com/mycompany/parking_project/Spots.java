package com.mycompany.parking_project;
import java.io.*;
import java.util.*;

public class Spots implements Serializable {

    public String type;
    public int ID;
    public List<Slots> slots;

    public Spots(String type, int ID) {
        this.type = type;
        this.ID = ID;
        this.slots = new ArrayList<>();
    }

    public void addSlot(String date, String time, double fees, int slotID) {
        Slots newSlot = new Slots(date, time, fees, slotID);
        slots.add(newSlot);
    }

    public void removeSlot(int slotiD) {
        Iterator<Slots> iterator = slots.iterator();
        while (iterator.hasNext()) {
            Slots slot = iterator.next();
            if (slot.slotID == slotiD) {
                iterator.remove();
                System.out.println("Slot Number: " + slotiD + " Removed");
                return;
            }
        }
        System.out.println("Slot Number: " + slotiD + " not found.");
    }

    public void displayAllSlots() {
        for (Slots slots : slots) {
            System.out.println("Slot ID: " + slots.slotID + ", Slot Date: " + slots.date + ", Slot Time: " + slots.time + ", Slot Fees :" + slots.fees);
        }
    }

    public List<Slots> getSlots() {
        return slots;
    }

    public void setSlots(List<Slots> slots) {
        this.slots = slots;
    }

}

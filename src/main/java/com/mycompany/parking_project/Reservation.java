package com.mycompany.parking_project;
import java.util.*;
import java.io.*;

public class Reservation {
    public List<Slots> slots;
    public int hours;

    public Reservation(List<Slots> slots, int hours) {
        this.slots = slots;
        this.hours = hours;
    }
}


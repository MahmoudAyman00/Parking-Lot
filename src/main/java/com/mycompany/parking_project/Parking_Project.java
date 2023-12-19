package com.mycompany.parking_project;
import java.util.*;
import java.io.*;


public class Parking_Project {

    public static void main(String[] args) {

        System.out.println("Welcome Sur To Our Parking");
        System.out.println("Our Parking Lot manages a system for \na garage parking spots And reservation");
        System.out.println("Pleas Enter chose in decimal number ");
        System.out.println("1- Admin ");
        System.out.println("2- owner");
        Scanner get = new Scanner(System.in);
        int type = get.nextInt();
        if (type == 1) {
            Admin A = new Admin();
            System.out.println("Pleas Enter Your Username");
            Scanner get2 = new Scanner(System.in);
            String name = get2.next();
            System.out.println("Pleas Enter Your Password");
            Scanner get3 = new Scanner(System.in);
            String password = get3.next();
            A.login(name, password);
         
        } else if (type == 2) {
            Owners O = new Owners();
            System.out.println("(If this Your First Time Here Please Choose 2)");
            System.out.println("1 -Login\n2 -Signup");
            Scanner get4 = new Scanner(System.in);
            int select = get4.nextInt();
            if(select==1){
            System.out.println("Pleas Enter Your Id");
            Scanner get5 = new Scanner(System.in);
            int id = get5.nextInt();
            System.out.println("Pleas Enter Your Password");
            Scanner get6 = new Scanner(System.in);
            String ownerpassword = get6.next();
            O.login(id, ownerpassword);
            }
            else if(select==2){
            System.out.println("Pleas Enter Your Id");
            Scanner get7 = new Scanner(System.in);
            int id2 = get7.nextInt();
            System.out.println("Pleas Enter Your Name");
            Scanner get8 = new Scanner(System.in);
            String ownername = get8.next();
            System.out.println("Pleas Enter Your Password");
            Scanner get9 = new Scanner(System.in);
            String newownerpassword = get9.next();
            System.out.println("Pleas Enter Your licenseNumber");
            Scanner get10 = new Scanner(System.in);
            String ownerlicenseNumber = get10.next();
                O.signUp(id2, ownername, newownerpassword, ownerlicenseNumber);
            }
        } else {
            System.out.println("Invalid Number");
        }
        
        
        

//        Admin admin=new Admin();    
//
//            List<Spots> loadedSpots = admin.loadSpotsFromFile(admin.spotfile);
//
//            for (Spots spot : loadedSpots) {
//                System.out.println("Spot ID: " + spot.ID + ", Spot Type: " + spot.type);
//                List<Slots> slots = spot.getSlots();
//                for (Slots slot : slots) {
//                    System.out.println("Slot ID: " + slot.slotID + ", Date: " + slot.date+ ", Time: " + slot.time + ", Fees: " + slot.fees);
//                }
//            }
     
    }
}

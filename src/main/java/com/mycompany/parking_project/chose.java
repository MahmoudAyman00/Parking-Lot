package com.mycompany.parking_project;
import java.io.*;
import java.util.*;

public class chose extends Admin {

    Admin A = new Admin();

    public void getchose(int x) {
        if (x == 1) {
            System.out.println("Enter Type Of Spot : [normal , bike , large]");
            Scanner get = new Scanner(System.in);
            String Name = get.next().toLowerCase();
            System.out.println("Enter Id Of Spot");
            Scanner get1 = new Scanner(System.in);
            int i = get1.nextInt();
            A.addSpot(Name, i);

        } else if (x == 2) {
            System.out.println("Enter ID Spot To Slot");
            Scanner get = new Scanner(System.in);
            int F = get.nextInt();
            System.out.println("Enter ID Slot");
            Scanner get4 = new Scanner(System.in);
            int m = get4.nextInt();
            System.out.println("Enter Date Of Slot");
            Scanner get1 = new Scanner(System.in);
            String data = get1.next();
            System.out.println("Enter Time Of Slot");
            Scanner get2 = new Scanner(System.in);
            String Time = get2.next();
            System.out.println("Enter Fees To Slot");
            Scanner get3 = new Scanner(System.in);
            double S = get3.nextDouble();

            A.addSlots(F, data, Time, S, m);

        } else if (x == 3) {
            System.out.println("Enter Id Of Spot");
            Scanner get1 = new Scanner(System.in);
            int i = get1.nextInt();
            A.deletespot(i);

        } else if (x == 4) {
            System.out.println("Enter ID Spot");
            Scanner get3 = new Scanner(System.in);
            int S = get3.nextInt();
            System.out.println("Enter ID Slot");
            Scanner get1 = new Scanner(System.in);
            int id = get1.nextInt();
            A.deleteSlot(S, id);
        } else if (x == 5) {
            A.displayAllSpots();
        } else if (x == 6) {
            A.displayAllAvailableSlots();
        } else if (x == 8) {
             A.saveSpotsToFile(A.spot, A.spotfile);
        }else if (x == 7) {
            A.displayAllOwners();
        }

}
}

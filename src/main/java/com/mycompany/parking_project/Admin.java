package com.mycompany.parking_project;
import java.io.*;
import java.util.*;

public class Admin implements Serializable {

    public final String Name = "admin";
    public final String password = "admin";
    public List<Spots> spot;
    public List<Owners> owner;
//    File ownerfile = new File("C:\\Users\\maste\\Downloads\\Parking Lot\\owners_data.data");
    File spotfile = new File("C:\\Users\\maste\\Downloads\\Parking Lot\\All_Spots_File.data");

    
    public Admin() {
    spot = loadSpotsFromFile(spotfile);
//    owner = loadOwnerListFromFile( ownerfile);
    }

    public void login(String enteredname, String enteredPassword){
        if (enteredname.equals(Name)) {
            if (enteredPassword.equals(password)) {

                System.out.println("Success Login\n");
                System.out.println("pleas enter chose from 1 to 6 :\n");
                System.out.println("1- Add Spots. \n");
                System.out.println("2- Add Slots. \n");
                System.out.println("3- Delete Spots. \n");
                System.out.println("4- Delete Slots. \n");
                System.out.println("5- Display All Spots. \n");
                System.out.println("6- Display all available slots for all spots. \n");
                System.out.println("7- Display All Owners");

                chose s = new chose();

                Scanner we = new Scanner(System.in);
                int x = we.nextInt();
                s.getchose(x);
                while (true) {
                    System.out.println("Do You TO Continue....?");
                    System.out.println("1-Yes\n2-No");
                    Scanner wee = new Scanner(System.in);
                    int d = wee.nextInt();
                    if (d == 1) {
                        System.out.println("pleas enter chose from 1 to 7 :");
                        System.out.println("1- Add Spots. \n");
                        System.out.println("2- Add Slots. \n");
                        System.out.println("3- Delete Spots. \n");
                        System.out.println("4- Delete Slots. \n");
                        System.out.println("5- Display All Spots. \n");
                        System.out.println("6- Display all available slots for all spots. \n");
                        System.out.println("7- Display All Owners.\n");
                        System.out.println("8- To Save This Updates in Files");
                        Scanner w = new Scanner(System.in);
                        int m = w.nextInt();
                        s.getchose(m);
                    } else if (d == 2) {

                        System.out.println("Thank You");
                        break;
                    } else {
                        System.out.println("Invalid Number");
                    }
                }

            } else {
                System.out.println("Wrong Password!");

            }
        } else {
            System.out.println("Wrong Username!");

        }

    }

    public void addSpot(String type, int spotID) {
    
    boolean spotExists = spot.stream().anyMatch(s -> s.ID == spotID);

    if (spotExists) {
        System.out.println("Spot ID already exists. Please choose a different ID.");
    } else {
        Spots newSpot = new Spots(type, spotID);
        spot.add(newSpot);
        System.out.println("Spot Added Successfully");
    }
}

    public void addSlots(int spotID, String date, String time, double fees, int slotid) {
    boolean spotFound = false;

    for (Spots spots : spot) {
        if (spots.ID == spotID) {
            boolean slotExists = spots.getSlots().stream().anyMatch(slot -> slot.slotID == slotid);

            if (slotExists) {
                System.out.println("Slot ID already exists for this spot. Please choose a different slot ID.");
                spotFound = true;
            } else {
                spots.addSlot(date, time, fees, slotid);
                spotFound = true;
                System.out.println("Slot Added Successfully");
            }
            break;
        }
    }

    if (!spotFound) {
        System.out.println("Spot ID not found");
    }
}

    public void deletespot(int spotID) {

        boolean spotFound = false;
        for (Spots spots : spot) {
            if (spots.ID == spotID) {
                spot.remove(spots);
                spotFound = true;
                System.out.println("Spot deleted Successfully");
                break;
            }
        }
        if (!spotFound) {
            System.out.println("Spot ID not found");
        }
    }

    public void deleteSlot(int spotID, int slotid) {
        for (Spots s : spot) {
            if (s.ID == spotID) {
                s.removeSlot(slotid);
                return;
            }
        }
        System.out.println("Spot ID " + spotID + " not found.");
    }

    public void displayAllSpots() {
        for (Spots spots : spot) {
            System.out.println("Spot Type: " + spots.type + ", Spot ID: " + spots.ID);
        }
    }
    
    public void displayAllOwners() {
        for (Owners owners : owner) {
            System.out.println("Owner ID: " + owners.ID + ", Owner Name: " + owners.getName());
        }
    }

    public void displayAllAvailableSlots() {
        for (Spots spots : spot) {
            System.out.println("Spot Type: " + spots.type + ", Spot ID: " + spots.ID);
            spots.displayAllSlots();
        }
    }

    public static void saveSpotsToFile(List<Spots> spots, File spotfile) {
        try {
            if (!spotfile.exists()) {
                spotfile.createNewFile();
            }
            PrintStream ps = new PrintStream(spotfile);
            for (Spots spot : spots) {
                StringBuilder spotData = new StringBuilder();
                spotData.append(spot.ID).append("|").append(spot.type).append("|");

                // Iterate through slots in the current spot and save their data
                for(int i=0;i<spot.slots.size();i++)
            {
                if(i==spot.slots.size()-1)
                    spotData.append(spot.slots.get(i).slotID).append(":").append(spot.slots.get(i).date).append(":").append(spot.slots.get(i).time).append(":").append(spot.slots.get(i).fees);
                else
                    spotData.append(spot.slots.get(i).slotID).append(":").append(spot.slots.get(i).date).append(":").append(spot.slots.get(i).time).append(":").append(spot.slots.get(i).fees).append(",");
            }
            
            /*for (Slots slot : spot.slots) {
                spot.slots.size();
                spotData.append(slot.slotID).append(":").append(slot.date).append(":").append(slot.time).append(":").append(slot.fees).append(",");
            }*/

            // Write the spot's data to the file
            ps.println(spotData.toString());
                
        }
            
        ps.close();
        System.out.println("<< The Updates Saved in File Done >>");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static List<Spots> loadSpotsFromFile(File spotFile) {
    List<Spots> spotList = new ArrayList<>();
    try {
        Scanner scanner = new Scanner(spotFile);

        while (scanner.hasNextLine()) {
            String spotLine = scanner.nextLine();
            String[] spotData = spotLine.split("\\|");

            int spotID = Integer.parseInt(spotData[0]); // Assuming the first element is spot ID
            String spotType = spotData[1]; // Assuming the second element is spot type

            // Extracting slot information from the third element onwards
            String[] slotsData = spotData[2].split(",");
            List<Slots> slotsList = new ArrayList<>();

            for (String slotInfo : slotsData) {
                String[] slotParts = slotInfo.split(":");
                int slotID = Integer.parseInt(slotParts[0]); // Assuming the first part is slot ID
                String date = slotParts[1];
                String time = slotParts[2];// Assuming the second part is date
                double fees = Double.parseDouble(slotParts[3]); // Assuming the third part is fees

                // Creating a Slot object and adding it to the list of slots for this spot
                Slots slot = new Slots(date, time, fees, slotID);
                slotsList.add(slot);
            }

            // Creating the Spots object and adding it to the list
            Spots spot = new Spots(spotType, spotID);
            spot.setSlots(slotsList); // Assuming you have a method to set slots in Spots class
            spotList.add(spot);
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        // Handle the exception (e.g., logging, error message, etc.)
        e.printStackTrace(); // Print the stack trace for demonstration purposes
    }
    return spotList;
}

//   
}

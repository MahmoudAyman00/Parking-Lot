package com.mycompany.parking_project;

import java.io.*;
import java.util.*;

public class Owners implements Serializable {

    private static int lastAssignedId = 0;
    private String name;
    private String password;
    public int ID;
    private String licenseNumber;
    private List<Vehicle> vehicles;
    private List<Owners> ownerListCopy;
    private List<Reservation> reservations;
    public List<Spots> displayAllSpots;
    File ownerfile = new File("C:\\Users\\maste\\Downloads\\Parking Lot\\owners_data.data");
    File spotfile = new File("C:\\Users\\maste\\Downloads\\Parking Lot\\All_Spots_File.data");
    File Reservedspotfile = new File("C:\\Users\\maste\\Downloads\\Parking Lot\\All_Reserved_Spots_File.data");

    public Owners() {
        this.ownerListCopy = copyOwnerList(ownerfile);
        this.displayAllSpots=loadSpotsFromFile(spotfile);
        
    }
    
    public void signUp(int ID, String name, String password, String licenseNumber) {
        if (!isExistingID(ID)) {
            this.ID = ID;
            this.name = name;
            this.password = password;
            this.licenseNumber = licenseNumber;
            saveOwnersToFile(this, ownerfile);
            System.out.println("Signup Successful!");
            System.out.println("Please Run Again To Login");
        } else {
            System.out.println("ID already exists. Please choose another ID.");
        }
    }

    public boolean isExistingID(int ID) {
        for (Owners owner : ownerListCopy) {
            if (owner.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    public boolean login(int enteredID, String enteredPassword) {
        Owners owner = findOwnerByID(enteredID);
        if (owner != null && owner.getPassword().equals(enteredPassword)) {
            this.name = owner.getName();
            this.password = owner.getPassword();
            this.ID = owner.getID();
            this.licenseNumber = owner.getLicenseNumber();
            System.out.println("Login Success");
            return true;
        }
        System.out.println("Error Login!");
        return false;
    }

    public static void saveOwnersToFile(Owners owner, File ownerfile) {
        try {
            if (!ownerfile.exists()) {
                ownerfile.createNewFile();
            } else {
                FileOutputStream fos = new FileOutputStream(ownerfile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(owner);
                oos.close();
                fos.close();
                System.out.println("Done!");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private List<Owners> loadOwnerListFromFile(File ownerFile) {
        try {
            FileInputStream fileIn = new FileInputStream(ownerFile);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            List<Owners> ownersList = new ArrayList<>();
            while (fileIn.available() > 0) {
                Owners owner = (Owners) objectIn.readObject();
                ownersList.add(owner);
            }

            objectIn.close();
            return ownersList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); 
    }

    private List<Owners> copyOwnerList(File ownerFile) {
        List<Owners> originalList = loadOwnerListFromFile(ownerFile);
        List<Owners> copyList = new ArrayList<>(originalList);
        return copyList;
    }

    public Owners findOwnerByID(int enteredID) {
        for (Owners owner : ownerListCopy) {
            if (owner.getID() == enteredID) {
                return owner;
            }
        }
        return null;
    }

    public List<Owners> getOwnerListCopy() {
        return ownerListCopy;
    }

    public static int getLastAssignedId() {
        return lastAssignedId;
    }

    public static void setLastAssignedId(int lastAssignedId) {
        Owners.lastAssignedId = lastAssignedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public static List<Slots> loadReservedSlotsFromFile(File reservedSlotsFile) {
    List<Slots> slotList = new ArrayList<>();
    try {
        Scanner scanner = new Scanner(reservedSlotsFile);

        while (scanner.hasNextLine()) {
            String slotLine = scanner.nextLine();
            String[] slotData = slotLine.split(":"); // Assuming the format is "slotID:date:time:fees"

            int slotID = Integer.parseInt(slotData[0]); // Assuming the first element is slot ID
            String date = slotData[1];
            String time = slotData[2];
            double fees = Double.parseDouble(slotData[3]);

            // Creating a Slot object and adding it to the list
            Slots slot = new Slots(date, time, fees, slotID);
            slotList.add(slot);
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        // Handle the exception (e.g., logging, error message, etc.)
        e.printStackTrace(); // Print the stack trace for demonstration purposes
    }
    return slotList;
}

    public static void saveReservedSlotsToFile(List<Slots> reservedSlots, File reservedSlotsFile) {
    try {
        if (!reservedSlotsFile.exists()) {
            reservedSlotsFile.createNewFile();
        }
        PrintStream ps = new PrintStream(reservedSlotsFile);
        for (Slots slot : reservedSlots) {
            StringBuilder slotData = new StringBuilder();
            slotData.append(slot.slotID).append(":")
                    .append(slot.date).append(":")
                    .append(slot.time).append(":")
                    .append(slot.fees);

            // Write the slot's data to the file
            ps.println(slotData.toString());
        }
        ps.close();
        System.out.println("<< The Updates Saved in File Done >>");
    } catch (IOException e) {
        System.out.println(e);
    }
}
}

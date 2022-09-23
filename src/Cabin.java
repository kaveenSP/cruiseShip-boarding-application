import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cabin {
    Scanner input = new Scanner(System.in);
    private String availability;
    //Array of Passenger objects which is inside each cabin object
    private final Passenger[] passengers = new Passenger[3];

    //default constructor
    public Cabin() {

    }

    /**overloaded constructor
     * @param availability refers to cabin availability
     * If cabin is full, availability is set to Occupied
     * If cabin is empty, availability is set to Empty
     * Else availability is set to Vacant*/
    public Cabin(String availability) {
        this.availability = availability;
    }

    /** This methods initializes cabins
     * @param pArray Array of cabin objects
     * */
    public void initialize(Cabin[] pArray) {
        for(int i = 0; i< pArray.length; i++) {
            pArray[i] = new Cabin("Empty");
        }
    }

    /** Checks the number of cabins with a respective availability
     *@param pArray Array of cabin objects
     *@param availability availability of the cabin
     *@return count number of cabins with the respective availability */
    public int checkCabinAvailability(Cabin[] pArray, String availability) {
        int count = 0;
        for(int z = 0; z < pArray.length; z++){
            if (pArray[z].availability.equalsIgnoreCase(availability)){
                count++;
            }
        }
        return count;
    }

    /**checks passenger count in a cabin
     * @param pArray Array of cabin objects
     * @param index index of the respective cabin to be checked
     * @return passNum number of passengers inside the respective cabin */
    public int checkPassengerCount(Cabin[] pArray, int index) {
        int passNum = 0;
        for(int j = 0; j < this.passengers.length; j++) {
            if(pArray[index].passengers[j] != null) {
                passNum++;
            }
        }
        return passNum;
    }

    /**This method views all cabins and passengers
     * @param pArray Array of cabin objects*/
    public void viewCabins(Cabin[] pArray) {
        for(int i = 0; i < pArray.length; i++) {
            //checks if cabin is empty
            if(!pArray[i].availability.equalsIgnoreCase("Empty")) {
                System.out.println("Cabin " + (i+1) + " : " + pArray[i].availability);
                for (int j = 0; j < this.passengers.length; j++) {
                    if(pArray[i].passengers[j] != null) {
                        //displays passenger first name and surname
                        System.out.println("      Passenger " + (j+1) + " : " + pArray[i].passengers[j].getFirstName() + " " + pArray[i].passengers[j].getSurname());
                    }
                }
            } else {
                System.out.println("Cabin " + (i+1) + " : " + pArray[i].availability);
            }
            System.out.println("-------------------------------------------------------");
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    /**This method adds passengers to a selected cabin
     * @param pArray Array of cabin objects*/
    public void addCustomer(Cabin[] pArray) {
        //checks if all cabins are occupied
        if (checkCabinAvailability(pArray, "Occupied") == pArray.length) {
            System.out.println("ALL CABINS ARE OCCUPIED !");
        } else {
            boolean check = true; //controls the while loop below
            String fName, sName;
            double expense;
            while (check) {
                try {
                    System.out.print("\nPlease Select Your Cabin (1 - "+ pArray.length +"): ");
                    //user selected cabin
                    int exactCabinNo = input.nextInt();
                    input.nextLine();
                    int index = exactCabinNo - 1;
                    //checks if selected cabin is available
                    if (!pArray[index].availability.equalsIgnoreCase("Occupied")) {
                        for (int i = 0; i < this.passengers.length; i++) {
                            if (pArray[index].passengers[i] == null) {
                                System.out.print("\nEnter Passenger First Name : ");
                                fName = input.nextLine();
                                //checks if user input is blank
                                if (fName.equals("") || fName.equals(" ")) {
                                    System.out.println("Name Field Cannot Be Empty !\n\nWould you like to try again ? (Y/N)");
                                    String retry = input.nextLine();
                                    if (retry.equalsIgnoreCase("Y")) {
                                        break;
                                    } else if (retry.equalsIgnoreCase("N")) {
                                        System.out.println("You Have Not Been Added To A Cabin.");
                                        check = false;
                                        break;
                                    } else {
                                        System.out.println("\nInvalid Option Selected. You will now be redirected to the Menu.");
                                        check = false;
                                        break;
                                    }
                                }
                                System.out.print("\nEnter Passenger Surname : ");
                                sName = input.nextLine();
                                //checks if user input is blank
                                if (sName.equals("") || sName.equals(" ")) {
                                    System.out.println("Name Field Cannot Be Empty !\n\nWould you like to try again ? (Y/N)");
                                    String retry = input.nextLine();
                                    if (retry.equalsIgnoreCase("Y")) {
                                        break;
                                    } else if (retry.equalsIgnoreCase("N")) {
                                        System.out.println("You Have Not Been Added To A Cabin.");
                                        check = false;
                                        break;
                                    } else {
                                        System.out.println("\nInvalid Option Selected. You will now be redirected to the Menu.");
                                        check = false;
                                        break;
                                    }
                                }
                                System.out.print("\nExpenses For The Passenger : ");
                                expense = input.nextDouble();
                                input.nextLine();
                                //Checks if entered expense value is greater than 0
                                if (expense > 0) {
                                    pArray[index].passengers[i] = new Passenger(fName, sName, expense);
                                    pArray[index].availability = "Vacant";
                                    System.out.println("\n--------------------------------------------------");
                                    System.out.println("| You have successfully booked a slot in Cabin " + exactCabinNo + " |");
                                    System.out.println("--------------------------------------------------");
                                    check = false;
                                    break;
                                } else {
                                    System.out.println("Invalid Value Entered !\n\nWould you like to try again ? (Y/N)");
                                    String retry = input.nextLine();
                                    if (retry.equalsIgnoreCase("Y")) {
                                        break;
                                    } else if (retry.equalsIgnoreCase("N")) {
                                        System.out.println("You Have Not Been Added To A Cabin.");
                                        check = false;
                                        break;
                                    } else {
                                        System.out.println("\nInvalid Option Selected. You will now be redirected to the Menu.");
                                        check = false;
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("\nThe selected cabin is already occupied ! Would you like to try again ? (Y/N)");
                        String retry = input.nextLine();
                        if (retry.equalsIgnoreCase("Y")) {
                        } else if (retry.equalsIgnoreCase("N")) {
                            System.out.println("You Have Not Been Added To A Cabin.");
                            check = false;
                        } else {
                            System.out.println("\nInvalid Option Selected. You will now be redirected to the Menu.");
                            check = false;
                        }
                    }
                    if (checkPassengerCount(pArray, index) == this.passengers.length) {
                        pArray[index].availability = "Occupied";
                    }
                } catch (InputMismatchException e) {
                    //If user enters a different data type than the expected type this block will executed
                    System.out.println("Invalid Value Detected !\n\nWould you like to try again ? (Y/N)");
                    input.nextLine();
                    String retry = input.nextLine();
                    if (retry.equalsIgnoreCase("Y")) {
                        check = true;
                    } else if (retry.equalsIgnoreCase("N")) {
                        System.out.println("You Have Not Been Added To A Cabin.");
                        check = false;
                    } else {
                        System.out.println("Invalid Option Selected. You will now be redirected to the Menu.");
                        check = false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //If user selects a cabin that is not defined, this code block executes
                    System.out.println("Invalid Cabin Number Entered.\n\nWould you like to try again ? (Y/N)");
                    String retry = input.nextLine();
                    if (retry.equalsIgnoreCase("Y")) {
                        check = true;
                    } else if (retry.equalsIgnoreCase("N")) {
                        System.out.println("You Have Not Been Added To A Cabin.");
                        check = false;
                    } else {
                        System.out.println("\nInvalid Option Selected. You will now be redirected to the Menu.");
                        check = false;
                    }
                }
            }
            System.out.println("-----------------------------------------------------------------------");
        }
    }

    /**This method displays cabins without any passengers
     * @param pArray Array of cabin objects*/
    public void displayEmptyCabins(Cabin[] pArray) {
        if(checkCabinAvailability(pArray,"Empty") == 0) {
            System.out.println("ALL CABINS ARE OCCUPIED.");
        } else {
            for (int i = 0; i < pArray.length; i++) {
                if (pArray[i].availability.equalsIgnoreCase("Empty")) {
                    System.out.println("Cabin " + (i + 1) + " : " + pArray[i].availability);
                }
            }
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    /**This method is used to delete passengers from cabin
     * Passenger is found using full name.
     * @param pArray Array of passenger objects*/
    public void deleteCustomer(Cabin[] pArray) {
        boolean deleted = false; //to confirm the passenger is deleted
        int cabinIndex = 0;
        Passenger pasObj = new Passenger();
        //check if all cabins are empty
        if(checkCabinAvailability(pArray,"Empty") == pArray.length) {
            System.out.println("\nALL CABINS ARE EMPTY !");
        } else {
            System.out.println("Enter Customer Full Name : ");
            String passengerName = input.nextLine();
            for(int i = 0; i < pArray.length; i++) {
                for (int j = 0; j < this.passengers.length; j++) {
                    if(pArray[i].passengers[j] != null) {
                        if ((pArray[i].passengers[j].getFirstName() + pArray[i].passengers[j].getSurname()).equalsIgnoreCase(passengerName.replaceAll("\\s", ""))) {
                            pArray[i].passengers[j] = null;
                            System.out.println(passengerName + " has been removed from Cabin " + (i+1));
                            pArray[i].availability = "Vacant";
                            pasObj.reducePassengerCount();
                            deleted = true;
                            cabinIndex = i; //cabin index of the deleted passenger's cabin
                            break;
                        }
                    }
                }
                if(deleted) {
                    //if customer is deleted, then breaks out of the loop
                    break;
                }
            }
            //if passenger is not found in cabins
            if(!deleted) {
                System.out.println("There is no cabin booked under " + passengerName + "'s name.");
            }
        }
        //sets cabin availability to empty if the cabin is empty
        if(checkPassengerCount(pArray,cabinIndex) == 0) {
            pArray[cabinIndex].availability = "Empty";
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    /**overloaded method to delete customers when a queue is available
     *Passenger is found using full name.
     * @param pArray Array of passenger objects
     * @param pArrayPassenger Queue array with passenger objects*/
    public void deleteCustomer(Cabin[] pArray, Passenger[] pArrayPassenger) {
        boolean deleted = false;//to confirm the passenger is deleted
        int cabinIndex = 0;
        Passenger pasObj = new Passenger();
        //check if all cabins are empty
        if(checkCabinAvailability(pArray,"Empty") == pArray.length) {
            System.out.println("\nALL CABINS ARE EMPTY !");
        } else {
            System.out.println("Enter Customer Full Name : ");
            String passengerName = input.nextLine();
            for (int i = 0; i < pArray.length; i++) {
                for (int j = 0; j < this.passengers.length; j++) {
                    if (pArray[i].passengers[j] != null) {
                        if ((pArray[i].passengers[j].getFirstName() + pArray[i].passengers[j].getSurname()).equalsIgnoreCase(passengerName.replaceAll("\\s", ""))) {
                            System.out.println(passengerName + " has been removed from Cabin " + (i + 1));
                            //calls dequeue method from passenger class
                            pArray[i].passengers[j] = pasObj.dequeue(pArrayPassenger,pArray, i);
                            deleted = true;
                            cabinIndex = i;
                            break;
                        }
                    }
                }
                //if customer is deleted, then breaks out of the loop
                if (deleted) {
                    break;
                }
            }
            //if passenger is not found in cabins
            if (!deleted) {
                System.out.println("There is no cabin booked under " + passengerName + "'s name.");
            }
        }
        int passNum = checkPassengerCount(pArray,cabinIndex);
        //checks and sets the respective cabin availability to for the cabin
        if(passNum == this.passengers.length) {
            pArray[cabinIndex].availability = "Occupied";
        } else if(passNum == 0) {
            pArray[cabinIndex].availability = "Empty";
        } else {
            pArray[cabinIndex].availability = "Vacant";
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    /**This method finds customer cabin by getting user input of passengers full name
     * @param pArray Array of passenger objects*/
    public void findCustomerByName(Cabin[] pArray) {
        boolean found = false; //to check if the passenger is found
        //checks if all cabins are empty
        if(checkCabinAvailability(pArray,"Empty") == pArray.length) {
            System.out.println("\nALL CABINS ARE EMPTY !");
        } else {
            System.out.println("Enter Customer Full Name : ");
            //gets customer full name
            String passengerName = input.nextLine();
            for(int i = 0; i < pArray.length; i++) {
                for (int j = 0; j < this.passengers.length; j++) {
                    if(pArray[i].passengers[j] != null) {
                        if ((pArray[i].passengers[j].getFirstName() + pArray[i].passengers[j].getSurname()).equalsIgnoreCase(passengerName.replaceAll("\\s", ""))) {
                            //displays customer cabin number
                            System.out.println(passengerName + " is in Cabin " + (i+1));
                            found = true;
                            break;
                        }
                    }
                }
                //if customer is found, then breaks out of the loop
                if(found) {
                    break;
                }
            }
            //if customer is not found
            if(!found) {
                System.out.println("There is no cabin booked under " + passengerName + "'s name.");
            }
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    /**This method writes customer first name, surname and expenses per passenger into a text file
     * @param pArray Array of passenger objects*/
    public void writeToFile(Cabin[] pArray) {
        try {
            FileWriter writer = new FileWriter("CruiseShip_data.txt");
            for (int i = 0; i < pArray.length; i++) {
                if (pArray[i].availability.equalsIgnoreCase("Empty")) {
                    writer.write("\nCabin " + (i + 1) + " : " + pArray[i].availability + "\n-----------------------------------------------------------------------\n");
                } else {
                    writer.write("Cabin " + (i+1) + " : " + pArray[i].availability);
                    for(int j = 0; j < this.passengers.length; j++) {
                        if(pArray[i].passengers[j] != null) {
                            writer.write("\n    Passenger " + (j+1) + " : " + pArray[i].passengers[j].getFirstName() + " " + pArray[i].passengers[j].getSurname() + " - $" + pArray[i].passengers[j].getExpenses());
                        }
                    }
                    writer.write("\n-----------------------------------------------------------------------\n");
                }
            }
            System.out.println("Successfully wrote data to CruiseShip_data.txt");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    /**This method reads customer first name, surname and expenses per passenger from an existing text file and displays them in console*/
    public void readFromFile() {
        File inputFile = new File("CruiseShip_data.txt");
        try {
            Scanner fileData = new Scanner(inputFile);
            while (fileData.hasNext()) {
                System.out.println(fileData.nextLine());
            }
            fileData.close();
        } catch (FileNotFoundException e) {
            //If method was called before storing data
            System.out.println("Data has not been Stored yet !");
        }
    }

    /**This method creates a separate array with passenger full names
     * Then the names are sorted in alphabetical order
     * @param pArray Array of cabin objects*/
    public void sortArrayData(Cabin[] pArray) {
        //checks if all cabins are empty
        if(checkCabinAvailability(pArray,"Empty") == pArray.length) {
            System.out.println("\nALL CABINS ARE EMPTY !");
        } else {
            Passenger forCount = new Passenger();
            //new array for passenger names
            String[] sortedArray = new String[forCount.getPassengerCount()];
            int sortedArrayIndex = 0;
            //initializing
            for(int i = 0; i < pArray.length; i++) {
                for(int j = 0; j < this.passengers.length; j++) {
                    if(pArray[i].passengers[j] != null) {
                        sortedArray[sortedArrayIndex] = pArray[i].passengers[j].getFirstName() + " " + pArray[i].passengers[j].getSurname();
                        sortedArrayIndex++;
                    }
                }
                if(sortedArrayIndex == forCount.getPassengerCount()) {
                    break;
                }
            }
            //sorting
            for(int x = 0; x < sortedArray.length ; x++) {
                for (int y = x + 1; y < forCount.getPassengerCount(); y++) {
                    if ((sortedArray[x].replaceAll("\\s","")).compareToIgnoreCase(sortedArray[y].replaceAll("\\s","")) > 0) {
                        String temp = sortedArray[x];
                        sortedArray[x] = sortedArray[y];
                        sortedArray[y] = temp;
                    }
                }
            }
            //displaying
            for(int z = 0; z < sortedArray.length; z++) {
                System.out.println((z+1) + ". " + sortedArray[z]);
            }
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    /**This method displays customer expenses per person and total expenses for all passengers
     * @param pArray Array of cabin objects*/
    public void findExpenses(Cabin[] pArray) {
        double totalExpenses = 0; //holds total expenses
        //checks if all cabins are empty
        if(checkCabinAvailability(pArray,"Empty") == pArray.length) {
            System.out.println("\nALL CABINS ARE EMPTY !");
        } else {
            for(int i = 0; i < pArray.length; i++) {
                for(int j = 0; j < this.passengers.length; j++) {
                    if(pArray[i].passengers[j] != null) {
                        //displays expenses per passenger
                        System.out.println(pArray[i].passengers[j].getFirstName() + " " + pArray[i].passengers[j].getSurname() + " - $" + pArray[i].passengers[j].getExpenses());
                        totalExpenses += pArray[i].passengers[j].getExpenses();
                    }
                }
            }
            Passenger forCount = new Passenger();
            //displays total expenses
            System.out.println("\nTotal Expenses For " + forCount.getPassengerCount() + " Passengers : $" + totalExpenses);
        }
        System.out.println("-----------------------------------------------------------------------");
    }
}
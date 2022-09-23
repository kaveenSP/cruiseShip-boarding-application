import java.util.Scanner;

public class CruiseShip_Task03 {
    /**This is the main method*/
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Instantiating cabin class
        Cabin cabinObject = new Cabin();
        //Instantiating passenger class
        Passenger pasObject = new Passenger();
        String choice; //option to be selected by the user
        boolean loopCheck = true; // used to exit from the while loop
        Cabin[] cabinsArray = new Cabin[12]; //Array of 12 cabin objects
        //Initialize Cabins
        cabinObject.initialize(cabinsArray);
        Passenger[] queueArray = new Passenger[3];
        //Initialize passenger objects for the queue
        for (int i = 0; i< queueArray.length; i++) {
            queueArray[i] = new Passenger();
        }
        while(loopCheck){
            System.out.println("\nPlease select one of the options Below\n" + "--------------------------------------");
            System.out.println("    V : View All Cabins");
            System.out.println("    A : Add Customers To Cabin");
            System.out.println("    E : Display Empty Cabins");
            System.out.println("    D : Delete Customer From Cabin");
            System.out.println("    F : Find Cabin From Customer Name");
            System.out.println("    S : Store Program Data Into a File");
            System.out.println("    L : Load program Data From File");
            System.out.println("    O : View Passengers Ordered Alphabetically By Name");
            System.out.println("    T : Print Expenses");
            System.out.println("    W : View Waiting List");
            System.out.println("    Q : Quit");
            System.out.println("\nEnter the option letter from the above list : (V/A/E/F/S/L/O)");
            choice = input.nextLine();
            System.out.println("-----------------------------------------------------------------------");
            switch (choice.toUpperCase()) {
                case "V" -> cabinObject.viewCabins(cabinsArray);
                case "A" -> {
                    //checks if all cabins have atleast a single passenger
                    if(cabinObject.checkCabinAvailability(cabinsArray, "Vacant") + cabinObject.checkCabinAvailability(cabinsArray,"Occupied") == cabinsArray.length) {
                        //If all cabins have atleast one passenger, passengers will be added to the queue
                        pasObject.enqueue(queueArray);
                    } else {
                        //If cabins are empty
                        cabinObject.addCustomer(cabinsArray);
                    }
                }
                case "E" -> cabinObject.displayEmptyCabins(cabinsArray);
                case "D" -> cabinObject.deleteCustomer(cabinsArray, queueArray);
                case "F" -> cabinObject.findCustomerByName(cabinsArray);
                case "S" -> cabinObject.writeToFile(cabinsArray);
                case "L" -> cabinObject.readFromFile();
                case "O" -> cabinObject.sortArrayData(cabinsArray);
                case "T" -> cabinObject.findExpenses(cabinsArray);
                case "W" -> pasObject.viewQueue(queueArray);
                case "Q" -> {
                    input.close();
                    loopCheck = false;
                }
                default -> System.out.println("\nInvalid Option Selected !");
            }
        }
    }
}
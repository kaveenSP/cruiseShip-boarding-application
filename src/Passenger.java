import java.util.InputMismatchException;
import java.util.Scanner;

public class Passenger {
    private String firstName,surname;
    private double expenses;
    private static int passengerCount;
    static int front = -1;
    static int rear = -1;
    private Scanner input = new Scanner(System.in);

    //default constructor
    public Passenger() {

    }

    /**Creates a passenger object while keeping the count of total passengers
     * @param fName first name of the passenger
     * @param sName surname of the passenger
     * @param nExpenses passenger expenses
     * overloaded constructor*/
    public Passenger(String fName, String sName, double nExpenses) {
        firstName = fName;
        surname = sName;
        expenses = nExpenses;
        passengerCount++; //Keeps count of total Passengers
    }

    /**returns passenger first name
     * @return first name*/
    public String getFirstName() {
        return firstName;
    }

    /**returns passenger surname
     * @return surname*/
    public String getSurname() {
        return surname;
    }

    /**returns passenger expenses
     * @return expenses*/
    public double getExpenses() {
        return expenses;
    }

    /**returns current passenger count
     * @return total current passenger count*/
    public int getPassengerCount() {
        return passengerCount;
    }

    /**reduces passenger count by 1
     */
    public void reducePassengerCount() {
        passengerCount--;
    }

    /**This method takes details of customers and store them in an object inside an array of Passenger objects when all the cabins are occupied
     * @param pArray array of passenger objects
     * */
    public void enqueue(Passenger[] pArray) {
        //Check whether the queue is full
        if(front == 0 && rear == (pArray.length - 1) || (rear + 1) == front) {
            System.out.println("All The Cabins And The Waiting Queue Are Currently Full. Please Try Again Later.");
        } else {
            System.out.println("All The Cabins Are Currently Occupied. Would You Like To Be Added To The Waiting List ? (Y/N)");
            String queueAnswer = input.nextLine();
            if (queueAnswer.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("\nEnter Passenger First Name : ");
                        String fName = input.nextLine();
                        //Checks if user entered an empty value
                        if(fName.equals("") || fName.equals(" ")) {
                            System.out.println("Name Field Cannot Be Empty !\n\nWould you like to try again ? (Y/N)");
                            String retry = input.nextLine();
                            if (retry.equalsIgnoreCase("Y")) {
                                break;
                            } else if (retry.equalsIgnoreCase("N")) {
                                System.out.println("You Have Not Been Added To The Queue.");
                                break;
                            } else {
                                System.out.println("\nInvalid Option Selected. You will now be redirected to the Menu.");
                                break;
                            }
                        }
                        System.out.print("\nEnter Passenger Surname : ");
                        String sName = input.nextLine();
                        //Checks if user entered an empty value
                        if(sName.equals("") || sName.equals(" ")) {
                            System.out.println("Name Field Cannot Be Empty !\n\nWould you like to try again ? (Y/N)");
                            String retry = input.nextLine();
                            if (retry.equalsIgnoreCase("Y")) {
                                break;
                            } else if (retry.equalsIgnoreCase("N")) {
                                System.out.println("You Have Not Been Added To The Queue.");
                                break;
                            } else {
                                System.out.println("\nInvalid Option Selected. You will now be redirected to the Menu.");
                                break;
                            }
                        }
                        System.out.print("\nExpenses For The Passenger : ");
                        double expense = input.nextDouble();
                        input.nextLine();
                        //Checks if entered expense value is greater than 0
                        if (expense > 0) {
                            if (front == -1) {
                                front = 0;
                            }
                            rear = (rear + 1) % pArray.length;
                            pArray[rear] = new Passenger(fName, sName, expense);
                            System.out.println("\nPassenger Has Been Added To The Queue Successfully.");
                            reducePassengerCount();
                            break;
                        } else {
                            System.out.println("Invalid Value Entered !\n\nWould you like to try again ?");
                            String retry = input.nextLine();
                            if (retry.equalsIgnoreCase("Y")) {
                                continue;
                            } else if (retry.equalsIgnoreCase("N")) {
                                System.out.println("You Have Not Been Added To The Queue.");
                                break;
                            } else {
                                System.out.println("\nInvalid Option Selected. You will now be redirected to the Menu.");
                                break;
                            }
                        }
                    } catch (InputMismatchException e) {
                        //If user enters a different data type than the expected type this block will execute
                        System.out.println("Invalid Value Detected !\n\nWould you like to try again ? (Y/N)");
                        input.nextLine();
                        String retry = input.nextLine();
                        if (retry.equalsIgnoreCase("Y")) {
                            continue;
                        } else if (retry.equalsIgnoreCase("N")) {
                            System.out.println("You Have Not Been Added To The Queue.");
                            break;
                        } else {
                            System.out.println("Invalid Option Selected. You will now be redirected to the Menu.");
                            break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //If user selects a cabin that is not defined, this code block executes
                        System.out.println("Invalid Cabin Number Entered.\n\nWould you like to try again ? (Y/N)");
                        String retry = input.nextLine();
                        if (retry.equalsIgnoreCase("Y")) {
                            continue;
                        } else if (retry.equalsIgnoreCase("N")) {
                            System.out.println("You Have Not Been Added To The Queue.");
                            break;
                        } else {
                            System.out.println("\nInvalid Option Selected. You will now be redirected to the Menu.");
                            break;
                        }
                    }
                }
            } else if (queueAnswer.equalsIgnoreCase("N")) {
                System.out.println("Sorry For The Inconvenience.");
            } else {
                System.out.println("\nInvalid Option Selected. You will now be redirected to the Menu.");
            }
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    /**This method is used to return the respective passenger object.
     * If all cabins are filled with a single passenger and if there are customers in the queue this method will return the passenger object of the first customer to get out of the queue.
     * If cabins are filled with more than a single passenger, this method will return a null value.
     * @param pArray array of passenger objects for the queue
     * @param pArrayCabin array of cabin objects
     * @param cabinIndex respective cabin index to make changes
     * @return sets the passenger object
     * */
    public Passenger dequeue(Passenger[] pArray, Cabin[] pArrayCabin, int cabinIndex) {
        Passenger passengerObject;
        Cabin cabinObject = new Cabin();
        if(front == -1 || cabinObject.checkPassengerCount(pArrayCabin,cabinIndex) > 1) {
            reducePassengerCount();
            return null;
        } else {
            passengerObject = pArray[front];
            if(front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % pArray.length;
            }
            System.out.println("\n" + passengerObject.getFirstName() + " " + passengerObject.getSurname() + " Has Been Removed From Queue And Added To Cabin " + (cabinIndex + 1));
            return passengerObject;
        }
    }

    /**View the queue.
     * @param pArray array of passenger objects for the queue
     * */
    public void viewQueue(Passenger[] pArray) {
        int count = 1; //keeps count of the passengers in the queue
        if(front == -1) {
            System.out.println("The Waiting List Is Empty.");
        } else {
            System.out.print("Next : ");
            for(int i = front; i != rear; i = ((i+1) % pArray.length)) {
                //prints passenger first name and surname
                System.out.println(pArray[i].getFirstName() + " " + pArray[i].getSurname());
                count++;
            }
            //prints passenger first name and surname
            System.out.println(pArray[rear].getFirstName() + " " + pArray[rear].getSurname());
            //Checks if the waiting list is full and display it.
            if(count == pArray.length) {
                System.out.println("\nThe Waiting List Is Full.");
            }
        }
        System.out.println("-----------------------------------------------------------------------");
    }
}

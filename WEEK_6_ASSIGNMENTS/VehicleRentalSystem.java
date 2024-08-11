package WEEK_6_ASSIGNMENTS;

import java.util.InputMismatchException;

import java.util.Scanner;


/**

 * 1. Design and implement an interface named "Vehicle" that includes methods for retrieving the

 *    vehicle's make, model, and year of manufacture.

 */

interface Vehicle {

    String getMake();

    String getModel();

    int getYear();

}



/**

 * 2. Develop a class named "Car" that implements the Vehicle and "CarVehicle" interfaces. This

 *    interface includes additional methods for setting and retrieving the number of doors and

 *    the fuel type (petrol, diesel, or electric).

 */

interface CarVehicle {

    int getNumDoors();

    void setNumDoors(int numDoors);

    String getFuelType();

    void setFuelType(String fuelType);

}



class Car implements Vehicle, CarVehicle {

    private String make, model;

    private int year, numDoors;

    private String fuelType;



    // Implement Vehicle interface methods

    public String getMake() { return make; }

    public String getModel() { return model; }

    public int getYear() { return year; }



    // Implement CarVehicle interface methods

    public int getNumDoors() { return numDoors; }

    public void setNumDoors(int numDoors) { this.numDoors = numDoors; }

    public String getFuelType() { return fuelType; }

    public void setFuelType(String fuelType) { this.fuelType = fuelType; }



    // Constructor

    public Car(String make, String model, int year, int numDoors, String fuelType) {

        this.make = make;

        this.model = model;

        this.year = year;

        this.numDoors = numDoors;

        this.fuelType = fuelType;

    }

}



/**

 * 3. Construct a class named "Motorcycle" that also implements Vehicle and the "MotorVehicle"

 *    interfaces. This interface should have methods for setting and retrieving the number of

 *    wheels and the type of motorcycle (sport, cruiser, or off-road).

 */

interface MotorVehicle {

    int getNumWheels();

    void setNumWheels(int numWheels);

    String getMotorcycleType();

    void setMotorcycleType(String motorcycleType);

}



class Motorcycle implements Vehicle, MotorVehicle {

    private String make, model;

    private int year, numWheels;

    private String motorcycleType;



    // Implement Vehicle interface methods

    public String getMake() { return make; }

    public String getModel() { return model; }

    public int getYear() { return year; }



    // Implement MotorVehicle interface methods

    public int getNumWheels() { return numWheels; }

    public void setNumWheels(int numWheels) { this.numWheels = numWheels; }

    public String getMotorcycleType() { return motorcycleType; }

    public void setMotorcycleType(String motorcycleType) { this.motorcycleType = motorcycleType; }



    // Constructor

    public Motorcycle(String make, String model, int year, int numWheels, String motorcycleType) {

        this.make = make;

        this.model = model;

        this.year = year;

        this.numWheels = numWheels;

        this.motorcycleType = motorcycleType;

    }

}



/**

 * 4. Generate a class named "Truck" that implements the Vehicle and "TruckVehicle" interfaces.

 *    This interface should include methods for setting and retrieving the cargo capacity (in

 *    tons) and the transmission type (manual or automatic).

 */

interface TruckVehicle {

    double getCargoCapacity();

    void setCargoCapacity(double cargoCapacity);

    String getTransmissionType();

    void setTransmissionType(String transmissionType);

}



class Truck implements Vehicle, TruckVehicle {

    private String make, model;

    private int year;

    private double cargoCapacity;

    private String transmissionType;



    // Implement Vehicle interface methods

    public String getMake() { return make; }

    public String getModel() { return model; }

    public int getYear() { return year; }



    // Implement TruckVehicle interface methods

    public double getCargoCapacity() { return cargoCapacity; }

    public void setCargoCapacity(double cargoCapacity) { this.cargoCapacity = cargoCapacity; }

    public String getTransmissionType() { return transmissionType; }

    public void setTransmissionType(String transmissionType) { this.transmissionType = transmissionType; }



    // Constructor

    public Truck(String make, String model, int year, double cargoCapacity, String transmissionType) {

        this.make = make;

        this.model = model;

        this.year = year;

        this.cargoCapacity = cargoCapacity;

        this.transmissionType = transmissionType;

    }

}



/**

 * 5. Integrate all the classes into a main program that allows the user to create objects of

 *    different vehicle types, provide relevant information, and display the details of each

 *    vehicle.

 */

public class VehicleRentalSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);



        System.out.println("Welcome to the Vehicle Rental System!");

        int numVehicles = 0;

        boolean validInput = false;



        while (!validInput) {

            System.out.print("Enter the number of vehicles to create: ");

            try {

                numVehicles = scanner.nextInt();

                validInput = true;

            } catch (InputMismatchException e) {

                System.out.println("Invalid input. Please enter an integer value.");

                scanner.nextLine(); // Consume the invalid input

            }

        }

        scanner.nextLine(); // Consume newline character



        Vehicle[] vehicles = new Vehicle[numVehicles];



        for (int i = 0; i < numVehicles; i++) {

            System.out.println("\nVehicle " + (i + 1));

            System.out.print("Enter vehicle type (car/motorcycle/truck): ");

            String vehicleType = scanner.nextLine().toLowerCase();



            switch (vehicleType) {

                case "car":

                    vehicles[i] = createCar(scanner);

                    break;

                case "motorcycle":

                    vehicles[i] = createMotorcycle(scanner);

                    break;

                case "truck":

                    vehicles[i] = createTruck(scanner);

                    break;

                default:

                    System.out.println("Invalid vehicle type. Please enter car, motorcycle, or truck.");

                    i--; // Decrement i to retry this iteration

                    break;

            }

        }



        // Display the details of each vehicle

        System.out.println("\nVehicle Details:");

        for (Vehicle vehicle : vehicles) {

            if (vehicle instanceof Car) {

                Car car = (Car) vehicle;

                System.out.println("Car - Make: " + car.getMake() + ", Model: " + car.getModel() + ", Year: " + car.getYear() +

                        ", Number of Doors: " + car.getNumDoors() + ", Fuel Type: " + car.getFuelType());

            } else if (vehicle instanceof Motorcycle) {

                Motorcycle motorcycle = (Motorcycle) vehicle;

                System.out.println("Motorcycle - Make: " + motorcycle.getMake() + ", Model: " + motorcycle.getModel() +

                        ", Year: " + motorcycle.getYear() + ", Number of Wheels: " + motorcycle.getNumWheels() +

                        ", Motorcycle Type: " + motorcycle.getMotorcycleType());

            } else if (vehicle instanceof Truck) {

                Truck truck = (Truck) vehicle;

                System.out.println("Truck - Make: " + truck.getMake() + ", Model: " + truck.getModel() +

                        ", Year: " + truck.getYear() + ", Cargo Capacity: " + truck.getCargoCapacity() +

                        " tons, Transmission Type: " + truck.getTransmissionType());

            }

        }

    }



    private static Car createCar(Scanner scanner) {

        String make, model, fuelType;

        int year, numDoors;



        try {

            System.out.print("Enter make: ");

            make = scanner.nextLine();

            System.out.print("Enter model: ");

            model = scanner.nextLine();

            System.out.print("Enter year: ");

            year = scanner.nextInt();

            System.out.print("Enter number of doors: ");

            numDoors = scanner.nextInt();

            scanner.nextLine(); // Consume newline character

            System.out.print("Enter fuel type (petrol/diesel/electric): ");

            fuelType = scanner.nextLine();

        } catch (InputMismatchException e) {

            System.out.println("Invalid input. Please try again.");

            scanner.nextLine(); // Consume the invalid input

            return null;

        }



        return new Car(make, model, year, numDoors, fuelType);

    }



    private static Motorcycle createMotorcycle(Scanner scanner) {

        String make, model, motorcycleType;

        int year, numWheels;



        try {

            System.out.print("Enter make: ");

            make = scanner.nextLine();

            System.out.print("Enter model: ");

            model = scanner.nextLine();

            System.out.print("Enter year: ");

            year = scanner.nextInt();

            System.out.print("Enter number of wheels: ");

            numWheels = scanner.nextInt();

            scanner.nextLine(); // Consume newline character

            System.out.print("Enter motorcycle type (sport/cruiser/off-road): ");

            motorcycleType = scanner.nextLine();

        } catch (InputMismatchException e) {

            System.out.println("Invalid input. Please try again.");

            scanner.nextLine(); // Consume the invalid input

            return null;

        }



        return new Motorcycle(make, model, year, numWheels, motorcycleType);

    }



    private static Truck createTruck(Scanner scanner) {

        String make, model, transmissionType;

        int year;

        double cargoCapacity;



        try {

            System.out.print("Enter make: ");

            make = scanner.nextLine();

            System.out.print("Enter model: ");

            model = scanner.nextLine();

            System.out.print("Enter year: ");

            year = scanner.nextInt();

            System.out.print("Enter cargo capacity (in tons): ");

            cargoCapacity = scanner.nextDouble();

            scanner.nextLine(); // Consume newline character

            System.out.print("Enter transmission type (manual/automatic): ");

            transmissionType = scanner.nextLine();

        } catch (InputMismatchException e) {

            System.out.println("Invalid input. Please try again.");

            scanner.nextLine(); // Consume the invalid input

            return null;

        }



        return new Truck(make, model, year, cargoCapacity, transmissionType);

    }

}

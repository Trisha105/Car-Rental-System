import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) { cars.add(car); }
    public void addCustomer(Customer customer) { customers.add(customer); }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental remove = null;
        for (Rental r : rentals) {
            if (r.getCar() == car) {
                remove = r;
                break;
            }
        }
        if (remove != null) {
            rentals.remove(remove);
        } else {
            System.out.println("Car was not rented.");
        }
    }

    private Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    private int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("===== Car Rental System =====");
                System.out.println("1. Rent a Car");
                System.out.println("2. Return a Car");
                System.out.println("3. Exit");

                int choice = getIntInput(scanner, "Enter your choice: ");

                if (choice == 1) {
                    System.out.println("\n== Rent a Car ==\n");
                    System.out.print("Enter your name: ");
                    String customerName = scanner.nextLine().trim();

                    if (customerName.isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        continue;
                    }

                    System.out.println("\nAvailable Cars:");
                    boolean hasAvailableCars = false;
                    for (Car car : cars) {
                        if (car.isAvailable()) {
                            System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                            hasAvailableCars = true;
                        }
                    }

                    if (!hasAvailableCars) {
                        System.out.println("No cars available for rent.");
                        continue;
                    }

                    System.out.print("\nEnter the car ID you want to rent: ");
                    String carId = scanner.nextLine().trim();

                    int rentalDays = getIntInput(scanner, "Enter the number of days for rental: ");

                    Customer customer = findCustomerByName(customerName);
                    if (customer == null) {
                        customer = new Customer("CUS" + (customers.size() + 1), customerName);
                        addCustomer(customer);
                    }

                    Car selectedCar = null;
                    for (Car car : cars) {
                        if (car.getCarId().equalsIgnoreCase(carId) && car.isAvailable()) {
                            selectedCar = car;
                            break;
                        }
                    }

                    if (selectedCar != null && rentalDays > 0) {
                        double totalPrice = selectedCar.calculatePrice(rentalDays);
                        System.out.println("\n== Rental Information ==\n");
                        System.out.println("Customer ID: " + customer.getCustomerId());
                        System.out.println("Customer Name: " + customer.getName());
                        System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                        System.out.println("Rental Days: " + rentalDays);
                        System.out.printf("Total Price: $%.2f%n", totalPrice);

                        System.out.print("\nConfirm rental (Y/N): ");
                        String confirm = scanner.nextLine().trim();

                        if (confirm.equalsIgnoreCase("Y")) {
                            rentCar(selectedCar, customer, rentalDays);
                            System.out.println("\nCar rented successfully.");
                        } else {
                            System.out.println("\nRental canceled.");
                        }
                    } else {
                        System.out.println("\nInvalid car selection or rental days must be greater than 0.");
                    }

                } else if (choice == 2) {
                    System.out.println("\n== Return a Car ==\n");
                    System.out.print("Enter the car ID you want to return: ");
                    String carId = scanner.nextLine().trim();

                    Car carToReturn = null;
                    for (Car car : cars) {
                        if (car.getCarId().equalsIgnoreCase(carId) && !car.isAvailable()) {
                            carToReturn = car;
                            break;
                        }
                    }

                    if (carToReturn != null) {
                        Customer customer = null;
                        for (Rental r : rentals) {
                            if (r.getCar() == carToReturn) {
                                customer = r.getCustomer();
                                break;
                            }
                        }

                        if (customer != null) {
                            returnCar(carToReturn);
                            System.out.println("Car returned successfully by " + customer.getName());
                        } else {
                            System.out.println("Car was not rented or rental information is missing.");
                        }
                    } else {
                        System.out.println("Invalid car ID or car is not rented.");
                    }

                } else if (choice == 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } finally {
            scanner.close();
        }

        System.out.println("\nThank you for using the Car Rental System!");
    }
}
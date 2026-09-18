public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("T001", "Toyota", "Allion", 60.0);
        Car car2 = new Car("H001", "Honda", "Civic", 150.0);
        Car car3 = new Car("T002", "Toyota", "Corolla", 50.0);
        Car car4 = new Car("T003", "Toyota", "Noah", 350.0);
        Car car5 = new Car("H002", "Honda", "CR-V", 150.0);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);

        rentalSystem.menu();
    }
}
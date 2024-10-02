// Product Interface (Vehicle)
public interface Vehicle {
    void create();
}

// Concrete Product (Car)
public class Car implements Vehicle {
    @Override
    public void create() {
        System.out.println("Car has been manufactured.");
    }
}

// Concrete Product (Truck)
public class Truck implements Vehicle {
    @Override
    public void create() {
        System.out.println("Truck has been manufactured.");
    }
}

// Factory (VehicleFactory)
public class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        if (type.equalsIgnoreCase("car")) {
            return new Car();
        } else if (type.equalsIgnoreCase("truck")) {
            return new Truck();
        }
        throw new IllegalArgumentException("Unknown vehicle type.");
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.createVehicle("car");
        car.create();

        Vehicle truck = VehicleFactory.createVehicle("truck");
        truck.create();
    }
}

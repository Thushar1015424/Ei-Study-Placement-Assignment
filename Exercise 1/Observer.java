// Subject (WeatherStation)
public interface WeatherStation {
    void addObserver(Device device);
    void removeObserver(Device device);
    void notifyObservers();
}

// Concrete Subject
public class WeatherStationImpl implements WeatherStation {
    private List<Device> devices = new ArrayList<>();
    private String weather;

    @Override
    public void addObserver(Device device) {
        devices.add(device);
    }

    @Override
    public void removeObserver(Device device) {
        devices.remove(device);
    }

    @Override
    public void notifyObservers() {
        for (Device device : devices) {
            device.update(weather);
        }
    }

    public void setWeather(String weather) {
        this.weather = weather;
        notifyObservers();
    }
}

// Observer (Device)
public interface Device {
    void update(String weather);
}

// Concrete Observer (Phone)
public class Phone implements Device {
    @Override
    public void update(String weather) {
        System.out.println("Phone received weather update: " + weather);
    }
}

// Concrete Observer (Smartwatch)
public class Smartwatch implements Device {
    @Override
    public void update(String weather) {
        System.out.println("Smartwatch received weather update: " + weather);
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        WeatherStationImpl weatherStation = new WeatherStationImpl();

        Device phone = new Phone();
        Device smartwatch = new Smartwatch();

        weatherStation.addObserver(phone);
        weatherStation.addObserver(smartwatch);

        weatherStation.setWeather("Sunny");
    }
}

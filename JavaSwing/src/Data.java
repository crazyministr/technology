/**
 * Class stores the data
 */
public class Data {
    private String FuelType;
    private double countFuel;
    private String brand;

    public Data(String FuelType, double countFuel, String brand) {
        this.FuelType = FuelType;
        this.countFuel = countFuel;
        this.brand = brand;
    }

    /**
     * @return fuel type
     */
    public String getTypeFuel() {
        return FuelType;
    }

    /**
     * @return the amount of fuel on 100 km
     */
    public double getCountFuel() {
        return countFuel;
    }

    /**
     * @return brand auto
     */
    public String getBrand() {
        return brand;
    }
}

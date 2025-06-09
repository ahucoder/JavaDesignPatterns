package creational.builder;

public class App {
    public static void main(String[] args) {
        House seaviewHouse = new House.Builder("Concrete", "Brick", "A")
                .hasSwimmingPool(true)
                .hasGarage(true)
                .hasGarden(true)
                .build();
        System.out.println(seaviewHouse);
    }
}

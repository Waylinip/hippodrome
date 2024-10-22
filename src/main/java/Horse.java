import java.util.Objects;

import static java.util.Objects.isNull;

public class Horse {

    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

   public void move() {
         distance += speed * getRandomDouble(0.2, 0.9);
    }


    public static double getRandomDouble(double min, double max) {

        return (Math.random() * (max - min)) + min;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return Double.compare(horse.speed, speed) == 0 &&
                Double.compare(horse.distance, distance) == 0 &&
                name.equals(horse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, speed, distance);
    }
}

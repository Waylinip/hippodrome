import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

public class Horse_Test {
    @Test
    void nullNameEx(){
       IllegalArgumentException e =  assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,2));

        assertEquals("Name cannot be null.", e.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t\t", "\n\n\n\n"})
    void NameEx(String name){
        IllegalArgumentException e =  assertThrows(IllegalArgumentException.class, () -> new Horse(name,1,2));

        assertEquals("Name cannot be blank.", e.getMessage());
    }
    @Test
    void negativeSpeedEx(){
        IllegalArgumentException e =  assertThrows(IllegalArgumentException.class, () -> new Horse("Jorg",-1,2));

        assertEquals("Speed cannot be negative.", e.getMessage());
    }
    @Test
    void negativeDistanceEx(){
        IllegalArgumentException e =  assertThrows(IllegalArgumentException.class, () -> new Horse("Jorg",1,-2));

        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    void getName() {
        Horse horse = new Horse("Jorg",1,2);
        assertEquals("Jorg", horse.getName());
    }

    @Test
    void getSpeed() {
        double expectedSpeed = 48;
        Horse horse = new Horse("Jorg", expectedSpeed,2);
        assertEquals( expectedSpeed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        double expectedDistance = 53;
        Horse horse = new Horse("Jorg", 1,expectedDistance);
        assertEquals( expectedDistance, horse.getDistance());
    }
    @Test
    void zeroDistanceDef() {
        double expectedDistance = 0;
        Horse horse = new Horse("Jorg", 1);
        assertEquals( expectedDistance, horse.getDistance());
    }
    @Test
    void moveRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("John",31,283).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

       @ParameterizedTest
       @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.07})
        void move(double random){
            try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
                Horse horse = new Horse("John", 31, 283);
                mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
                horse.move();
                assertEquals(283 + 31 * random, horse.getDistance());
            }
        }
}

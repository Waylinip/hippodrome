import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

public class Hippodrome_Test {
    @Test
    void emptyHorseEx() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    void nullHorseEx() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horse" + i, i, i ));
        }
          Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }
    @Test
    void moveTest() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = mock(Horse.class);
            horses.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

         for (Horse horse : horses) {
            verify(horse).move();
        }
    }
    @Test
    void getWinnerTest() {
        Horse horse1 = new Horse("Horse1", 10, 50);
        Horse horse2 = new Horse("Horse2", 10, 100); // эта лошадь с наибольшим расстоянием
        Horse horse3 = new Horse("Horse3", 10, 75);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));

        assertSame(horse2, hippodrome.getWinner());
    }
}
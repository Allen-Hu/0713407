import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    PriorityQueue<Integer> priorityQueue;
    @BeforeEach
    void setUp() {
        priorityQueue = new PriorityQueue<>();
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void testArguments(Integer[] input, Integer[] expected) {
        for(int i = 0; i < input.length; i++)
            priorityQueue.add(input[i]);
        var result = new Integer[input.length];
        for(int i = 0; i < input.length; i++)
            result[i] = priorityQueue.poll();
        assertArrayEquals(expected, result);
    }

    @Test
    void testException_addNull() {
        assertThrows(NullPointerException.class, () -> {
           priorityQueue.add(null);
        });
    }

    @Test
    void testException_negativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue priorityQueue1 = new PriorityQueue(-1);
        });
    }

    @Test
    void testException_toNullArray() {
        assertThrows(NullPointerException.class, () -> {
            Integer array[] = null;
            priorityQueue.toArray(array);
        });
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(new Integer[]{4, 2, 3, 1}, new Integer[]{1, 2, 3, 4}),
                Arguments.of(new Integer[]{3, 3, 3, 1}, new Integer[]{1, 3, 3, 3}),
                Arguments.of(new Integer[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, new Integer[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}),
                Arguments.of(new Integer[]{-1, -1, -1}, new Integer[]{-1, -1, -1}),
                Arguments.of(new Integer[]{3, 8, 2, 4, 2}, new Integer[]{2, 2, 3, 4, 8})
        );
    }
}

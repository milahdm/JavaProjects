import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class StudentTest {

    @Test
    public void testWire0() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/wire0.in");
        });
    }

    // your tests go here

    @Test
    public void testSimplePath() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/simple.in");
        });
    }

    // Obstacle navigation test
    @Test
    public void testObstaclePath() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/obstacles.in");
        });
    }

    // Multiple paths test
    @Test
    public void testMultiplePaths() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/multi.in");
        });
    }

    // No solution possible test
    @Test
    public void testNoSolution() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            Utilities.test("./test/inputs/unsolvable.in");
        });
    }

    // Complex scenario test
    @Test
    public void testComplexScenario() {
        assertTimeout(Duration.ofMillis(2000), () -> {
            Utilities.test("./test/inputs/complicated.in");
        });
    }

}

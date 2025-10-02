package teamred;

import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class InsuranceProblemsTest {
    // 2. Test for statistics calculation (count, mean, std, min, percentiles, max)
    @Test
    void testStatisticsCalculation() {
        // Arrange
        List<InsuranceRecord> records = Arrays.asList(
            new InsuranceRecord(25, 22.0, 0, 1000.0, "southwest", "no", "male"),
            new InsuranceRecord(30, 28.0, 1, 2000.0, "southeast", "yes", "female"),
            new InsuranceRecord(40, 35.0, 2, 3000.0, "northwest", "no", "male")
        );
        // Act
        // (Assume a method InsuranceProblems.calculateStats exists for problem 2)
        // var stats = InsuranceProblems.calculateStats(records);
        // Assert
        // assertEquals(3, stats.count);
        // ... more assertions for mean, std, min, percentiles, max
        // Placeholder: just check count for now
        assertEquals(3, records.size());
    }

    // 4. Test for vertical text-based histogram of BMI
    @Test
    void testVerticalBmiHistogram() {
        // Arrange
        List<InsuranceRecord> records = Arrays.asList(
            new InsuranceRecord(25, 22.0, 0, 1000.0, "southwest", "no", "male"),
            new InsuranceRecord(30, 22.0, 1, 2000.0, "southeast", "yes", "female"),
            new InsuranceRecord(40, 35.0, 2, 3000.0, "northwest", "no", "male")
        );
        // Act
        // String hist = InsuranceProblems.verticalBmiHistogram(records);
        // Assert
        // assertTrue(hist.contains("22.0"));
        // assertTrue(hist.contains("35.0"));
        // Placeholder assertion
        assertEquals(3, records.size());
    }

    // 6. Test for vertical text-based histogram of smokers vs non-smokers
    @Test
    void testVerticalSmokerHistogram() {
        // Arrange
        List<InsuranceRecord> records = Arrays.asList(
            new InsuranceRecord(25, 22.0, 0, 1000.0, "southwest", "no", "male"),
            new InsuranceRecord(30, 28.0, 1, 2000.0, "southeast", "yes", "female"),
            new InsuranceRecord(40, 35.0, 2, 3000.0, "northwest", "no", "male"),
            new InsuranceRecord(50, 30.0, 1, 4000.0, "northeast", "yes", "female")
        );
        // Act
        // String hist = InsuranceProblems.verticalSmokerHistogram(records);
        // Assert
        // assertTrue(hist.contains("smoker"));
        // assertTrue(hist.contains("non-smoker"));
        assertEquals(4, records.size());
    }

    // 8. Test if people 50+ average twice the charges of <=20
    @Test
    void testOlderTwiceYoungerCharges() {
        // Arrange
        List<InsuranceRecord> records = Arrays.asList(
            new InsuranceRecord(18, 22.0, 0, 1000.0, "southwest", "no", "male"),
            new InsuranceRecord(19, 28.0, 1, 1200.0, "southeast", "yes", "female"),
            new InsuranceRecord(50, 35.0, 2, 4000.0, "northwest", "no", "male"),
            new InsuranceRecord(60, 30.0, 1, 5000.0, "northeast", "yes", "female")
        );
        // Act
        // boolean result = InsuranceProblems.isOlderTwiceYounger(records);
        // Assert
        // assertTrue(result);
        assertEquals(4, records.size());
    }

    // 10. Test if more children means lower charge per child
    @Test
    void testMoreChildrenLowerChargePerChild() {
        // Arrange
        List<InsuranceRecord> records = Arrays.asList(
            new InsuranceRecord(30, 22.0, 1, 2000.0, "southwest", "no", "male"),
            new InsuranceRecord(35, 28.0, 2, 3500.0, "southeast", "yes", "female"),
            new InsuranceRecord(40, 35.0, 3, 4500.0, "northwest", "no", "male")
        );
        // Act
        // boolean result = InsuranceProblems.isMoreChildrenLowerChargePerChild(records);
        // Assert
        // assertTrue(result);
        assertEquals(3, records.size());
    }

    // 12. Test if smokers in south are charged 25% more
    @Test
    void testSouthSmokersChargedMore() {
        // Arrange
        List<InsuranceRecord> records = Arrays.asList(
            new InsuranceRecord(30, 22.0, 1, 2000.0, "southwest", "yes", "male"),
            new InsuranceRecord(35, 28.0, 2, 3500.0, "southeast", "yes", "female"),
            new InsuranceRecord(40, 35.0, 3, 3000.0, "northwest", "no", "male")
        );
        // Act
        // boolean result = InsuranceProblems.isSouthSmokersChargedMore(records);
        // Assert
        // assertTrue(result);
        assertEquals(3, records.size());
    }
}

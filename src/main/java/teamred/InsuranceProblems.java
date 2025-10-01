package teamred;

import java.util.*;

public class InsuranceProblems {
    // 1. Store the first N records of the dataset in some custom object
    public static List<InsuranceRecord> getFirstNRecords(List<InsuranceRecord> records, int n) {
        return new ArrayList<>(records.subList(0, Math.min(n, records.size())));
    }

    // 3. Display a horizontal text-based histogram of the ages (no plotting libraries)
    public static String horizontalAgeHistogram(List<InsuranceRecord> records) {
        Map<Integer, Integer> ageCounts = new TreeMap<>();
        for (InsuranceRecord r : records) {
            ageCounts.put(r.age, ageCounts.getOrDefault(r.age, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : ageCounts.entrySet()) {
            sb.append(entry.getKey()).append(": ");
            for (int i = 0; i < entry.getValue(); i++) sb.append("*");
            sb.append("\n");
        }
        return sb.toString();
    }

    // 5. Determine the total number of records for each number of children
    public static Map<Integer, Integer> countByChildren(List<InsuranceRecord> records) {
        Map<Integer, Integer> childrenCounts = new TreeMap<>();
        for (InsuranceRecord r : records) {
            childrenCounts.put(r.children, childrenCounts.getOrDefault(r.children, 0) + 1);
        }
        return childrenCounts;
    }

    // 7. Check if data is "fair" by region (no more than 5% difference)
    public static boolean isRegionFair(List<InsuranceRecord> records) {
        Map<String, Integer> regionCounts = new HashMap<>();
        for (InsuranceRecord r : records) {
            regionCounts.put(r.region, regionCounts.getOrDefault(r.region, 0) + 1);
        }
        int total = records.size();
        int fairThreshold = (int)Math.ceil(total * 0.05);
        int min = Collections.min(regionCounts.values());
        int max = Collections.max(regionCounts.values());
        return (max - min) <= fairThreshold;
    }

    // 9. Check if charges for bmi in 30-45 are greater than other ranges
    public static boolean isBmiRangeChargesHigher(List<InsuranceRecord> records) {
        double sum3045 = 0, sumOther = 0;
        int count3045 = 0, countOther = 0;
        for (InsuranceRecord r : records) {
            if (r.bmi >= 30 && r.bmi <= 45) {
                sum3045 += r.charges;
                count3045++;
            } else {
                sumOther += r.charges;
                countOther++;
            }
        }
        double avg3045 = count3045 == 0 ? 0 : sum3045 / count3045;
        double avgOther = countOther == 0 ? 0 : sumOther / countOther;
        return avg3045 > avgOther;
    }

    // 11. Do smokers have higher charges and wider range?
    public static boolean doSmokersHaveHigherCharges(List<InsuranceRecord> records) {
        double sumSmoker = 0, sumNon = 0;
        int countSmoker = 0, countNon = 0;
        double minSmoker = Double.MAX_VALUE, maxSmoker = Double.MIN_VALUE;
        double minNon = Double.MAX_VALUE, maxNon = Double.MIN_VALUE;
        for (InsuranceRecord r : records) {
            if (r.smoker.equalsIgnoreCase("yes")) {
                sumSmoker += r.charges;
                countSmoker++;
                minSmoker = Math.min(minSmoker, r.charges);
                maxSmoker = Math.max(maxSmoker, r.charges);
            } else {
                sumNon += r.charges;
                countNon++;
                minNon = Math.min(minNon, r.charges);
                maxNon = Math.max(maxNon, r.charges);
            }
        }
        double avgSmoker = countSmoker == 0 ? 0 : sumSmoker / countSmoker;
        double avgNon = countNon == 0 ? 0 : sumNon / countNon;
        double rangeSmoker = maxSmoker - minSmoker;
        double rangeNon = maxNon - minNon;
        return avgSmoker > avgNon && rangeSmoker > rangeNon;
    }

    // 13. Do smokers average lower bmi than non-smokers?
    public static boolean doSmokersAverageLowerBmi(List<InsuranceRecord> records) {
        double sumSmoker = 0, sumNon = 0;
        int countSmoker = 0, countNon = 0;
        for (InsuranceRecord r : records) {
            if (r.smoker.equalsIgnoreCase("yes")) {
                sumSmoker += r.bmi;
                countSmoker++;
            } else {
                sumNon += r.bmi;
                countNon++;
            }
        }
        double avgSmoker = countSmoker == 0 ? 0 : sumSmoker / countSmoker;
        double avgNon = countNon == 0 ? 0 : sumNon / countNon;
        return avgSmoker < avgNon;
    }

    // 15. Sort 4 regions by average charges descending
    public static List<String> regionsByAvgCharges(List<InsuranceRecord> records) {
        Map<String, List<Double>> regionCharges = new HashMap<>();
        for (InsuranceRecord r : records) {
            regionCharges.computeIfAbsent(r.region, k -> new ArrayList<>()).add(r.charges);
        }
        List<Map.Entry<String, Double>> regionAvg = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : regionCharges.entrySet()) {
            double sum = 0;
            for (double c : entry.getValue()) sum += c;
            double avg = entry.getValue().isEmpty() ? 0 : sum / entry.getValue().size();
            regionAvg.add(new AbstractMap.SimpleEntry<>(entry.getKey(), avg));
        }
        regionAvg.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : regionAvg) {
            result.add(entry.getKey());
        }
        return result;
    }

    // 17. At what average age do southerners smoke more than northerners?
    public static double averageAgeSouthernersSmokeMore(List<InsuranceRecord> records) {
        int sumSouth = 0, countSouth = 0, sumNorth = 0, countNorth = 0;
        for (InsuranceRecord r : records) {
            if (r.smoker.equalsIgnoreCase("yes")) {
                if (r.region.toLowerCase().contains("south")) {
                    sumSouth += r.age;
                    countSouth++;
                } else if (r.region.toLowerCase().contains("north")) {
                    sumNorth += r.age;
                    countNorth++;
                }
            }
        }
        double avgSouth = countSouth == 0 ? 0 : (double)sumSouth / countSouth;
        double avgNorth = countNorth == 0 ? 0 : (double)sumNorth / countNorth;
        return avgSouth > avgNorth ? avgSouth : avgNorth;
    }

    // 19. At what average age do southerners average more children than northerners?
    public static double averageAgeSouthernersMoreChildren(List<InsuranceRecord> records) {
        int sumAgeSouth = 0, countSouth = 0, sumAgeNorth = 0, countNorth = 0, childrenSouth = 0, childrenNorth = 0;
        for (InsuranceRecord r : records) {
            if (r.region.toLowerCase().contains("south")) {
                childrenSouth += r.children;
                sumAgeSouth += r.age;
                countSouth++;
            } else if (r.region.toLowerCase().contains("north")) {
                childrenNorth += r.children;
                sumAgeNorth += r.age;
                countNorth++;
            }
        }
        double avgChildrenSouth = countSouth == 0 ? 0 : (double)childrenSouth / countSouth;
        double avgChildrenNorth = countNorth == 0 ? 0 : (double)childrenNorth / countNorth;
        if (avgChildrenSouth > avgChildrenNorth) {
            return countSouth == 0 ? 0 : (double)sumAgeSouth / countSouth;
        } else {
            return countNorth == 0 ? 0 : (double)sumAgeNorth / countNorth;
        }
    }

    // 21. Calculate the simple linear regression of the charges versus the number of children (Pearson r)
    public static double pearsonCorrelationChargesChildren(List<InsuranceRecord> records) {
        int n = records.size();
        if (n == 0) return 0;
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0, sumY2 = 0;
        for (InsuranceRecord r : records) {
            double x = r.children;
            double y = r.charges;
            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumX2 += x * x;
            sumY2 += y * y;
        }
        double numerator = n * sumXY - sumX * sumY;
        double denominator = Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY));
        return denominator == 0 ? 0 : numerator / denominator;
    }
}

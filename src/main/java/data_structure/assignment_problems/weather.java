package data_structure.assignment_problems;

public class weather {

    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || k <= 0 || k > readings.length) {
            return 0;
        }

        int windowSum = 0;
        int alerts = 0;

        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }

        if (windowSum >= k * threshold) {
            alerts++;
        }

        for (int i = k; i < readings.length; i++) {
            windowSum += readings[i];
            windowSum -= readings[i - k];

            if (windowSum >= k * threshold) {
                alerts++;
            }
        }

        return alerts;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        System.out.println(countAlerts(readings, k, threshold));
    }
}

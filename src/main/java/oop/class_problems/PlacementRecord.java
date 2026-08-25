package oop.class_problems;

public class PlacementRecord {

    String studentName;
    String company;
    double packageLpa;

    public PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }

    public static void main(String[] args) {

        PlacementRecord record1 =
                new PlacementRecord("Ravi", "TCS", 4.5);

        PlacementRecord record2 =
                new PlacementRecord("Anitha", "Zoho", 6.2);

        PlacementRecord record3 =
                new PlacementRecord("Karthik", "Infosys", 4.0);

        PlacementRecord[] records = {
            record1,
            record2,
            record3
        };

        for (PlacementRecord record : records) {
            record.printRecord();
        }
    }
}

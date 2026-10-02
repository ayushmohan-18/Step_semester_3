package main.java.object_class_method.assignment_problems;

interface MembershipPlan {

    String getPlanName();

    int getDuration();

    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {

    public String getPlanName() {
        return "Monthly";
    }

    public int getDuration() {
        return 1;
    }

    public double calculateFee() {
        return 1000;
    }
}

class QuarterlyPlan implements MembershipPlan {

    public String getPlanName() {
        return "Quarterly";
    }

    public int getDuration() {
        return 3;
    }

    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }
}

class AnnualPlan implements MembershipPlan {

    public String getPlanName() {
        return "Annual";
    }

    public int getDuration() {
        return 12;
    }

    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }
}

class Member {

    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

class Membership {

    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = MembershipStatus.ACTIVE;
    }

    public void checkIn() {

        if (status == MembershipStatus.ACTIVE) {
            System.out.println(member.getName()
                    + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: "
                    + member.getName()
                    + "'s membership is "
                    + status + ".");
        }
    }

    public void freeze() {

        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return;
        }

        if (status == MembershipStatus.FROZEN) {
            System.out.println("Membership is already Frozen.");
            return;
        }

        status = MembershipStatus.FROZEN;

        System.out.println(member.getName()
                + "'s membership frozen. Status: Frozen.");
    }

    public void unfreeze() {

        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return;
        }

        if (status != MembershipStatus.FROZEN) {
            System.out.println("Membership is not Frozen.");
            return;
        }

        status = MembershipStatus.ACTIVE;

        System.out.println(member.getName()
                + "'s membership unfrozen. Status: Active.");
    }

    public void expire() {

        status = MembershipStatus.EXPIRED;

        System.out.println(member.getName()
                + "'s membership expired. Status: Expired.");
    }

    public MembershipStatus getStatus() {
        return status;
    }
}

public class fitZone {

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        MembershipPlan quarterly = new QuarterlyPlan();
        MembershipPlan monthly = new MonthlyPlan();

        Membership m1 = new Membership(asha, quarterly);
        Membership m2 = new Membership(ravi, monthly);

        System.out.printf(
                "Quarterly membership created for Asha. Fee: ₹%.2f. Status: Active.%n",
                quarterly.calculateFee()
        );

        System.out.printf(
                "Monthly membership created for Ravi. Fee: ₹%.2f. Status: Active.%n",
                monthly.calculateFee()
        );

        m1.checkIn();

        m1.freeze();

        m1.checkIn();

        m2.expire();

        m2.freeze();
    }
}
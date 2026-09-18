package accessModifiers.class_problems;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Problem4 {

    public static void main(String[] args) {

        LibraryMemberBean member = new LibraryMemberBean();

        member.setMembershipId("LIB-8841");
        member.setName("Priya Nair");
        member.setPremiumMember(true);

        System.out.println(
                "Membership ID: " +
                member.getMembershipId()
        );

        System.out.println(
                "Name: " +
                member.getName()
        );

        System.out.println(
                "Premium: " +
                member.isPremiumMember()
        );


        // Second attempt is ignored
        member.setMembershipId("FAKE-0000");

        System.out.println(
                "Membership ID after second set: " +
                member.getMembershipId()
        );


        // Security answer is write-only
        member.setSecurityAnswer("BlueMountain");

        System.out.println("Security answer stored securely.");
    }
}


class LibraryMemberBean {

    private String membershipId;
    private String name;
    private boolean premiumMember;

    private String securityAnswerHash;

    private boolean membershipIdSet;


    // Public no-argument constructor
    public LibraryMemberBean() {

    }


    public String getMembershipId() {
        return membershipId;
    }


    public void setMembershipId(String id) {

        if (!membershipIdSet) {

            membershipId = id;
            membershipIdSet = true;
        }
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {

        this.name = name;
    }


    public boolean isPremiumMember() {
        return premiumMember;
    }


    public void setPremiumMember(boolean premium) {

        this.premiumMember = premium;
    }


    public void setSecurityAnswer(String answer) {

        securityAnswerHash = hash(answer);
    }


    private String hash(String value) {

        try {

            MessageDigest md =
                    MessageDigest.getInstance("SHA-256");

            byte[] bytes =
                    md.digest(
                            value.getBytes(
                                    StandardCharsets.UTF_8
                            )
                    );

            StringBuilder result =
                    new StringBuilder();

            for (byte b : bytes) {

                result.append(
                        String.format("%02x", b)
                );
            }

            return result.toString();

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e);
        }
    }
}
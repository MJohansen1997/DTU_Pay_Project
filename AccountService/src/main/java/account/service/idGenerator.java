package account.service;

import java.util.UUID;

public class idGenerator {
    public static String generateID(String role) {
        switch (role) {
            case "m":
                return ("m" + UUID.fromString("merchant").toString());
            case "c":
                return ("c" + UUID.fromString("customer").toString());
            default:
                return "cant generate id without a role!";
        }
    }
}

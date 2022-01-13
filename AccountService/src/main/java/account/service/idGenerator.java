package account.service;

import java.util.UUID;

public class idGenerator {
    public static String generateID(String role) {
        String id;
        switch (role) {
            case "m":
                id = UUID.randomUUID().toString();
                System.out.println(id);
                return ("m" + id);
            case "c":
                id = UUID.randomUUID().toString();
                System.out.println(id);
                return ("c" + id);
            default:
                return "cant generate id without a role!";
        }
    }
}

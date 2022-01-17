package account.service;

import java.util.UUID;

public class idGenerator {
    public static String generateID(String role) {
        String id;
        switch (role) {
            case "Merchant":
                id = UUID.randomUUID().toString();
                System.out.println(id);
                return ("m" + id);
            case "Customer":
                id = UUID.randomUUID().toString();
                System.out.println(id);
                return ("c" + id);
            default:
                return "cant generate id without a role!";
        }
    }
}

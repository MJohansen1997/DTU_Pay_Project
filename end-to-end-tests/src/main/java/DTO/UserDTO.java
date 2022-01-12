package DTO;

public class UserDTO {
    String firstName;
    String lastName;
    String cpr;
    String bankID;

    public UserDTO(String firstName, String lastName, String cpr, String bankID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpr = cpr;
        this.bankID = bankID;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}

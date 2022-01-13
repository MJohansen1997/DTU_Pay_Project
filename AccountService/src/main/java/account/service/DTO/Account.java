package account.service.DTO;

public class Account {
    String firstName;
    String lastName;
    String cpr;
    String bankID;
    String roleID;
//    BigDecimal balance;

    public Account(String firstName, String lastName, String cpr, String bankID, String roleID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpr = cpr;
        this.bankID = bankID;
        this.roleID = roleID;
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
    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

}

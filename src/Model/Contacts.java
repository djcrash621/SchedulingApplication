package Model;

public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    public Contacts (int newContactId, String newContactName, String newEmail){
        this.contactId = newContactId;
        this.contactName = newContactName;
        this.email = newEmail;
    }

    public int getContactId() {
        return this.contactId;
    }

    public void setContactId(int newContactId) { this.contactId = newContactId; }

    public void setContactName(String newContactName) {
        this.contactName = newContactName;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return (this.contactName);
    }
}

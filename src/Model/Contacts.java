package Model;

/**
 * This class defines the contacts object and its methods.
 */
public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    /**
     * Class constructor.
     * @param newContactId new Contact ID integer.
     * @param newContactName new Contact Name String.
     * @param newEmail new Email String.
     */
    public Contacts (int newContactId, String newContactName, String newEmail){
        this.contactId = newContactId;
        this.contactName = newContactName;
        this.email = newEmail;
    }

    /**
     * Returns current objects contact ID.
     * @return Contact ID integer.
     */
    public int getContactId() {
        return this.contactId;
    }

    /**
     * Sets a new contact ID for the current object.
     * @param newContactId new Contact ID integer.
     */
    public void setContactId(int newContactId) { this.contactId = newContactId; }

    /**
     * Sets a new contact name for the current object.
     * @param newContactName new Contact Name String.
     */
    public void setContactName(String newContactName) {
        this.contactName = newContactName;
    }

    /**
     * Sets a new email for the current object.
     * @param newEmail new Email String
     */
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    /**
     * Returns the contact name for the current object.
     * @return Contact Name String.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Returns the email for the current object
     * @return Email String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Overrides the default toString method to return the contact name of the current object.
     * @return Contact Name String
     */
    @Override
    public String toString() {
        return (this.contactName);
    }
}

package watchtower.entity;

import watchtower.dataaccesslayer.contract.IData;

/**
 * An entity representing a user.
 */
public class User implements IData {

    private final long id;
    private final String emailAddress;

    /**
     * Constructor
     * @param id
     * @param emailAddress
     */
    public User(long id, String emailAddress) {
        this.id = id;
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the index
     * @return Returns the index
     */
    @Override
    public String getIndex() {
        return "user";
    }

    /**
     * Returns the ID of the user
     * @return Returns the ID of the user
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the email address of the user
     * @return Returns the email address of the user
     */
    public String getEmailAddress() {
        return emailAddress;
    }

}

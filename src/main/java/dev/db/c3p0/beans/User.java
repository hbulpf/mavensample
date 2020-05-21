
package dev.db.c3p0.beans;

/**
 * User
 */
public class User {

    private Integer id;

    private String username;

    private String userpwd;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param userpwd  the userpwd
     */
    public User(String username, String userpwd) {
        this.username = username;
        this.userpwd = userpwd;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets userpwd.
     *
     * @return the userpwd
     */
    public String getUserpwd() {
        return userpwd;
    }

    /**
     * Sets userpwd.
     *
     * @param userpwd the userpwd
     */
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", userpwd='" + userpwd + '\'' + '}';
    }

}

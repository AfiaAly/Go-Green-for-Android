package models;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class User {
    /*@Getter @Setter*/ private String username;
    /*@Getter @Setter*/ private String password;
    /*@Getter @Setter*/ private boolean success;

    public User(String username, String password, boolean success) {
        this.username = username;
        this.password = password;
        this.success = success;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", success=" + success +
                '}';
    }
}

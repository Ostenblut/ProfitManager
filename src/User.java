public class User {
    private final String username;
    private final String password;
    private final String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String get_username() {
        return username;
    }

    public String get_password() {
        return password;
    }

    public String get_role() {
        return role;
    }
}

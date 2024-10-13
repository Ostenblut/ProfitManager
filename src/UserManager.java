public class UserManager {
    private final User[] users;
    private int user_counter;

    public UserManager(int capacity) {
        users = new User[capacity];
        user_counter = 0;
    }

    public void add_user(String username, String password, String role) {
        if (user_counter < users.length) {
            users[user_counter] = new User(username, password, role);
            user_counter++;
        } else {
            System.out.println("Cannot add user, capacity is full.");
        }
    }

    public User authenticate(String username, String password) {
        for (int i = 0; i < user_counter; i++) {
            if (users[i].get_username().equals(username) && users[i].get_password().equals(password)) {
                return users[i];
            }
        }
        return null;
    }
}

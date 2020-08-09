package jace.shim.tdd.chapter7;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<>();
    @Override
    public void save(User user) {
        this.users.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return this.users.get(id);
    }
}

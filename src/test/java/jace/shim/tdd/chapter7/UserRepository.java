package jace.shim.tdd.chapter7;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}

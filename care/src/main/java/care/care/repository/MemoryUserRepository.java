package care.care.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import care.care.domain.User;



public class MemoryUserRepository implements UserRepository {

    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;
    
    @Override
    public User save(User user) {
        user.setID(++sequence);
        store.put(user.getID(), user);
        return user;
    }

    @Override
    public Optional<User> findByid(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return store.values().stream().filter(user -> user.getName().equals(name)).findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}

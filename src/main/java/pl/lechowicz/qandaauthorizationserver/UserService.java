package pl.lechowicz.qandaauthorizationserver;

public interface UserService {
    User getByUsername(String username);

    User save(User entity);
}

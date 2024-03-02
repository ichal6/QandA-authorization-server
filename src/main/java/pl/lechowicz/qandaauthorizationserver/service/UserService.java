package pl.lechowicz.qandaauthorizationserver.service;

import pl.lechowicz.qandaauthorizationserver.domain.User;

public interface UserService {
    User getByUsername(String username);

    User save(User entity);
}

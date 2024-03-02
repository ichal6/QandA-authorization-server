package pl.lechowicz.qandaauthorizationserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.lechowicz.qandaauthorizationserver.domain.User;
import pl.lechowicz.qandaauthorizationserver.repository.UserRepository;
import pl.lechowicz.qandaauthorizationserver.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User getByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }

        return repository.findByUsername(username).orElse(null);
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }
}

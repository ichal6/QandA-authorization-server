package pl.lechowicz.qandaauthorizationserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.lechowicz.qandaauthorizationserver.domain.Role;
import pl.lechowicz.qandaauthorizationserver.repository.RoleRepository;
import pl.lechowicz.qandaauthorizationserver.service.RoleService;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    public static final String DEFAULT_ROLE = "USER";

    private final RoleRepository repository;

    @Override
    public Role getByName(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        return repository.findByName(name).orElse(null);
    }

    @Override
    public Role getDefaultRole() {
        return getByName(DEFAULT_ROLE);
    }
}

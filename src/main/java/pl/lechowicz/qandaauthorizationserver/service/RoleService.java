package pl.lechowicz.qandaauthorizationserver.service;

import pl.lechowicz.qandaauthorizationserver.domain.Role;

public interface RoleService {
    Role getByName(String name);
    Role getDefaultRole();
}

package pl.lechowicz.qandaauthorizationserver;

public interface RoleService {
    Role getByName(String name);
    Role getDefaultRole();
}

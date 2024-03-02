package pl.lechowicz.qandaauthorizationserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lechowicz.qandaauthorizationserver.domain.Role;

import java.util.Optional;

@Repository
public interface RoleRepository  extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}

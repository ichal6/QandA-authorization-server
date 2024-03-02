package pl.lechowicz.qandaauthorizationserver.configuration.socialLogin.mapper;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import pl.lechowicz.qandaauthorizationserver.domain.User;

public interface OidcUserMapper {
    OidcUser map(OidcIdToken idToken, OidcUserInfo userInfo, User localUser);

    OidcUser map(OidcUser oidcUser);
}

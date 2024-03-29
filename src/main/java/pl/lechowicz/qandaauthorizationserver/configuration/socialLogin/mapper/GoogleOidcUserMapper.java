package pl.lechowicz.qandaauthorizationserver.configuration.socialLogin.mapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import pl.lechowicz.qandaauthorizationserver.configuration.socialLogin.CustomOidcUser;
import pl.lechowicz.qandaauthorizationserver.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component("google")
public class GoogleOidcUserMapper implements OidcUserMapper {

    @Override
    public OidcUser map(OidcUser oidcUser) {
        CustomOidcUser user = new CustomOidcUser(oidcUser.getIdToken(), oidcUser.getUserInfo());
        user.setUsername(oidcUser.getEmail());
        //Enable new users by default
        user.setActive(true);
        return user;
    }

    @Override
    public OidcUser map(OidcIdToken idToken, OidcUserInfo userInfo, User user) {
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(role -> role.getAuthorities().stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                )
                .collect(Collectors.toSet());

        OidcIdToken customIdToken = getCustomIdToken(idToken, user);

        CustomOidcUser oidcUser = new CustomOidcUser(authorities, customIdToken, userInfo);
        oidcUser.setId(user.getId());
        oidcUser.setUsername(user.getUsername());
        oidcUser.setCreatedAt(user.getCreatedAt());
        oidcUser.setActive(user.isActive());
        return oidcUser;
    }

    private OidcIdToken getCustomIdToken(OidcIdToken idToken, User user) {
        Map<String, Object> claims = new HashMap<>(idToken.getClaims());
        claims.put(StandardClaimNames.GIVEN_NAME, user.getFirstName());
        claims.put(StandardClaimNames.MIDDLE_NAME, user.getMiddleName());
        claims.put(StandardClaimNames.FAMILY_NAME, user.getLastName());
        claims.put(StandardClaimNames.LOCALE, user.getLocale());
        claims.put(StandardClaimNames.PICTURE, user.getAvatarUrl());

        return new OidcIdToken(
                idToken.getTokenValue(), idToken.getIssuedAt(), idToken.getExpiresAt(), claims
        );
    }
}
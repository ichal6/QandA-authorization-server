package pl.lechowicz.qandaauthorizationserver.configuration.socialLogin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.function.Consumer;

public class SocialLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthenticationSuccessHandler delegate = new SavedRequestAwareAuthenticationSuccessHandler();

    private final Consumer<OAuth2User> oauth2UserHandler = (user) -> {};

    @Setter
    private Consumer<OidcUser> oidcUserHandler = this.oauth2UserHandler::accept;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException, ServletException {
        if (authentication instanceof OAuth2AuthenticationToken) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof OidcUser) {
                this.oidcUserHandler.accept((OidcUser) principal);
            } else if (principal != null) {
                this.oauth2UserHandler.accept((OAuth2User) principal);
            }
        }

        this.delegate.onAuthenticationSuccess(request, response, authentication);
    }
}

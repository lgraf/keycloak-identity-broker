package org.lgraf.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Base64;

@Controller
class ProfileController {

    private final OAuth2AuthorizedClientService acs;

    ProfileController(OAuth2AuthorizedClientService acs) {
        this.acs = acs;
    }

    @GetMapping("/")
    String index(Model model, OAuth2AuthenticationToken authentication) {
        OidcUser principal = (OidcUser) authentication.getPrincipal();

        model.addAttribute("preferredUsername", principal.getPreferredUsername());
        model.addAttribute("email", principal.getEmail());
        model.addAttribute("givenName", principal.getGivenName());
        model.addAttribute("familyName", principal.getFamilyName());
        model.addAttribute("authorities", principal.getAuthorities());
        model.addAttribute("token", getTokenPayload(authentication));

        return "profile";
    }

    private String getTokenPayload(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient authorizedClient = acs.loadAuthorizedClient(authentication
                .getAuthorizedClientRegistrationId(), authentication.getName());

        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        String payload = accessToken.split("[.]")[1];
        String decoded = new String(Base64.getDecoder().decode(payload));

        return prettify(decoded);
    }

    private String prettify(String json) {
        ObjectMapper mapper = new ObjectMapper();

        String prettyJson;
        try {
            Object jsonObject = mapper.readValue(json, Object.class);
            prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (IOException x) {
            throw new IllegalArgumentException("invalid json string!", x);
        }

        return prettyJson;
    }

}

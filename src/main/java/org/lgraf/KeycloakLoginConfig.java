package org.lgraf;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter
        .DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;


@EnableWebSecurity
class KeycloakLoginConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .loginPage(DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/keycloak");
    }
}

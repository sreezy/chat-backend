package oasis.contact.chat.security;

import oasis.contact.chat.security.ApiKeyAuthentication;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static final String ADMIN_AUTH_TOKEN = "admin-api-key";
    private static final String USER_AUTH_TOKEN = "user-api-key";

    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || (!apiKey.equals(ADMIN_AUTH_TOKEN) && !apiKey.equals(USER_AUTH_TOKEN))) {
            throw new BadCredentialsException("Invalid API Key");
        }

        // Assign authorities based on API Key
        List<GrantedAuthority> authorities;
        if (ADMIN_AUTH_TOKEN.equals(apiKey)) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities = Collections.emptyList(); // No specific authority for user
        }

        return new ApiKeyAuthentication(apiKey, authorities);
    }
}

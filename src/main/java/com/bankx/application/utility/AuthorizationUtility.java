package com.bankx.application.utility;

import com.bankx.application.web.rest.errors.BadRequestAlertException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuthorizationUtility {

    private AuthorizationUtility() {

    }

    public static String getLoggedInUsername() {
        return getAuthentication().getName();
    }

    public static String getAccountNoPrefix() {
        return getAuthentication().getName();
    }

    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BadRequestAlertException("User authentication is not exist");
        }
        return authentication;
    }

    public static String getLoggedInUsernameOrElse(String elseUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication != null) && !(authentication instanceof AnonymousAuthenticationToken)) {
            elseUser = authentication.getName();
        }

        return elseUser;
    }

}

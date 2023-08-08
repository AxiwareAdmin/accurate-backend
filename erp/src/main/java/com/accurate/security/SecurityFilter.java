package com.accurate.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Spring Web MVC Security Java Config Demo Project
 * Bootstraps Spring Security Filter.
 *
 * @author www.codejava.net
 *
 */
public class SecurityFilter extends AbstractSecurityWebApplicationInitializer {
 
    public SecurityFilter() {
        super(SecurityConfig.class);
    }
 
}
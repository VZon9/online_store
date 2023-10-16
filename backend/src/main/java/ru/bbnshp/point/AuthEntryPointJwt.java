package ru.bbnshp.point;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> requestURI = Arrays.stream(request.getRequestURI().split("/")).toList();
         if(requestURI.get(1).equals("admin") && !requestURI.get(2).equals("login") && !(authentication instanceof AnonymousAuthenticationToken)) {
            response.sendRedirect("/admin/login");
        }
         else {
             logger.error("Unauthorized error: {}", authException.getMessage());
             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
         }
    }
}

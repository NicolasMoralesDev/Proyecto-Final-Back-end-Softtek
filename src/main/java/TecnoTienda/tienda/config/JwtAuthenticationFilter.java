package TecnoTienda.tienda.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This component is used to filter the requests and check if the user is authenticated. If the user is authenticated,
 * the request is allowed to continue. If the user is not authenticated, the request is rejected. Also, this component is
 * used to extract the user email from the JWT token and create an authentication in the security context.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * This method is an implementation of the doFilterInternal method of the OncePerRequestFilter class. doFilterInternal
     * can be used to apply a filter to the requests. In this case, the filter is used to check if the user is authenticated.
     *
     * @param request The request.
     * @param response The response.
     * @param filterChain The filter chain.
     * @throws ServletException Exception thrown if there is an error in the servlet.
     * @throws IOException Exception thrown if there is an error in the input/output.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String jwt;
        String userEmail;

        // Gets the Authorization header, which contains the token.
        final String authHeader = request.getHeader("Authorization");

        // If the header does not exist or does not start with "Bearer ", the request is allowed to continue.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // If the header exists and starts with "Bearer ", then we extract the token without the "Bearer " prefix.
        jwt = authHeader.substring(7);

        try {
            // We extract the user email from the token.
            userEmail = jwtService.extractUsername(jwt);

            // If the user email is not null and the user is not authenticated, we create an authentication in the security context.
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // We get the user details from the user email.
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                // We check if the token is valid.
                if (!jwtService.isTokenValid(jwt, userDetails)) {
                    SecurityContextHolder.getContext().setAuthentication(null);
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token");
                    return;
                }

                // We create an authentication in the security context.
                if (jwtService.isTokenValid(jwt, userDetails)) {

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities() // Roles del usuario.
                    );

                    // Adding the authentication details to the authentication token.
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    // Adding the authentication to the security context.
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token");
            return;
        }
        filterChain.doFilter(request, response); // We allow the request to continue.
    }
}

package TecnoTienda.tienda.config;

import TecnoTienda.tienda.exceptions.UserValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    // Key de 250 bits hex obtenida desde application.properties
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /**
     * Extract the username from a token. If the token is invalid, the method will return null.
     * @param token The token from which the username will be extracted.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        String username;
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * This method is used to generate a token with no extra claims.
     * @param userDetails The user details of the user that is trying to be authenticated.
     * @return The generated token.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * This method is used to validate a token. The method will return true if the token is valid and false if the token
     * is invalid.
     * @param token The token to be validated.
     * @param userDetails The user details of the user that is trying to be authenticated.
     * @return True if the token is valid and false if the token is invalid.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        boolean isTokenExpired;
        try {
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            throw new UserValidationException("Invalid token");
        }
    }

    /**
     * This method is used to generate a token.
     * @param extraClaims The extra claims that will be included in the token. The extra claims are optional and they
     *                    need to be passed as a Map<String, Object>., where the key is the name of the claim and the
     *                    value is the value of the claim.
     * @param userDetails The user details of the user that is trying to be authenticated.
     * @return The generated token.
     */
    public String generateToken(
            Map<String, Object> extraClaims, // Claims to be added to the token.
            UserDetails userDetails // User details of the user that is trying to be authenticated.
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSignInKey())
                .compact();

    }

    /**
     * This method is used to extract a claim from a token.
     * @param token The token from which the claim will be extracted.
     * @param claimsResolver The function that will be used to extract the claim.
     * @param <T> The type of the claim.
     * @return The claim extracted from the token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * This method is used to extract all the claims from a token.
     * @param token The token from which the claims will be extracted.
     * @return The claims extracted from the token.
     */
    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    /**
     * This method is used to get the signing key.
     * @return The signing key.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * This method is used to check if a token is expired. The method will return true if the token is expired and false
     * if the token is not expired.
     * @param token The token to be checked.
     * @return True if the token is expired and false if the token is not expired.
     */
    private boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    /**
     * This method is used to extract the expiration date from a token.
     * @param token The token from which the expiration date will be extracted.
     * @return The expiration date extracted from the token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
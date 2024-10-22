// 1
package com.example.library.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utility class for generating, parsing, and validating JWT tokens.
 */
@Component
public class JwtUtils {

    // Secret key used to sign and validate the JWT. This should be securely stored and not hard-coded in production.
    private final Key jwtSecretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

    // JWT expiration time in milliseconds. Here, it's set to 10 minutes (600000 milliseconds).
    private final long jwtExpirationMs = 600000;

    /**
     * Generates a JWT token for a given username.
     *
     * @param username the username to include in the token.
     * @return the generated JWT token as a string.
     */
    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username) // The "subject" of the token, which in this case is the username
                .setIssuedAt(new Date()) // Setting the time the token was issued (current time)
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Expiration time of the token
                .signWith(jwtSecretKey) // Signing the token with the secret key using HS512 algorithm
                .compact();  // Finalizes the creation of the token
    }

    /**
     * Extracts the username from a given JWT token.
     *
     * @param token the JWT token.
     * @return the username contained within the token.
     */
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey) // Setting the signing key to verify the token's signature
                .build()
                .parseClaimsJws(token) // Parses the token and verifies its signature
                .getBody() // Retrieves the claims (payload) of the token
                .getSubject();  // Extracts the "subject" field, which is the username
    }

    /**
     * Validates the given JWT token. This checks the token's signature and
     * expiration.
     *
     * @param token the JWT token to validate.
     * @return true if the token is valid; false otherwise.
     */
    public boolean validateJwtToken(String token) {
        try {
            // Try parsing and validating the token
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
            return true;  // If no exception is thrown, the token is valid
        } catch (SecurityException e) {
            // This exception is thrown if there is an invalid signature
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            // This exception is thrown if the token is malformed
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            // This exception is thrown if the token has expired
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            // This exception is thrown if the token is unsupported
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // This exception is thrown if the claims string is empty or invalid
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }

        return false;  // If any exception is caught, the token is invalid
    }
}

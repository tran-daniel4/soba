package com.example.soba.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET = System.getenv("JWT_SECRET");
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    public static String generateToken(String username) {
        try {
            // Creating JWS HMAC Signer
            JWSSigner signer = new MACSigner(SECRET);

            // Prepare JWT with claims set
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            // Applying HMAC Protection
            signedJWT.sign(signer);

            // Serializing JWT to compact form
            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Error generating JWT", e);
        }
    }

    // Validate and parse a JWT
    public static String validateToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET);

            if (jwsObject.verify(verifier)) {
                JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
                if (new Date().before(claims.getExpirationTime())) {
                    return claims.getSubject(); // Returns the username
                } else {
                    throw new RuntimeException("Token is expired");
                }
            } else {
                throw new RuntimeException("Invalid signature");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error validating JWT", e);
        }
    }
}

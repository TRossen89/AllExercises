package org.weeks.week6.Security.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.javalin.http.Handler;
import org.weeks.week6.Security.dtos.UserDTO;
import org.weeks.week6.Security.exceptions.ApiException;
import org.weeks.week6.Security.model.User;
import org.weeks.week6.Security.persistence.daos.SecurityDao;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class SecurityController {


    private static SecurityController instance;

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static SecurityDao securityDao = new SecurityDao();

    private final String SECRET_KEY = "DetteErEnHemmeligNøgleTilAtDanneJWT_TokensMed";


    public static SecurityController getInstance() {
        if (instance != null) {
            instance = new SecurityController();
        }
        return instance;
    }


    public Handler login() {

        return ctx -> {
            ObjectNode returnObject = objectMapper.createObjectNode();

            try {
                UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);

                User verifiedUser = securityDao.getVerifiedUser(userDTO.getUsername(), userDTO.getPassword());
                String token = createToken(new UserDTO(verifiedUser));

            }

        };
    }


    public String createToken(UserDTO userDTO) {
        String ISSUER;
        String TOKEN_EXPIRE_TIME;
        String SECRET_KEY;

        if (System.getenv("DEPLOYED") != null)
            {
                ISSUER = System.getenv("ISSUER");
                TOKEN_EXPIRE_TIME = System.getenv("TOKEN_EXPIRE_TIME");
                SECRET_KEY = System.getenv("SECRET_KEY");
            } else{
                ISSUER = "Thomas Hartmann";
                TOKEN_EXPIRE_TIME = "1800000"; // 30 minutes in milliseconds
                SECRET_KEY = this.SECRET_KEY;
            }
            return createTokenAsString(userDTO, ISSUER, TOKEN_EXPIRE_TIME, SECRET_KEY);
        }

    private String createTokenAsString(UserDTO userDTO, String ISSUER, String TOKEN_EXPIRE_TIME, String SECRET_KEY) {

        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(userDTO.getUsername())
                    .issuer(ISSUER)
                    .claim("username", userDTO.getUsername())
                    .claim("roles", userDTO.getRoles().stream().reduce("", (s1, s2) -> s1 + "," + s2))
                    .expirationTime(new Date(new Date().getTime() + Integer.parseInt(TOKEN_EXPIRE_TIME)))
                    .build();
            Payload payload = new Payload(claimsSet.toJSONObject());

            JWSSigner signer = new MACSigner(SECRET_KEY);
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);
            jwsObject.sign(signer);
            return jwsObject.serialize();

        } catch (JOSEException e) {
            e.printStackTrace();
            throw new ApiException(500, "Could not create token");
        }
    }

    public boolean authorize (UserDTO user, Set < String > allowedRoles){

            AtomicBoolean hasAccess = new AtomicBoolean(false);

            if (user != null) {
                user.getRoles().stream().forEach(role -> {
                    if (allowedRoles.contains(role.toUpperCase())) {
                        hasAccess.set(true);
                    }
                });
            }
            return hasAccess.get();
        }
    }

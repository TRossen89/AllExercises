package org.weeks.week6.Security.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.RouteRole;
import org.weeks.week6.Security.controllers.SecurityController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class SecurityRoutes {

    private static SecurityController securityController = SecurityController.getInstance();
    private static ObjectMapper jsonMapper = new ObjectMapper();

    public static EndpointGroup getSecurityRoutes() {

        return () -> {
            path("/auth", () -> {
                post("/login", securityController.login(), Role.ANYONE);
                post("/register", securityController.register(), Role.ANYONE);
            });
        };
    }

    public static EndpointGroup getSecuredRoutes() {

        return () -> {
            path("/protected", () -> {
                before(securityController.authenticate());
                get("/user_demo", (ctx) -> ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from USER Protected")), Role.USER);
                get("/admin_demo", (ctx) -> ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from ADMIN Protected")), Role.ADMIN);
            });
        };
    }

    public enum Role implements RouteRole {ANYONE, USER, ADMIN}
}

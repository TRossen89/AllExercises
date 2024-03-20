package org.weeks.week6.Security.routes;

import io.javalin.apibuilder.EndpointGroup;
import org.weeks.week6.Security.controllers.SecurityController;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class SecurityRoutes {

private static SecurityController securityController = SecurityController.getInstance();

    public static EndpointGroup getSecurityRoutes() {
        return ()->{
            path("/auth", ()->{
                post("/login", securityController.login(), Role.ANYONE);
                post("/register", securityController.register(), Role.ANYONE);
            });
        };
    }

    public static EndpointGroup getSecuredRoutes() {
    }
}

package org.weeks.week6.Security;


import org.weeks.week6.Security.config.ApiConfig;
import org.weeks.week6.Security.controllers.SecurityController;
import org.weeks.week6.Security.routes.SecurityRoutes;

public class Main {

    public static void main(String[] args) {

        //SecurityController securityControllerInstantiated = SecurityController.getInstance();

        ApiConfig
                .getInstance()
                .initiateServer()
                .errorHandling()
                .setApiExceptionHandling()
                .setGeneralExceptionHandling()
                .checkSecurityRoles()
                .startServer(7007)
                .setRoutes(SecurityRoutes.getSecurityRoutes())
                .setRoutes(SecurityRoutes.getSecuredRoutes());

    }
}

package org.weeks.week6.Security;


import org.weeks.week6.Security.config.ApiConfig;
import org.weeks.week6.Security.routes.SecurityRoutes;

public class Main {


    public static void main(String[] args) {

        ApiConfig
                .getInstance()
                .initiateServer()
                .errorHandling()
                .startServer(7007)
                .setRoutes(SecurityRoutes.getSecurityRoutes())
                .setRoutes(SecurityRoutes.getSecuredRoutes());

    }
}

package org.weeks.week5.part2_Vetirinarian_exercise.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class ApplicationConfig {


    private ObjectMapper objectMapper = new ObjectMapper();
    private static ApplicationConfig instance;
    private static Javalin app;

    private ApplicationConfig() {

    }

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }


    public ApplicationConfig initiateServer() {

        app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            config.routing.contextPath = "api/vet";
        });
        return instance;
    }

    public ApplicationConfig setRoutes(EndpointGroup routes) {
        app.routes(() -> {
            path("/", routes);
        });
        return instance;
    }

    public ApplicationConfig startServer(int port){
        app.start(port);
        return instance;
    }

    public ApplicationConfig errorHandling(){

        app.error(404, ctx -> {
            String message = ctx.attribute("message");
            message = "{\"404\": \"" + message + "\"}";
            ctx.json(message);
        });

        app.error(500, ctx -> {
            String message = ctx.attribute("message");
            message = "{\"500\": \"" + message + "\"}";
            ctx.json(message);
        });

        return instance;
    }

}

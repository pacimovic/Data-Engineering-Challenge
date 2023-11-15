package com.example.backend.resources;

import com.example.backend.entities.LogInOut;
import com.example.backend.services.LogInOutService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/logInOuts")
public class LogInOutResource {

    @Inject
    private LogInOutService logInOutService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LogInOut> allLogInOuts(){
        return  this.logInOutService.allLogInOuts();
    }
}

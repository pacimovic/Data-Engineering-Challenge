package com.example.backend.resources;

import com.example.backend.entities.Country;
import com.example.backend.entities.LogInOut;
import com.example.backend.services.LogInOutService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/log_in_outs")
public class LogInOutResource {

    @Inject
    private LogInOutService logInOutService;

    @GET
    @Produces("text/plain")
    public int numOfActiveUsers() {
        return this.logInOutService.numOfActiveUsers();
    }

    @GET
    @Path("/{date1}/{date2}")
    @Produces("text/plain")
    public int numOfActiveUsersDate(@PathParam("date1") Integer date1, @PathParam("date2") Integer date2) {
        return this.logInOutService.numOfActiveUsersDate(date1, date2);
    }

    @GET
    @Path("/country")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> numOfActiveUsersCountry() {
        return this.logInOutService.numOfActiveUsersCountry();
    }

    @GET
    @Path("/country/{date1}/{date2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> numOfActiveUsersCountryDate(@PathParam("date1") Integer date1, @PathParam("date2") Integer date2) {
        return this.logInOutService.numOfActiveUsersCountryDate(date1, date2);
    }

    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LogInOut> userLogins(@PathParam("user_id") String user_id) {
        return this.logInOutService.userLogins(user_id);
    }
}

package com.example.backend.resources;

import com.example.backend.entities.Country;
import com.example.backend.entities.Registration;
import com.example.backend.services.RegistrationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/registrations")
public class RegistrationResource {

    @Inject
    private RegistrationService registrationService;

    @GET
    @Produces("text/plain")
    public int paidUsers(){
        return this.registrationService.paidUsers();
    }
    @GET
    @Path("/{date1}/{date2}")
    @Produces("text/plain")
    public int paidUsersDate(@PathParam("date1") Integer date1, @PathParam("date2") Integer date2){
        return this.registrationService.paidUsersDate(date1, date2);
    }
    @GET
    @Path("/country")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> paidUsersCountry() {
        return this.registrationService.paidUsersCountry();
    }
    @GET
    @Path("/country/{date1}/{date2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> paidUsersCountryDate(@PathParam("date1") Integer date1, @PathParam("date2") Integer date2){
        return this.registrationService.paidUsersCountryDate(date1, date2);
    }
    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Registration userRegistration(@PathParam("user_id") String user_id) {
        return this.registrationService.userRegistration(user_id);
    }

}

package com.example.backend.resources;

import com.example.backend.entities.Country;
import com.example.backend.entities.GameSession;
import com.example.backend.services.GameSessionService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/sessions")
public class GameSessionResource {

    @Inject
    private GameSessionService gameSessionService;

    @GET
    @Produces("text/plain")
    public float sessionsAvg() {
        return this.gameSessionService.sessionsAvg();
    }
    @GET
    @Path("/{date1}/{date2}")
    @Produces("text/plain")
    public float sessionsAvgDate(@PathParam("date1") Integer date1,@PathParam("date2") Integer date2) {
        return this.gameSessionService.sessionsAvgDate(date1, date2);
    }
    @GET
    @Path("/country")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> sessionsAvgCountry() {
        return this.gameSessionService.sessionsAvgCountry();
    }
    @GET
    @Path("/country/{date1}/{date2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> sessionsAvgCountryDate(@PathParam("date1") Integer date1,@PathParam("date2") Integer date2){
        return this.gameSessionService.sessionsAvgCountryDate(date1, date2);
    }
    //------------------------------------------------------------------------------------------------------------------
    @GET
    @Path("/time")
    @Produces("text/plain")
    public float timeAvg() {
        return this.gameSessionService.timeAvg();
    }
    @GET
    @Path("/time/{date1}/{date2}")
    @Produces("text/plain")
    public float timeAvgDate(@PathParam("date1") Integer date1,@PathParam("date2") Integer date2) {
        return this.gameSessionService.timeAvgDate(date1, date2);
    }
    @GET
    @Path("/time/country")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> timeAvgCountry() {
        return this.gameSessionService.timeAvgCountry();
    }
    @GET
    @Path("/time/country/{date1}/{date2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> timeAvgCountryDate(@PathParam("date1") Integer date1,@PathParam("date2") Integer date2){
        return this.gameSessionService.timeAvgCountryDate(date1, date2);
    }

    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GameSession> userGameSessions(@PathParam("user_id") String user_id){
        return this.gameSessionService.userGameSessions(user_id);
    }
}

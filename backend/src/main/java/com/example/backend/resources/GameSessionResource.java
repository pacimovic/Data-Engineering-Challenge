package com.example.backend.resources;

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
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GameSession> userGameSessions(@PathParam("user_id") String user_id){
        return this.gameSessionService.userGameSessions(user_id);
    }
}

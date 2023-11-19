package com.example.backend.resources;

import com.example.backend.entities.Country;
import com.example.backend.entities.TransactionEvent;
import com.example.backend.services.TransactionService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("transactions")
public class TransactionResource {
    @Inject
    private TransactionService transactionService;

    @GET
    @Produces("text/plain")
    public float totalRevenue(){
        return transactionService.totalRevenue();
    }

    @GET
    @Path("/{date1}/{date2}")
    @Produces("text/plain")
    public float totalRevenueDate(@PathParam("date1") Integer date1,@PathParam("date2") Integer date2){
        return transactionService.totalRevenueDate(date1, date2);
    }

    @GET
    @Path("/country")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> totalRevenueCountry() {
        return this.transactionService.totalRevenueCountry();
    }

    @GET
    @Path("/country/{date1}/{date2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> totalRevenueCountryDate(@PathParam("date1") Integer date1,@PathParam("date2") Integer date2) {
        return this.transactionService.totalRevenueCountryDate(date1, date2);
    }
}

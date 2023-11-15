package com.example.backend.resources;

import com.example.backend.entities.TransactionEvent;
import com.example.backend.services.TransactionService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("transactions")
public class TransactionResource {
    @Inject
    private TransactionService transactionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransactionEvent> allTransactions(){
        return this.transactionService.allTransactions();
    }
}

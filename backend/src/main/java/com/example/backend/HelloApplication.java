package com.example.backend;

import com.example.backend.entities.LogInOut;
import com.example.backend.entities.Registration;
import com.example.backend.entities.TransactionEvent;
import com.example.backend.repositories.*;
import com.example.backend.services.LogInOutService;
import com.example.backend.services.ProcessData;
import com.example.backend.services.RegistrationService;
import com.example.backend.services.TransactionService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication(){
        AbstractBinder abstractBinder = new AbstractBinder() {
            @Override
            protected void configure() {

                this.bind(MySqlLogInOutRepository.class).to(LogInOutRepository.class).in(Singleton.class);
                this.bind(MySqlRegistrationRepository.class).to(RegistrationRepository.class).in(Singleton.class);
                this.bind(MySqlTransactionRepository.class).to(TransactionRepository.class).in(Singleton.class);

                this.bindAsContract(LogInOutService.class);
                this.bindAsContract(RegistrationService.class);
                this.bindAsContract(TransactionService.class);
            }
        };

        this.register(abstractBinder);

        packages("com.example.backend");

        ProcessData processData = new ProcessData();

        processData.loadJSONl("events.jsonl");

        processData.startCleaning();

        System.out.println("Loading to database...Its gonna take a while :(");
        processData.loadToDatabase();
        System.out.println("Loading done.");
    }

}

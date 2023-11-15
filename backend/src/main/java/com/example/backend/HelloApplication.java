package com.example.backend;

import com.example.backend.entities.LogInOut;
import com.example.backend.repositories.LogInOutRepository;
import com.example.backend.repositories.MySqlLogInOutRepository;
import com.example.backend.services.LogInOutService;
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
import java.util.List;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    private static List<LogInOut> logInOuts = new ArrayList<>();

    public HelloApplication(){
        AbstractBinder abstractBinder = new AbstractBinder() {
            @Override
            protected void configure() {

                this.bind(MySqlLogInOutRepository.class).to(LogInOutRepository.class).in(Singleton.class);

                this.bindAsContract(LogInOutService.class);
            }
        };

        this.register(abstractBinder);

        packages("com.example.backend");


        loadJSONl("events.jsonl");


        System.out.println("Loading to database...Its gonna take a while :(");
        loadToDatabase();
        System.out.println("Loading done.");

    }

    private void loadToDatabase(){
        //LoginOut
        Configuration con = new Configuration().configure().addAnnotatedClass(LogInOut.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        for (LogInOut logInOut: logInOuts){
            session.save(logInOut);
        }
        tx.commit();
    }

    private void loadJSONl(String filePath){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);

        if (inputStream != null) {
            // Create ObjectMapper and JsonFactory
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jsonFactory = objectMapper.getFactory();

            // Create JsonParser
            try (JsonParser jsonParser = jsonFactory.createParser(new BufferedReader(new InputStreamReader(inputStream)))) {
                // Read each line as a JSON object
                while (jsonParser.nextToken() != null) {
                    JsonNode jsonNode = objectMapper.readTree(jsonParser);

                    //Inner object
                    JsonNode jsonNodeChild = jsonNode.get("event_data");

                    //Common for all entities
                    int event_id = jsonNode.get("event_id").asInt();
                    int event_timestamp = jsonNode.get("event_timestamp").asInt();
                    String event_type = jsonNode.get("event_type").asText();
                    String user_id = jsonNodeChild.get("user_id").asText();

                    if(jsonNode.get("event_type").asText().equals("login") || jsonNode.get("event_type").asText().equals("logout")){
                        LogInOut logInOut = new LogInOut(event_id, event_timestamp, event_type, user_id);
                        logInOuts.add(logInOut);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}

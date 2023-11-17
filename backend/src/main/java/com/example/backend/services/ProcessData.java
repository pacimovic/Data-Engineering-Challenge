package com.example.backend.services;

import com.example.backend.entities.GameSession;
import com.example.backend.entities.LogInOut;
import com.example.backend.entities.Registration;
import com.example.backend.entities.TransactionEvent;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ProcessData {

    private List<LogInOut> logInOuts = new ArrayList<>();
    private List<Registration> registrations = new ArrayList<>();
    private List<TransactionEvent> transactions = new ArrayList<>();
    private List<GameSession> gameSessions = new ArrayList<>();
    private HashMap<String,Integer> map = new HashMap<>();

    public void startCleaning(){
        System.out.println("Logs: " + logInOuts.size());
        System.out.println("Registrations: " +registrations.size());
        System.out.println("Transactions: " + transactions.size());

        Collections.sort(logInOuts);

        List<LogInOut> logsTmp = new ArrayList<>();
        int sessionId = 1;
        for(LogInOut log: logInOuts){
            //if event type is login and this user hasn't already been logged in
            if(log.getEvent_type().equals("login")  && !map.containsKey(log.getUser_id())){
                map.put(log.getUser_id(), log.getEvent_timestamp());
                logsTmp.add(log);
            }
            //if event type is logout and user has been logged in
            else if(log.getEvent_type().equals("logout") && map.containsKey(log.getUser_id())){
                GameSession gameSession = new GameSession(sessionId, log.getUser_id(), map.get(log.getUser_id()), log.getEvent_timestamp());
                gameSessions.add(gameSession);
                sessionId++;
                map.remove(log.getUser_id());
                logsTmp.add(log);
            }
        }
        //passing through all users that has been logged in
        int last_timestamp = logInOuts.get(logInOuts.size() - 1).getEvent_timestamp();
        for (Map.Entry<String, Integer> set : map.entrySet()) {
            GameSession gameSession = new GameSession(sessionId, set.getKey(), set.getValue(), last_timestamp);
            gameSessions.add(gameSession);
            sessionId++;
        }

        logInOuts = logsTmp;
        System.out.println("Cleaned logs: " +  logsTmp.size());
        System.out.println("Game sessions: " + gameSessions.size());

    }
    public void loadToDatabase(){
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

        //Registration
        con = new Configuration().configure().addAnnotatedClass(Registration.class);
        reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        sf = con.buildSessionFactory(reg);
        session = sf.openSession();
        tx = session.beginTransaction();
        for (Registration registration: registrations){
            session.save(registration);
        }
        tx.commit();

        //Transaction
        con = new Configuration().configure().addAnnotatedClass(TransactionEvent.class);
        reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        sf = con.buildSessionFactory(reg);
        session = sf.openSession();
        tx = session.beginTransaction();
        for (TransactionEvent transactionEvent: transactions){
            session.save(transactionEvent);
        }
        tx.commit();

        //Game sessions
        con = new Configuration().configure().addAnnotatedClass(GameSession.class);
        reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        sf = con.buildSessionFactory(reg);
        session = sf.openSession();
        tx = session.beginTransaction();
        for (GameSession gameSession: gameSessions){
            session.save(gameSession);
        }
        tx.commit();
    }

    public void loadJSONl(String filePath){
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
                    else if(jsonNode.get("event_type").asText().equals("registration")){
                        String country = jsonNodeChild.get("country").asText();
                        String name = jsonNodeChild.get("name").asText();
                        String device_os = jsonNodeChild.get("device_os").asText();
                        String marketing_campaign = jsonNodeChild.get("marketing_campaign").asText();
                        Registration registration = new Registration(event_id, event_timestamp, event_type, user_id, country, name, device_os, marketing_campaign);
                        registrations.add(registration);
                    }
                    else if(jsonNode.get("event_type").asText().equals("transaction")){
                        float transaction_amount = (float) jsonNodeChild.get("transaction_amount").asDouble();
                        String transaction_currency = jsonNodeChild.get("transaction_currency").asText();
                        TransactionEvent transaction = new TransactionEvent(event_id, event_timestamp, event_type, user_id, transaction_amount, transaction_currency);
                        transactions.add(transaction);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

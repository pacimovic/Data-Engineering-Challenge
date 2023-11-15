package com.example.backend.repositories;

import com.example.backend.entities.LogInOut;
import com.example.backend.entities.Registration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlRegistrationRepository extends MySqlAbstractRepository implements RegistrationRepository{

    @Override
    public List<Registration> allRegistratiion() {
        List<Registration> registrations = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from registrations");
            while (resultSet.next()) {
                int event_id = resultSet.getInt("event_id");
                int event_timestamp = resultSet.getInt("event_timestamp");
                String event_type = resultSet.getString("event_type");
                String user_id = resultSet.getString("user_id");
                String country = resultSet.getString("country");
                String name = resultSet.getString("name");
                String device_os = resultSet.getString("device_os");
                String marketing_campaign = resultSet.getString("marketing_campaign");
                Registration registration = new Registration(event_id, event_timestamp, event_type, user_id, country, name, device_os, marketing_campaign);
                registrations.add(registration);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return registrations;
    }
}

package com.example.backend.repositories;

import com.example.backend.entities.Country;
import com.example.backend.entities.LogInOut;
import com.example.backend.entities.Registration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRegistrationRepository extends MySqlAbstractRepository implements RegistrationRepository{


    @Override
    public int paidUsers() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int paidUsers = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS registration_count\n" +
                    "FROM registrations\n" +
                    "WHERE marketing_campaign <> ? AND marketing_campaign <> '';");
            preparedStatement.setString(1, "null");
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                paidUsers = resultSet.getInt("registration_count");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return paidUsers;
    }

    @Override
    public int paidUsersDate(Integer date1, Integer date2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int paidUsers = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS registration_count\n" +
                    "FROM registrations\n" +
                    "WHERE marketing_campaign <> ? AND marketing_campaign <> '' AND event_timestamp BETWEEN ? AND ?;");
            preparedStatement.setString(1, "null");
            preparedStatement.setInt(2, date1);
            preparedStatement.setInt(3, date2);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                paidUsers = resultSet.getInt("registration_count");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return paidUsers;
    }

    @Override
    public List<Country> paidUsersCountry() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT country, " +
                    "COUNT(*) AS registration_count\n" +
                    "FROM registrations r\n" +
                    "WHERE marketing_campaign <> ? AND marketing_campaign <> '' " +
                    "GROUP BY country;");
            preparedStatement.setString(1, "null");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String countryName = resultSet.getString("country");
                int number = resultSet.getInt("registration_count");
                Country country = new Country(countryName, number);
                countries.add(country);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return countries;
    }

    @Override
    public List<Country> paidUsersCountryDate(Integer date1, Integer date2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT country, " +
                    "COUNT(*) AS registration_count\n" +
                    "FROM registrations r\n" +
                    "WHERE marketing_campaign <> ? AND marketing_campaign <> '' AND event_timestamp BETWEEN ? AND ? " +
                    "GROUP BY country;");
            preparedStatement.setString(1, "null");
            preparedStatement.setInt(2, date1);
            preparedStatement.setInt(3, date2);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String countryName = resultSet.getString("country");
                int number = resultSet.getInt("registration_count");
                Country country = new Country(countryName, number);
                countries.add(country);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return countries;
    }

    @Override
    public Registration userRegistration(String user_id) {
        Registration registration = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM registrations where user_id = ?");
            preparedStatement.setString(1, user_id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int event_id = resultSet.getInt("event_id");
                int event_timestamp = resultSet.getInt("event_timestamp");
                String event_type = resultSet.getString("event_type");
                String user_reg_id = resultSet.getString("user_id");
                String country = resultSet.getString("country");
                String name = resultSet.getString("name");
                String device_os = resultSet.getString("device_os");
                String marketing_campaign = resultSet.getString("marketing_campaign");
                registration = new Registration(event_id, event_timestamp, event_type, user_reg_id, country, name, device_os, marketing_campaign);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return registration;
    }
}

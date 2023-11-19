package com.example.backend.repositories;

import com.example.backend.entities.Country;
import com.example.backend.entities.LogInOut;
import com.example.backend.entities.Registration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlLogInOutRepository extends MySqlAbstractRepository implements LogInOutRepository{

    @Override
    public int numOfLogins() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int numOfLogins = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT COUNT(event_id) AS logins_count FROM log_in_outs WHERE event_type = ?");
            preparedStatement.setString(1, "login");
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                numOfLogins = resultSet.getInt("logins_count");
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

        return numOfLogins;
    }

    @Override
    public int numOfLoginsDate(Integer date1, Integer date2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int numOfLogins = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT COUNT(event_id) AS logins_count FROM log_in_outs WHERE event_type = ?" +
                    "AND event_timestamp BETWEEN ? AND ?");
            preparedStatement.setString(1, "login");
            preparedStatement.setInt(2, date1);
            preparedStatement.setInt(3, date2);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                numOfLogins = resultSet.getInt("logins_count");
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

        return numOfLogins;
    }

    @Override
    public List<Country> numOfLoginsCountry() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT r.country AS country, COUNT(l.event_id) AS logins_count\n" +
                    "FROM registrations r\n" +
                    "LEFT JOIN log_in_outs l ON r.user_id = l.user_id AND l.event_type = ?\n" +
                    "GROUP BY r.country;");
            preparedStatement.setString(1, "login");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String countryName = resultSet.getString("country");
                int number = resultSet.getInt("logins_count");
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
    public List<Country> numOfLoginsCountryDate(Integer date1, Integer date2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT r.country AS country, COUNT(l.event_id) AS logins_count\n" +
                    "FROM registrations r\n" +
                    "LEFT JOIN log_in_outs l ON r.user_id = l.user_id AND l.event_type = ? AND l.event_timestamp BETWEEN ? AND ?\n" +
                    "GROUP BY r.country;");
            preparedStatement.setString(1, "login");
            preparedStatement.setInt(2, date1);
            preparedStatement.setInt(3, date2);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String countryName = resultSet.getString("country");
                int number = resultSet.getInt("logins_count");
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
    public int numOfActiveUsers() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int numOfActiveUsers = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT COUNT(DISTINCT user_id) AS user_count FROM log_in_outs WHERE event_type = ?");
            preparedStatement.setString(1, "login");
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                numOfActiveUsers = resultSet.getInt("user_count");
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

        return numOfActiveUsers;
    }

    @Override
    public int numOfActiveUsersDate(Integer date1, Integer date2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int numOfActiveUsers = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT COUNT(DISTINCT user_id) AS user_count FROM log_in_outs WHERE " +
                    "event_type = ? AND event_timestamp BETWEEN ? AND ?");
            preparedStatement.setString(1, "login");
            preparedStatement.setInt(2, date1);
            preparedStatement.setInt(3, date2);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                numOfActiveUsers = resultSet.getInt("user_count");
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

        return numOfActiveUsers;
    }

    @Override
    public List<Country> numOfActiveUsersCountry() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT r.country AS country, COUNT(DISTINCT l.user_id) AS user_count\n" +
                    "FROM registrations r\n" +
                    "LEFT JOIN log_in_outs l ON r.user_id = l.user_id AND l.event_type = ?\n" +
                    "GROUP BY r.country;");
            preparedStatement.setString(1, "login");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String countryName = resultSet.getString("country");
                int number = resultSet.getInt("user_count");
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
    public List<Country> numOfActiveUsersCountryDate(Integer date1, Integer date2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT r.country AS country, COUNT(DISTINCT l.user_id) AS user_count\n" +
                    "FROM registrations r\n" +
                    "LEFT JOIN log_in_outs l ON r.user_id = l.user_id AND l.event_type = ? AND l.event_timestamp BETWEEN ? AND ?\n" +
                    "GROUP BY r.country;");
            preparedStatement.setString(1, "login");
            preparedStatement.setInt(2, date1);
            preparedStatement.setInt(3, date2);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String countryName = resultSet.getString("country");
                int number = resultSet.getInt("user_count");
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
    public List<LogInOut> userLogins(String user_id) {
        List<LogInOut> userLogins = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM log_in_outs WHERE user_id = ? AND event_type = ? ORDER BY event_timestamp DESC");
            preparedStatement.setString(1,user_id);
            preparedStatement.setString(2,"login");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int event_id = resultSet.getInt("event_id");
                int event_timestamp = resultSet.getInt("event_timestamp");
                String event_type = resultSet.getString("event_type");
                String user_login_id = resultSet.getString("user_id");
                LogInOut logInOut = new LogInOut(event_id, event_timestamp, event_type, user_login_id);
                userLogins.add(logInOut);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return userLogins;
    }

}

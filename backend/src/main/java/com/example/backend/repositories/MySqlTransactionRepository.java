package com.example.backend.repositories;


import com.example.backend.entities.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTransactionRepository extends MySqlAbstractRepository implements TransactionRepository{


    @Override
    public float totalRevenue() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        float totalRevenue = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT \n" +
                    "SUM(CASE WHEN transaction_currency = ? THEN transaction_amount ELSE transaction_amount * 1.3 END) AS total_revenue_usd\n" +
                    "FROM transactions;");
            preparedStatement.setString(1, "USD");
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                totalRevenue = resultSet.getFloat("total_revenue_usd");
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

        return totalRevenue;
    }

    @Override
    public float totalRevenueDate(Integer date1, Integer date2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        float totalRevenue = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT \n" +
                    "SUM(CASE WHEN transaction_currency = ? THEN transaction_amount ELSE transaction_amount * 1.3 END) AS total_revenue_usd\n" +
                    "FROM transactions WHERE event_timestamp BETWEEN ? AND ?");
            preparedStatement.setString(1, "USD");
            preparedStatement.setInt(2, date1);
            preparedStatement.setInt(3, date2);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                totalRevenue = resultSet.getFloat("total_revenue_usd");
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

        return totalRevenue;
    }

    @Override
    public List<Country> totalRevenueCountry() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT r.country AS country, " +
                    "SUM(CASE WHEN l.transaction_currency = ? THEN l.transaction_amount ELSE l.transaction_amount * 1.3 END) AS total_revenue_usd\n" +
                    "FROM registrations r\n" +
                    "LEFT JOIN transactions l ON r.user_id = l.user_id\n" +
                    "GROUP BY r.country;");
            preparedStatement.setString(1, "USD");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String countryName = resultSet.getString("country");
                float revenue = resultSet.getFloat("total_revenue_usd");
                Country country = new Country(countryName, revenue);
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
    public List<Country> totalRevenueCountryDate(Integer date1, Integer date2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT r.country AS country, " +
                    "SUM(CASE WHEN l.transaction_currency = ? THEN l.transaction_amount ELSE l.transaction_amount * 1.3 END) AS total_revenue_usd\n" +
                    "FROM registrations r\n" +
                    "LEFT JOIN transactions l ON r.user_id = l.user_id AND l.event_timestamp BETWEEN ? AND ?\n" +
                    "GROUP BY r.country;");
            preparedStatement.setString(1, "USD");
            preparedStatement.setInt(2, date1);
            preparedStatement.setInt(3, date2);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String countryName = resultSet.getString("country");
                float revenue = resultSet.getFloat("total_revenue_usd");
                Country country = new Country(countryName, revenue);
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
}

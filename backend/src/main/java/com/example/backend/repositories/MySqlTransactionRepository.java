package com.example.backend.repositories;


import com.example.backend.entities.TransactionEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlTransactionRepository extends MySqlAbstractRepository implements TransactionRepository{

    @Override
    public List<TransactionEvent> allTransactions() {
        List<TransactionEvent> transactions = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from transactions");
            while (resultSet.next()) {
                int event_id = resultSet.getInt("event_id");
                int event_timestamp = resultSet.getInt("event_timestamp");
                String event_type = resultSet.getString("event_type");
                String user_id = resultSet.getString("user_id");
                float transaction_amount = resultSet.getFloat("transaction_amount");
                String transaction_currency = resultSet.getString("transaction_currency");
                TransactionEvent transactionEvent = new TransactionEvent(event_id, event_timestamp, event_type, user_id, transaction_amount, transaction_currency);
                transactions.add(transactionEvent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return transactions;
    }
}

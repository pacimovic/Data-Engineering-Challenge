package com.example.backend.repositories;

import com.example.backend.entities.LogInOut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlLogInOutRepository extends MySqlAbstractRepository implements LogInOutRepository{
    @Override
    public List<LogInOut> allLogInOuts() {
        List<LogInOut> logInOuts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from log_in_outs");
            while (resultSet.next()) {
                int event_id = resultSet.getInt("event_id");
                int event_timestamp = resultSet.getInt("event_timestamp");
                String event_type = resultSet.getString("event_type");
                String user_id = resultSet.getString("user_id");
                LogInOut logInOut = new LogInOut(event_id, event_timestamp, event_type, user_id);
                logInOuts.add(logInOut);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return logInOuts;

    }

    @Override
    public LogInOut addLogInOut(LogInOut logInOut) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();


            preparedStatement = connection.prepareStatement("INSERT INTO log_in_outs (event_id, event_timestamp, event_type, user_id) VALUES(?, ?, ?, ?)");
            preparedStatement.setInt(1, logInOut.getEvent_id());
            preparedStatement.setInt(2, logInOut.getEvent_timestamp());
            preparedStatement.setString(3, logInOut.getEvent_type());
            preparedStatement.setString(4, logInOut.getUser_id());
            preparedStatement.executeUpdate();
            //resultSet = preparedStatement.getGeneratedKeys();

           /*
            if (resultSet.next()) {
                logInOut.setEvent_id(resultSet.getInt(1));
            }
            */
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            //this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return logInOut;

    }

}

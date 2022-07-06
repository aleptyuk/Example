package example.api.data_base;

import java.sql.*;

import static example.config.TestConfigProvider.*;

public class ConnectorUtil {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;


    public static void Connector() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATA_BASE, USER_DATA_BASE, PASSWORD_DATA_BASE);
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getResultSet(String query) {
        try {
            Connector();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public static ResultSet getResultSetPrepared(PreparedStatement preparedStatement) {
        try {
            Connector();
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public static PreparedStatement getPreparedStatement(String query) {
        try {
            Connector();
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }


    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            statement.close();
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}

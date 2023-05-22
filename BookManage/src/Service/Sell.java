package Service;
import jdbc.JdbcComm;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class Sell {
    public BigDecimal sell(String book_name) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Connection connection = jdbc.getConnection();
        Statement statement = connection.createStatement();

        String query = "UPDATE t_book SET qty = qty - 1 WHERE book_name = '" + book_name + "'";
        statement.executeUpdate(query);

        query = "SELECT selling_price FROM t_book WHERE book_name = '" + book_name + "'";
        ResultSet resultSet = statement.executeQuery(query);

        BigDecimal price = BigDecimal.valueOf(0.00);
        if (resultSet.next()) {
            price = resultSet.getBigDecimal("selling_price");
        }

        resultSet.close();
        statement.close();
        connection.close();

        return price;
    }


    public BigDecimal sellALot(LinkedList<String> books) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Sell sell = new Sell();
        BigDecimal sum = BigDecimal.valueOf(0.00);
        BigDecimal discount_rate = BigDecimal.valueOf(0.30);

        for (String book_name : books) {
            sum = sum.add(sell.sell(book_name));
        }

        sum = sum.multiply(discount_rate);

        return sum;
    }
}

package com.dev.pos.dao;

import com.dev.pos.db.DBConnection;
import com.dev.pos.dto.CustomerDTO;
import com.dev.pos.dto.UserDTO;
import com.dev.pos.util.security.PasswordManager;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessCode {

    //-------------- User start---------

    public static boolean createUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {

            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO user VALUES(?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userDTO.getEmail());
            preparedStatement.setString(2, PasswordManager.encrypt(userDTO.getPassword()));

            return preparedStatement.executeUpdate()>0;
    }

    public static UserDTO findUser(String email) throws SQLException, ClassNotFoundException {

            Connection connection = DBConnection.getInstance().getConnection();
            String sql= "SELECT * FROM user WHERE email = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new UserDTO(
                        resultSet.getString(1),
                        resultSet.getString(2)
                );
            }
            return null;
    }

    //-------------- User end---------

    //-------------- Customer start---------

    public static boolean createCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES(?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customerDTO.getEmail());
        preparedStatement.setString(2,customerDTO.getName());
        preparedStatement.setString(3,customerDTO.getContact());
        preparedStatement.setDouble(4,customerDTO.getSalary());

        return preparedStatement.executeUpdate()>0;
    }

    public static boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE customer SET name = ?,contact=?,salary=?  WHERE email = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customerDTO.getName());
        preparedStatement.setString(2,customerDTO.getContact());
        preparedStatement.setDouble(3,customerDTO.getSalary());
        preparedStatement.setString(4,customerDTO.getEmail());

        return preparedStatement.executeUpdate()>0;
    }

    public static boolean deleteCustomer(String email) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM customer WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        return preparedStatement.executeUpdate()>0;

    }

    public static CustomerDTO findCustomer(String email) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM customer WHERE email = ?";
        ResultSet resultSet = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
        if (resultSet.next()) {
            return new CustomerDTO(
                    resultSet.getString("email"),
                    resultSet.getString("name"),
                    resultSet.getString("contact"),
                    resultSet.getDouble("salary")
            );
        }
        return null;
    }

    public static List<CustomerDTO> findAllCustomer() throws SQLException, ClassNotFoundException {

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM customer");

        while (resultSet.next()) {
            customerDTOList.add(new CustomerDTO(
                    resultSet.getString("email"),
                    resultSet.getString("name"),
                    resultSet.getString("contact"),
                    resultSet.getDouble("salary")
            ));
        }
        return customerDTOList;
    }

    public static List<CustomerDTO> searchCustomer(String searchText) throws SQLException, ClassNotFoundException {
        return null;
    }

    //-------------- Customer end---------
}
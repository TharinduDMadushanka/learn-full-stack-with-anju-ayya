package com.dev.pos.controller;

import com.dev.pos.Enum.BoType;
import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.UserBo;
import com.dev.pos.dao.DatabaseAccessCode;
import com.dev.pos.db.DBConnection;
import com.dev.pos.dto.UserDTO;
import com.dev.pos.util.security.PasswordManager;
import com.dev.pos.util.security.UserSessionData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public PasswordField txtPassword;

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    public void btnLoginOnAction(ActionEvent actionEvent) {
//        try{
//            Connection connection = DBConnection.getInstance().getConnection();
//            String sql= "SELECT * FROM user WHERE email = ?";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, txtEmail.getText());
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()){
//                if(PasswordManager.checkPassword(txtPassword.getText().trim(), resultSet.getString("password"))){
//                    System.out.println("Password Matched");
//                    setUI("DashboardForm");
//                }else {
//                    new Alert(Alert.AlertType.ERROR, "User not found.!").show();
//                }
//            }else {
//                new Alert(Alert.AlertType.ERROR, "User not found.!").show();
//            }
//
//        }catch (SQLException | ClassNotFoundException e){
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

       try{

//           UserDTO user = new DatabaseAccessCode().findUser(txtEmail.getText());
           UserDTO user = userBo.findUser(txtEmail.getText());

           if(user != null){
               if(PasswordManager.checkPassword(txtPassword.getText(), user.getPassword())){
                   UserSessionData.email= txtEmail.getText().trim();
                   setUI("DashboardForm");
               }else {
                   new Alert(Alert.AlertType.ERROR, "User not found.!").show();
               }
           }else {
               new Alert(Alert.AlertType.ERROR, "User not found.!").show();
           }

       }catch (ClassNotFoundException | SQLException e){
           e.printStackTrace();
       } catch (IOException e) {
           throw new RuntimeException(e);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }

    public void btnSignupOnAction(ActionEvent actionEvent) throws IOException {

        setUI("SignupForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}

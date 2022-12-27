package com.example.loginappl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;


import java.io.IOException;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String favChef){

        Parent root = null;

        if (username != null && favChef != null){                           //if going to logInScene

            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LogginController logginController = loader.getController();
                logginController.set_userInformation(username,favChef);
            } catch(IOException e){
                e.printStackTrace();
            }
    } else{
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);

        stage.setScene(new Scene(root, 640,420));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password, String favChef){

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckIfUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_schema", "root", "givemeyob123!");
            psCheckIfUserExists =connection.prepareStatement("SELECT * FROM Users WHERE userName = ?");
            psCheckIfUserExists.setString(1,username);
            resultSet =psCheckIfUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists!!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The username is not viable");
                alert.show();

            } else{
                psInsert = connection.prepareStatement("INSERT INTO Users (userName, password, favChef VALUES (?, ?, ?))");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.setString(3, favChef);

                psInsert.executeUpdate();

                changeScene(event, "logg-in.fxml", "Welcome!", username,favChef);
            }
        }catch (SQLException e){
            e.printStackTrace();

        } finally {                 //close connection
            if(resultSet != null){
                try{
                    resultSet.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(psCheckIfUserExists != null){
                try{
                    psCheckIfUserExists.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try{
                    psInsert.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }

    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_schema", "root", "givemeyob123!");
            preparedStatement = connection.prepareStatement("SELECT password, favChef FROM Users WHERE userName = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided information is incorrect!");
                alert.show();


            }else{
                while(resultSet.next()){                                            //loop through to compare if inputted password is correct
                    String retrivedPassword = resultSet.getString("password");
                    String retrievedChef = resultSet.getString("favChef");

                    if(retrivedPassword.equals(password)){
                        changeScene(event,"logg-in.fxml", "Welcome!", username, retrievedChef);
                    }else{
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided information is incorrect");
                        alert.show();
                    }
                }

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }

}

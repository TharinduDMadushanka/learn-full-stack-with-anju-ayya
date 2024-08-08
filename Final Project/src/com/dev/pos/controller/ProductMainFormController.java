package com.dev.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ProductMainFormController {
    public AnchorPane context;
    public TextField txtProductCode;
    public TextField txtProductDescription;
    public Button btnSave;
    public TextField txtSearch;
    public TableView tblProduct;
    public TableColumn colProductId;
    public TableColumn colDescription;
    public TableColumn colShowMore;
    public TableColumn colDelete;
    public TextField txtSelectedProductCode;
    public TableView tblProductMain;
    public TableColumn colNo;
    public TableColumn colQty;
    public TableColumn colBuyPrice;
    public TableColumn colDiscount;
    public TableColumn colShowPrice;
    public TableColumn colSellPrice;
    public TableColumn colMainDelete;
    public TextField txtSelectedDescription;

    public void btnBacktoHome(ActionEvent actionEvent) {
    }

    public void btnAddNewProductOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnNewBatchOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
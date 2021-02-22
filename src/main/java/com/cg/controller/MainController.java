package com.cg.controller;

import com.cg.model.RGBColor;
import com.cg.model.XYZColor;
import com.cg.model.LABColor;
import com.cg.converter.LABConverter;
import com.cg.converter.RGBConverter;
import com.cg.converter.XYZConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class MainController {

    @FXML
    private Slider sliderRgb1;
    @FXML
    private Slider sliderRgb2;
    @FXML
    private Slider sliderRgb3;
    @FXML
    private Slider sliderXyz1;
    @FXML
    private Slider sliderXyz2;
    @FXML
    private Slider sliderXyz3;
    @FXML
    private Slider sliderLab1;
    @FXML
    private Slider sliderLab2;
    @FXML
    private Slider sliderLab3;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField fieldRgb1;
    @FXML
    private TextField fieldRgb2;
    @FXML
    private TextField fieldRgb3;
    @FXML
    private TextField fieldLab1;
    @FXML
    private TextField fieldLab2;
    @FXML
    private TextField fieldLab3;
    @FXML
    private TextField fieldXyz1;
    @FXML
    private TextField fieldXyz2;
    @FXML
    private TextField fieldXyz3;


    @FXML
    public void initialize() {
        colorPicker.setOnAction(e -> {
            Color c = colorPicker.getValue();

            double r = c.getRed() * 255;
            double g = c.getGreen() * 255;
            double b = c.getBlue() * 255;
            RGBColor rgbColor = new RGBColor(r, g, b);
            XYZColor xyzColor = XYZConverter.fromRGB(rgbColor);
            LABColor labColor = LABConverter.fromXYZ(xyzColor);
            fieldRgb1.setText(Double.toString(r));
            fieldRgb2.setText(Double.toString(g));
            fieldRgb3.setText(Double.toString(b));
            fieldLab1.setText(Double.toString(labColor.getL()));
            fieldLab2.setText(Double.toString(labColor.getA()));
            fieldLab3.setText(Double.toString(labColor.getB()));
            fieldXyz1.setText(Double.toString(xyzColor.getX()));
            fieldXyz2.setText(Double.toString(xyzColor.getY()));
            fieldXyz3.setText(Double.toString(xyzColor.getZ()));

        });

        sliderRgb1.valueProperty().addListener((changed, oldValue, newValue)
                -> {
            fieldRgb1.setText(Double.toString(newValue.doubleValue()));
            setAllFromRgb();
        });
        sliderRgb2.valueProperty().addListener((changed, oldValue, newValue)
                -> {
            fieldRgb2.setText(Double.toString(newValue.doubleValue()));
            setAllFromRgb();
        });
        sliderRgb3.valueProperty().addListener((changed, oldValue, newValue)
                -> {
            fieldRgb3.setText(Double.toString(newValue.doubleValue()));
            setAllFromRgb();
        });
        sliderXyz1.valueProperty().addListener((changed, oldValue, newValue)
                -> {
            fieldXyz1.setText(Double.toString(newValue.doubleValue()));
            setAllFromXyz();
        });
        sliderXyz2.valueProperty().addListener((changed, oldValue, newValue)
                -> {
            fieldXyz2.setText(Double.toString(newValue.doubleValue()));
            setAllFromXyz();
        });
        sliderXyz3.valueProperty().addListener((changed, oldValue, newValue)
                -> {
            fieldXyz3.setText(Double.toString(newValue.doubleValue()));
            setAllFromXyz();
        });
        sliderLab1.valueProperty().addListener((changed, oldValue, newValue)
                -> {
            fieldLab1.setText(Double.toString(newValue.doubleValue()));
            setAllFromLab();
        });
        sliderLab2.valueProperty().addListener((changed, oldValue, newValue)
                -> {
            fieldLab2.setText(Double.toString(newValue.doubleValue()));
            setAllFromLab();
        });
        sliderLab3.valueProperty().addListener((changed, oldValue, newValue)
                -> {
            fieldLab3.setText(Double.toString(newValue.doubleValue()));
            setAllFromLab();
        });
        EventHandler<ActionEvent> changeListenerRgb = (event) -> setAllFromRgb();
        EventHandler<ActionEvent> changeListenerLab = (event) -> setAllFromLab();
        EventHandler<ActionEvent> changeListenerXyz = (event) -> setAllFromXyz();
        fieldRgb1.setOnAction(changeListenerRgb);
        fieldRgb2.setOnAction(changeListenerRgb);
        fieldRgb3.setOnAction(changeListenerRgb);
        fieldLab1.setOnAction(changeListenerLab);
        fieldLab2.setOnAction(changeListenerLab);
        fieldLab3.setOnAction(changeListenerLab);
        fieldXyz1.setOnAction(changeListenerXyz);
        fieldXyz2.setOnAction(changeListenerXyz);
        fieldXyz3.setOnAction(changeListenerXyz);
    }

    private void setAllFromRgb() {
        RGBColor rgbColor;
        try {
            rgbColor = new RGBColor(Double.parseDouble(fieldRgb1.getText()), Double.parseDouble(fieldRgb2.getText()),
                    Double.parseDouble(fieldRgb3.getText()));
            double r = rgbColor.getR();
            double g = rgbColor.getG();
            double b = rgbColor.getB();
            if(r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0){
                showAlert();
                return;
            }
        } catch (NumberFormatException e) {
            showAlert();
            return;
        }

        LABColor labColor = LABConverter.fromRGB(rgbColor);
        XYZColor xyzColor = XYZConverter.fromRGB(rgbColor);
        fieldLab1.setText(Double.toString(labColor.getL()));
        fieldLab2.setText(Double.toString(labColor.getA()));
        fieldLab3.setText(Double.toString(labColor.getB()));
        fieldXyz1.setText(Double.toString(xyzColor.getX()));
        fieldXyz2.setText(Double.toString(xyzColor.getY()));
        fieldXyz3.setText(Double.toString(xyzColor.getZ()));
    }

    private void setAllFromLab() {
        LABColor labColor;
        try {
            labColor = new LABColor(Double.parseDouble(fieldLab1.getText()), Double.parseDouble(fieldLab2.getText()),
                    Double.parseDouble(fieldLab3.getText()));
            double l = labColor.getL();
            double a = labColor.getA();
            double b = labColor.getB();
            if(l > 100 || l < 0 || a > 100 || a < -100 || b > 100 || b < -100){
                showAlert();
                return;
            }
        } catch (NumberFormatException e) {
            showAlert();
            return;
        }
        RGBColor rgbColor = RGBConverter.fromLAB(labColor);
        XYZColor xyzColor = XYZConverter.fromLab(labColor);
        fieldRgb1.setText(Double.toString(rgbColor.getR()));
        fieldRgb2.setText(Double.toString(rgbColor.getG()));
        fieldRgb3.setText(Double.toString(rgbColor.getB()));
        fieldXyz1.setText(Double.toString(xyzColor.getX()));
        fieldXyz2.setText(Double.toString(xyzColor.getY()));
        fieldXyz3.setText(Double.toString(xyzColor.getZ()));
    }

    private void setAllFromXyz() {
        XYZColor xyzColor;
        try {
            xyzColor = new XYZColor(Double.parseDouble(fieldXyz1.getText()), Double.parseDouble(fieldXyz2.getText()),
                    Double.parseDouble(fieldXyz3.getText()));
            double x = xyzColor.getX();
            double y = xyzColor.getY();
            double z = xyzColor.getZ();
            if(x > 110 || x < 0 || y > 110 || y < 0 || z > 110 || z < 0){
                showAlert();
                return;
            }
        } catch (NumberFormatException e) {
            showAlert();
            return;
        }
        RGBColor rgbColor = RGBConverter.fromXYZ(xyzColor);
        LABColor labColor = LABConverter.fromXYZ(xyzColor);
        fieldRgb1.setText(Double.toString(rgbColor.getR()));
        fieldRgb2.setText(Double.toString(rgbColor.getG()));
        fieldRgb3.setText(Double.toString(rgbColor.getB()));
        fieldLab1.setText(Double.toString(labColor.getL()));
        fieldLab2.setText(Double.toString(labColor.getA()));
        fieldLab3.setText(Double.toString(labColor.getB()));
    }

    private void showAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Wrong input!");
        alert.showAndWait();
    }
}
